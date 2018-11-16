package finance.domainservice.service.userinfo.impl;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import finance.core.dal.dataobject.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import finance.api.model.base.XMap;
import finance.api.model.response.ResponseResult;
import finance.api.model.vo.EverydayClockDateListVO;
import finance.api.model.vo.EverydayClockVO;
import finance.api.model.vo.GrowTaskVO;
import finance.api.model.vo.NewRegisterListVO;
import finance.core.common.constants.Constant;
import finance.core.common.enums.*;
import finance.domainservice.service.jwt.JwtService;
import finance.domainservice.service.userinfo.UserTaskBiz;
import finance.core.dal.dao.*;

/**
 * @program: finance-server
 *
 * @description: 签到
 *
 * @author: MORUIHAI
 *
 * @create: 2018-08-26 11:47
 **/
@Service
public class UserTaskBizImpl implements UserTaskBiz {
    private static final Logger      logger = LoggerFactory.getLogger(UserTaskBizImpl.class);
    @Resource
    private JwtService               jwtService;
    @Resource
    private FinanceCoinGameDAO       financeCoinGameMapper;
    @Resource
    private FinanceCoinLogDAO        financeCoinLogMapper;
    @Resource
    private StringRedisTemplate      stringRedisTemplate;
    @Resource
    private FinanceIdCardInfoDAO     financeIdCardInfoMapper;
    @Resource
    private FinanceUserTaskInfoDAO   financeUserTaskInfoMapper;
    @Resource
    private FinanceUserAccountDAO    financeUserAccountMapper;
    @Resource
    private FinanceUserInviteInfoDAO financeUserInviteInfoMapper;
    @Resource
    private FinanceOrderDAO          financeOrderMapper;

    @Value("${everydayclock.user.key.prefix}")
    private String                   everydayClockUserPrefix;
    @Value("${growtask.invite.stage.num}")
    private int                      growTaskInviteStageNum;

    @Override
    public EverydayClockVO findEveryDayClockInfo() {

        /**1.jwt 获取用户user_id **/
        Long userId = jwtService.getUserInfo().getId();
        String everydayClockUserRedisKey = everydayClockUserPrefix + userId;
        String everydayClockUserRedisValue = stringRedisTemplate.opsForValue()
            .get(everydayClockUserRedisKey);
        if (!StringUtils.isEmpty(everydayClockUserRedisValue)) {
            EverydayClockVO everydayClockRedis = JSON.parseObject(everydayClockUserRedisValue,
                new TypeReference<EverydayClockVO>() {
                });

            //当前可用金币
            Integer totalCanUserCoin = financeCoinLogMapper.selectCoinNumByUserId(userId);
            everydayClockRedis
                .setCurrentCanUseTotalCoin(totalCanUserCoin == null ? 0 : totalCanUserCoin);
            return everydayClockRedis;

        }

        EverydayClockVO everydayClockVO = new EverydayClockVO();

        /**2.活动表里查询每日签到的信息**/
        FinanceCoinGame financeCoinGame = financeCoinGameMapper
            .selectByTaskType(Constant.EVERYDAY_CLOCK, GameType.task.getCode());
        everydayClockVO.setCoinNum(financeCoinGame.getNum());
        /**3.查询用户所有签到信息 **/
        List<FinanceCoinLog> financeCoinLogList = financeCoinLogMapper
            .selectByUserIdAndTaskId(userId, financeCoinGame.getId());
        Map<Integer, FinanceCoinLog> currentMonthClockMap = new HashMap<>();
        Integer totalCoinNum = new Integer(0);
        int count = 0;
        if (financeCoinLogList != null && financeCoinLogList.size() > 0) {
            //3.1 查询昨天签到信息
            FinanceCoinLog yesterdayClockInfo = financeCoinLogMapper
                .selectYesAndToDatClockByUserId(userId, financeCoinGame.getId(), "Y", "");
            //3.2查询今日签到信息
            FinanceCoinLog todayClockInfo = financeCoinLogMapper
                .selectYesAndToDatClockByUserId(userId, financeCoinGame.getId(), "", "Y");

            int currentMonth = LocalDate.now().getMonthValue();
            Calendar cal = Calendar.getInstance();
            int month = 0;
            int day = 0;

            Date preDate = null;
            Boolean isContinuous = true;
            for (int i = 0; i < financeCoinLogList.size(); i++) {
                totalCoinNum = totalCoinNum + financeCoinLogList.get(i).getNum();
                cal.setTime(financeCoinLogList.get(i).getCreateTime());
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);

                month = cal.get(Calendar.MONTH) + 1;
                day = cal.get(Calendar.DAY_OF_MONTH);
                //3.1 查询当月签到记录
                if (month == currentMonth) {
                    currentMonthClockMap.put(day, financeCoinLogList.get(i));
                }
                if (isContinuous) {
                    if (yesterdayClockInfo != null) {
                        if (preDate != null) {
                            if ((preDate.getTime() - cal.getTimeInMillis())
                                / (1000 * 60 * 60 * 24) == 1) {
                                count++;
                            } else {
                                isContinuous = false;
                            }
                        } else {
                            count++;
                        }
                    } else {
                        if (todayClockInfo != null) {
                            count = 1;
                        }
                    }
                    preDate = cal.getTime();
                }

            }

        }
        int totalCurrentMonthDays = getCurrentMonthDay();
        EverydayClockDateListVO everydayClockDateListVO = null;
        List<EverydayClockDateListVO> everydayClockDateListVOList = new ArrayList<>();
        for (int j = 1; j <= totalCurrentMonthDays; j++) {
            everydayClockDateListVO = new EverydayClockDateListVO();
            everydayClockDateListVO.setDate(j);
            if (currentMonthClockMap.containsKey(j)) {
                everydayClockDateListVO.setStatus("1");
            } else {
                everydayClockDateListVO.setStatus("0");
            }
            everydayClockDateListVOList.add(everydayClockDateListVO);

        }
        everydayClockVO.setSignList(everydayClockDateListVOList);
        everydayClockVO.setTotalCoinNum(totalCoinNum);
        everydayClockVO.setSigNum(count);

        String userClockJson = JSON.toJSONString(everydayClockVO);

        stringRedisTemplate.opsForValue().set(everydayClockUserRedisKey, userClockJson, 12,
            TimeUnit.HOURS);
        //当前可用金币
        Integer totalCanUserCoin = financeCoinLogMapper.selectCoinNumByUserId(userId);
        everydayClockVO.setCurrentCanUseTotalCoin(totalCanUserCoin == null ? 0 : totalCanUserCoin);

        return everydayClockVO;
    }

    @Override
    public ResponseResult<Boolean> finishTask(XMap xMap) {
        String type = String.valueOf(xMap.get("type"));
        String taskId = String.valueOf(xMap.get("taskId"));
        if (type.equals(GameTaskType.everydaySign.getCode())) {
            return finishEverydaySign();
        } else if (type.equals(GameTaskType.newRegisterTask.getCode())) {
            return finishNewRegisterTask(taskId);
        } else if (type.equals(GameTaskType.groupTask.getCode())) {
            return finishGrowTask(taskId);

        }
        return null;
    }

    @Override
    public List<NewRegisterListVO> queryNewTaskInfo() {

        List<NewRegisterListVO> newRegisterListVOList = new ArrayList<>();
        /**1.jwt 获取用户user_id **/
        Long userId = jwtService.getUserInfo().getId();
        NewRegisterListVO newRegisterListVO = new NewRegisterListVO();

        /**2.查询 完善资料 **/
        //查询新手任务
        List<FinanceCoinGame> newRegisterTaskList = financeCoinGameMapper
            .selectNewRegisterTask(Constant.NEWREGISTERTASK, GameType.task.getCode());
        Map<String, FinanceCoinGame> financeCoinGameMap = new HashMap<>();
        newRegisterTaskList.forEach(financeCoinGame -> financeCoinGameMap
            .put(financeCoinGame.getTaskName(), financeCoinGame));
        //2.1 先查询用户任务表
        FinanceCoinGame perfectInfoTask = financeCoinGameMap
            .get(GameTaskType.perfectInfoTask.getCode());
        FinanceUserTaskInfo financeUserTaskInfo = financeUserTaskInfoMapper
            .selectByUserIdAndTaskId(userId, perfectInfoTask.getId());
        if (financeUserTaskInfo != null) {
            newRegisterListVO.setStatus(financeUserTaskInfo.getFinishStatus());

        } else {
            FinanceIdCardInfo financeIdCardInfo = financeIdCardInfoMapper.selectByUserId(userId);
            if (financeIdCardInfo != null && financeIdCardInfo.getAuthStatus().intValue() == 1) {
                newRegisterListVO.setStatus(TaskStatus.finishNotReceive.getCode());
            } else {
                newRegisterListVO.setStatus(TaskStatus.unfinish.getCode());
            }
        }
        newRegisterListVO.setCoinNum(perfectInfoTask.getNum());
        newRegisterListVO.setPic(perfectInfoTask.getLogoUrl());
        newRegisterListVO
            .setTitle(GameTaskType.getParam().get(perfectInfoTask.getTaskName()).getMsg());
        newRegisterListVO.setTaskId(perfectInfoTask.getId());
        newRegisterListVOList.add(newRegisterListVO);

        /**3.提现 **/
        newRegisterListVO = new NewRegisterListVO();
        //3.1 先查询用户任务表
        FinanceCoinGame withdrawalTask = financeCoinGameMap
            .get(GameTaskType.withdrawalTask.getCode());
        FinanceUserTaskInfo withdrawalUserTaskInfo = financeUserTaskInfoMapper
            .selectByUserIdAndTaskId(userId, withdrawalTask.getId());
        if (withdrawalUserTaskInfo != null) {
            newRegisterListVO.setStatus(withdrawalUserTaskInfo.getFinishStatus());

        } else {
            FinanceOrder financeOrder = new FinanceOrder();
            financeOrder.setUserId(userId);
            financeOrder.setTransType(OrderType.debit.getCode());
            Long withdrawCount = financeOrderMapper.selectCountByOrder(financeOrder);
            // FinanceUserAccount financeUserAccount = financeUserAccountMapper.getAccountByUserId(userId);
            if (withdrawCount != null && withdrawCount.longValue() > 0) {
                newRegisterListVO.setStatus(TaskStatus.finishNotReceive.getCode());
            } else {
                newRegisterListVO.setStatus(TaskStatus.unfinish.getCode());
            }
        }
        newRegisterListVO.setCoinNum(withdrawalTask.getNum());
        newRegisterListVO.setPic(withdrawalTask.getLogoUrl());
        newRegisterListVO
            .setTitle(GameTaskType.getParam().get(withdrawalTask.getTaskName()).getMsg());
        newRegisterListVO.setTaskId(withdrawalTask.getId());
        newRegisterListVOList.add(newRegisterListVO);

        return newRegisterListVOList;
    }

    @Override
    public GrowTaskVO queryGrowTaskInfo() {
        GrowTaskVO growTaskVO = new GrowTaskVO();
        /**1.jwt 获取用户user_id **/
        Long userId = jwtService.getUserInfo().getId();
        /**2.查询用户邀请人数 **/
        List<Long> parentIdList = new ArrayList<>();
        parentIdList.add(userId);
        Long financeUserInviteInfo = financeUserInviteInfoMapper.selectCountByParentId(parentIdList,
            "");
        /**3.查询成长任务 **/
        List<FinanceCoinGame> growTaskList = financeCoinGameMapper
            .selectNewRegisterTask(Constant.GROWTASK, GameType.task.getCode());
        Map<String, FinanceCoinGame> financeCoinGameMap = new HashMap<>();
        growTaskList.forEach(financeCoinGame -> financeCoinGameMap
            .put(financeCoinGame.getTaskName(), financeCoinGame));

        //3.1 先查询用户任务表
        FinanceCoinGame invitePersonTask = financeCoinGameMap
            .get(GameTaskType.invitePerson.getCode());
        FinanceUserTaskInfo financeUserTaskInfo = financeUserTaskInfoMapper
            .selectByUserIdAndTaskId(userId, invitePersonTask.getId());
        if (financeUserTaskInfo != null) {
            if (financeUserInviteInfo.intValue() >= financeUserTaskInfo.getNextStageNum()) {
                growTaskVO.setStatus(TaskStatus.finishNotReceive.getCode());
            } else {
                growTaskVO.setStatus(financeUserTaskInfo.getFinishStatus());
            }

            growTaskVO.setNextNum(financeUserTaskInfo.getNextStageNum());

        } else {
            if (financeUserInviteInfo.intValue() >= growTaskInviteStageNum) {
                growTaskVO.setStatus(TaskStatus.finishNotReceive.getCode());
            } else {
                growTaskVO.setStatus(TaskStatus.unfinish.getCode());
            }
            growTaskVO.setNextNum(growTaskInviteStageNum);
        }
        growTaskVO.setTaskId(invitePersonTask.getId());
        growTaskVO.setPic(invitePersonTask.getLogoUrl());
        growTaskVO.setTitle(GameTaskType.getParam().get(invitePersonTask.getTaskName()).getMsg());
        growTaskVO.setCoinNum(invitePersonTask.getNum());
        growTaskVO.setCurrentNum(financeUserInviteInfo.intValue());

        return growTaskVO;
    }

    /**
      *功能描述:每日签到
      * @author: moruihai
      * @date: 2018/8/28 16:08
      * @param: []
      * @return: finance.model.finance.ResponseResult<java.lang.Boolean>
      */
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<Boolean> finishEverydaySign() {
        /**1.jwt 获取用户user_id **/
        Long userId = jwtService.getUserInfo().getId();

        /**2.活动表里查询每日签到的信息**/
        FinanceCoinGame financeCoinGame = financeCoinGameMapper
            .selectByTaskType(Constant.EVERYDAY_CLOCK, GameType.task.getCode());
        /**3.查询今日签到信息 **/
        FinanceCoinLog todayClockInfo = financeCoinLogMapper.selectYesAndToDatClockByUserId(userId,
            financeCoinGame.getId(), "", "Y");
        if (todayClockInfo != null) {
            return ResponseResult.error(finance.core.common.enums.CodeEnum.todayAlreadySign);
        }
        FinanceCoinLog financeCoinLog = new FinanceCoinLog();
        financeCoinLog.setUserId(userId);
        financeCoinLog.setTaskId(financeCoinGame.getId());
        financeCoinLog.setTaskName("每日签到");
        financeCoinLog.setNum(financeCoinGame.getNum());
        financeCoinLogMapper.insertSelective(financeCoinLog);
        /**4.删除redis中缓存的数据 **/
        String everydayClockUserRedisKey = everydayClockUserPrefix + userId;
        stringRedisTemplate.delete(everydayClockUserRedisKey);

        return ResponseResult.success(true);
    }

    /**
      *功能描述:新手任务
      * @author: moruihai
      * @date: 2018/8/28 16:03
      * @param: [taskId]
      * @return: finance.model.finance.ResponseResult<java.lang.Boolean>
      */
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<Boolean> finishNewRegisterTask(String taskId) {
        /**1.jwt 获取用户user_id **/
        Long userId = jwtService.getUserInfo().getId();
        Long tasksId = Long.valueOf(taskId);
        //增加验证，防刷接口
        Boolean isValid = false;
        FinanceCoinGame financeCoinGame = financeCoinGameMapper.selectByPrimaryKey(tasksId);
        logger.info("financeCoinGame:" + financeCoinGame.toString());
        logger.info("financeCoinGame.getTaskName().equals(GameTaskType.withdrawalTask.getCode()):"
                    + financeCoinGame.getTaskName().equals(GameTaskType.withdrawalTask.getCode()));
        if (GameTaskType.perfectInfoTask.getCode().equals(financeCoinGame.getTaskName())) {
            FinanceIdCardInfo financeIdCardInfo = financeIdCardInfoMapper.selectByUserId(userId);
            if (financeIdCardInfo != null && financeIdCardInfo.getAuthStatus().intValue() == 1) {
                isValid = true;
            }

        } else if (GameTaskType.withdrawalTask.getCode().equals(financeCoinGame.getTaskName())) {
            FinanceOrder financeOrder = new FinanceOrder();
            financeOrder.setUserId(userId);
            financeOrder.setTransType(OrderType.debit.getCode());
            Long withdrawCount = financeOrderMapper.selectCountByOrder(financeOrder);
            if (withdrawCount != null && withdrawCount.longValue() > 0) {
                isValid = true;
            }
        }
        FinanceUserTaskInfo newRegisterTaskRecord = financeUserTaskInfoMapper
            .selectByUserIdAndTaskId(userId, tasksId);
        if (newRegisterTaskRecord != null) {
            isValid = false;
        }
        if (!isValid) {
            return ResponseResult.error(finance.core.common.enums.CodeEnum.invalidOperation);
        }

        /**2.写人用户任务完成表 **/
        FinanceUserTaskInfo financeUserTaskInfo = new FinanceUserTaskInfo();
        financeUserTaskInfo.setUserId(userId);
        financeUserTaskInfo.setTaskId(tasksId);
        financeUserTaskInfo.setFinishStatus(TaskStatus.receive.getCode());
        financeUserTaskInfoMapper.insertSelective(financeUserTaskInfo);

        /**3.写入金币记录表 **/
        //3.1.获取任务信息
        // FinanceCoinGame financeCoinGame = financeCoinGameMapper.selectByPrimaryKey(tasksId);
        FinanceCoinLog financeCoinLog = new FinanceCoinLog();
        financeCoinLog.setUserId(userId);
        financeCoinLog.setNum(financeCoinGame.getNum());
        financeCoinLog.setTaskId(tasksId);
        financeCoinLog
            .setTaskName(GameTaskType.getParam().get(financeCoinGame.getTaskName()).getMsg());
        financeCoinLogMapper.insertSelective(financeCoinLog);

        return ResponseResult.success(true);
    }

    /**
      *功能描述:成长任务：邀请好友
      * @author: moruihai
      * @date: 2018/8/28 16:02
      * @param: [taskId]
      * @return: finance.model.finance.ResponseResult<java.lang.Boolean>
      */
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<Boolean> finishGrowTask(String taskId) {
        /**1.jwt 获取用户user_id **/
        Long userId = jwtService.getUserInfo().getId();
        Long tasksId = Long.valueOf(taskId);
        //防刷
        Boolean isValid = true;
        /**2.查询用户邀请人数 **/
        List<Long> parentIdList = new ArrayList<>();
        parentIdList.add(userId);
        Long financeUserInviteInfo = financeUserInviteInfoMapper.selectCountByParentId(parentIdList,
            "");
        FinanceUserTaskInfo inviteTaskInfo = financeUserTaskInfoMapper
            .selectByUserIdAndTaskId(userId, tasksId);
        if (inviteTaskInfo != null) {
            if (financeUserInviteInfo < (inviteTaskInfo.getNextStageNum()
                                         - growTaskInviteStageNum)) {
                isValid = false;
            }

        } else {
            if (financeUserInviteInfo < growTaskInviteStageNum) {
                isValid = false;
            }
        }
        if (!isValid) {
            return ResponseResult.error(CodeEnum.invalidOperation);
        }

        FinanceUserTaskInfo financeUserTaskInfo = new FinanceUserTaskInfo();
        financeUserTaskInfo.setUserId(userId);
        financeUserTaskInfo.setTaskId(tasksId);
        financeUserTaskInfo.setFinishStatus(TaskStatus.unfinish.getCode());
        /**2.写人用户任务完成表 **/
        //查询邀请好友用户任务
        // FinanceUserTaskInfo inviteTaskInfo = financeUserTaskInfoMapper.selectByUserIdAndTaskId(userId,tasksId);
        if (inviteTaskInfo != null) {
            financeUserTaskInfo.setId(inviteTaskInfo.getId());
            financeUserTaskInfo
                .setNextStageNum(inviteTaskInfo.getNextStageNum() + growTaskInviteStageNum);
            financeUserTaskInfoMapper.updateByPrimaryKeySelective(financeUserTaskInfo);
        } else {
            financeUserTaskInfo.setNextStageNum(growTaskInviteStageNum * 2);
            financeUserTaskInfoMapper.insertSelective(financeUserTaskInfo);
        }

        /**3.写入金币记录表 **/
        //3.1.获取任务信息
        FinanceCoinGame financeCoinGame = financeCoinGameMapper.selectByPrimaryKey(tasksId);
        FinanceCoinLog financeCoinLog = new FinanceCoinLog();
        financeCoinLog.setUserId(userId);
        financeCoinLog.setNum(financeCoinGame.getNum());
        financeCoinLog.setTaskId(tasksId);
        financeCoinLog.setTaskName(GameTaskType.invitePerson.getMsg());
        financeCoinLogMapper.insertSelective(financeCoinLog);

        return ResponseResult.success(true);

    }

    /**
      *功能描述:获取当月的天数
      * @author: moruihai
      * @date: 2018/8/27 10:51
      * @param: []
      * @return: int
      */
    private int getCurrentMonthDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }
}
