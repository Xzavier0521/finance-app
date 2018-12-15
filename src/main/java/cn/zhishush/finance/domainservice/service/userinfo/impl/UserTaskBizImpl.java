package cn.zhishush.finance.domainservice.service.userinfo.impl;

import cn.zhishush.finance.api.model.base.XMap;
import cn.zhishush.finance.api.model.response.ResponseResult;
import cn.zhishush.finance.api.model.vo.coin.EverydayClockDateListVO;
import cn.zhishush.finance.api.model.vo.coin.EverydayClockVO;
import cn.zhishush.finance.api.model.vo.coin.GrowTaskVO;
import cn.zhishush.finance.api.model.vo.coin.NewRegisterListVO;
import cn.zhishush.finance.core.common.constants.Constant;
import cn.zhishush.finance.core.common.enums.*;
import cn.zhishush.finance.core.dal.dao.account.IdCardInfoDAO;
import cn.zhishush.finance.core.dal.dao.account.UserAccountDAO;
import cn.zhishush.finance.core.dal.dao.coin.CoinGameDAO;
import cn.zhishush.finance.core.dal.dao.coin.CoinLogDAO;
import cn.zhishush.finance.core.dal.dao.order.OrderDAO;
import cn.zhishush.finance.core.dal.dao.user.UserInviteInfoDAO;
import cn.zhishush.finance.core.dal.dao.user.UserTaskInfoDAO;
import cn.zhishush.finance.core.dal.dataobject.account.IdCardInfoDO;
import cn.zhishush.finance.core.dal.dataobject.coin.CoinGameDO;
import cn.zhishush.finance.core.dal.dataobject.coin.CoinLogDO;
import cn.zhishush.finance.core.dal.dataobject.order.OrderDO;
import cn.zhishush.finance.core.dal.dataobject.user.UserTaskInfoDO;
import cn.zhishush.finance.domainservice.service.jwt.JwtService;
import cn.zhishush.finance.domainservice.service.userinfo.UserTaskBiz;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @program: finance-server
 * @description: 签到
 * @author: MORUIHAI
 * @create: 2018-08-26 11:47
 **/
@Service
public class UserTaskBizImpl implements UserTaskBiz {
    private static final Logger logger = LoggerFactory.getLogger(UserTaskBizImpl.class);
    @Resource
    private JwtService jwtService;
    @Resource
    private CoinGameDAO financeCoinGameMapper;
    @Resource
    private CoinLogDAO financeCoinLogMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private IdCardInfoDAO financeIdCardInfoMapper;
    @Resource
    private UserTaskInfoDAO financeUserTaskInfoMapper;
    @Resource
    private UserAccountDAO financeUserAccountMapper;
    @Resource
    private UserInviteInfoDAO financeUserInviteInfoMapper;
    @Resource
    private OrderDAO financeOrderMapper;

    @Value("${everydayclock.user.key.prefix}")
    private String everydayClockUserPrefix;
    @Value("${growtask.invite.stage.num}")
    private int growTaskInviteStageNum;

    @Override
    public EverydayClockVO findEveryDayClockInfo() {

        /** 1.jwt 获取用户user_id **/
        Long userId = jwtService.getUserInfo().getId();
        String everydayClockUserRedisKey = everydayClockUserPrefix + userId;
        String everydayClockUserRedisValue = stringRedisTemplate.opsForValue()
                .get(everydayClockUserRedisKey);
        if (!StringUtils.isEmpty(everydayClockUserRedisValue)) {
            EverydayClockVO everydayClockRedis = JSON.parseObject(everydayClockUserRedisValue,
                    new TypeReference<EverydayClockVO>() {
                    });

            // 当前可用金币
            Integer totalCanUserCoin = financeCoinLogMapper.selectCoinNumByUserId(userId);
            everydayClockRedis
                    .setCurrentCanUseTotalCoin(totalCanUserCoin == null ? 0 : totalCanUserCoin);
            return everydayClockRedis;

        }

        EverydayClockVO everydayClockVO = new EverydayClockVO();

        /** 2.活动表里查询每日签到的信息 **/
        CoinGameDO coinGameDO = financeCoinGameMapper.selectByTaskType(Constant.EVERYDAY_CLOCK,
                GameType.task.getCode());
        everydayClockVO.setCoinNum(coinGameDO.getNum());
        /** 3.查询用户所有签到信息 **/
        List<CoinLogDO> coinLogDOList = financeCoinLogMapper.selectByUserIdAndTaskId(userId,
                coinGameDO.getId());
        Map<Integer, CoinLogDO> currentMonthClockMap = new HashMap<>();
        Integer totalCoinNum = new Integer(0);
        int count = 0;
        if (coinLogDOList != null && coinLogDOList.size() > 0) {
            // 3.1 查询昨天签到信息
            CoinLogDO yesterdayClockInfo = financeCoinLogMapper
                    .selectYesAndToDatClockByUserId(userId, coinGameDO.getId(), "Y", "");
            // 3.2查询今日签到信息
            CoinLogDO todayClockInfo = financeCoinLogMapper.selectYesAndToDatClockByUserId(userId,
                    coinGameDO.getId(), "", "Y");

            int currentMonth = LocalDate.now().getMonthValue();
            Calendar cal = Calendar.getInstance();
            int month = 0;
            int day = 0;

            Date preDate = null;
            Boolean isContinuous = true;
            for (int i = 0; i < coinLogDOList.size(); i++) {
                totalCoinNum = totalCoinNum + coinLogDOList.get(i).getNum();
                cal.setTime(coinLogDOList.get(i).getCreateTime());
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);

                month = cal.get(Calendar.MONTH) + 1;
                day = cal.get(Calendar.DAY_OF_MONTH);
                // 3.1 查询当月签到记录
                if (month == currentMonth) {
                    currentMonthClockMap.put(day, coinLogDOList.get(i));
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
        // 当前可用金币
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
        /** 1.jwt 获取用户user_id **/
        Long userId = jwtService.getUserInfo().getId();
        NewRegisterListVO newRegisterListVO = new NewRegisterListVO();

        /** 2.查询 完善资料 **/
        // 查询新手任务
        List<CoinGameDO> newRegisterTaskList = financeCoinGameMapper
                .selectNewRegisterTask(Constant.NEWREGISTERTASK, GameType.task.getCode());
        Map<String, CoinGameDO> financeCoinGameMap = new HashMap<>();
        newRegisterTaskList.forEach(financeCoinGame -> financeCoinGameMap
                .put(financeCoinGame.getTaskName(), financeCoinGame));
        // 2.1 先查询用户任务表
        CoinGameDO perfectInfoTask = financeCoinGameMap.get(GameTaskType.perfectInfoTask.getCode());
        UserTaskInfoDO userTaskInfoDO = financeUserTaskInfoMapper.selectByUserIdAndTaskId(userId,
                perfectInfoTask.getId());
        if (userTaskInfoDO != null) {
            newRegisterListVO.setStatus(userTaskInfoDO.getFinishStatus());

        } else {
            IdCardInfoDO idCardInfoDO = financeIdCardInfoMapper.selectByUserId(userId);
            if (idCardInfoDO != null && idCardInfoDO.getAuthStatus().intValue() == 1) {
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

        /** 3.提现 **/
        newRegisterListVO = new NewRegisterListVO();
        // 3.1 先查询用户任务表
        CoinGameDO withdrawalTask = financeCoinGameMap.get(GameTaskType.withdrawalTask.getCode());
        UserTaskInfoDO withdrawalUserTaskInfo = financeUserTaskInfoMapper
                .selectByUserIdAndTaskId(userId, withdrawalTask.getId());
        if (withdrawalUserTaskInfo != null) {
            newRegisterListVO.setStatus(withdrawalUserTaskInfo.getFinishStatus());

        } else {
            OrderDO orderDO = new OrderDO();
            orderDO.setUserId(userId);
            orderDO.setTransType(OrderType.debit.getCode());
            Long withdrawCount = financeOrderMapper.selectCountByOrder(orderDO);
            // UserAccountDO financeUserAccount =
            // financeUserAccountMapper.getAccountByUserId(userId);
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
        /** 1.jwt 获取用户user_id **/
        Long userId = jwtService.getUserInfo().getId();
        /** 2.查询用户邀请人数 **/
        List<Long> parentIdList = new ArrayList<>();
        parentIdList.add(userId);
        Long financeUserInviteInfo = financeUserInviteInfoMapper.selectCountByParentId(parentIdList,
                "");
        /** 3.查询成长任务 **/
        List<CoinGameDO> growTaskList = financeCoinGameMapper
                .selectNewRegisterTask(Constant.GROWTASK, GameType.task.getCode());
        Map<String, CoinGameDO> financeCoinGameMap = new HashMap<>();
        growTaskList.forEach(financeCoinGame -> financeCoinGameMap
                .put(financeCoinGame.getTaskName(), financeCoinGame));

        // 3.1 先查询用户任务表
        CoinGameDO invitePersonTask = financeCoinGameMap.get(GameTaskType.invitePerson.getCode());
        UserTaskInfoDO userTaskInfoDO = financeUserTaskInfoMapper.selectByUserIdAndTaskId(userId,
                invitePersonTask.getId());
        if (userTaskInfoDO != null) {
            if (financeUserInviteInfo.intValue() >= userTaskInfoDO.getNextStageNum()) {
                growTaskVO.setStatus(TaskStatus.finishNotReceive.getCode());
            } else {
                growTaskVO.setStatus(userTaskInfoDO.getFinishStatus());
            }

            growTaskVO.setNextNum(userTaskInfoDO.getNextStageNum());

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
     * 功能描述:每日签到
     *
     * @author: moruihai
     * @date: 2018/8/28 16:08
     * @param: []
     * @return: finance.model.finance.ResponseResult<java.lang.Boolean>
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<Boolean> finishEverydaySign() {
        /** 1.jwt 获取用户user_id **/
        Long userId = jwtService.getUserInfo().getId();

        /** 2.活动表里查询每日签到的信息 **/
        CoinGameDO coinGameDO = financeCoinGameMapper.selectByTaskType(Constant.EVERYDAY_CLOCK,
                GameType.task.getCode());
        /** 3.查询今日签到信息 **/
        CoinLogDO todayClockInfo = financeCoinLogMapper.selectYesAndToDatClockByUserId(userId,
                coinGameDO.getId(), "", "Y");
        if (todayClockInfo != null) {
            return ResponseResult.error(CodeEnum.todayAlreadySign);
        }
        CoinLogDO coinLogDO = new CoinLogDO();
        coinLogDO.setUserId(userId);
        coinLogDO.setTaskId(coinGameDO.getId());
        coinLogDO.setTaskName("每日签到");
        coinLogDO.setNum(coinGameDO.getNum());
        financeCoinLogMapper.insertSelective(coinLogDO);
        /** 4.删除redis中缓存的数据 **/
        String everydayClockUserRedisKey = everydayClockUserPrefix + userId;
        stringRedisTemplate.delete(everydayClockUserRedisKey);

        return ResponseResult.success(true);
    }

    /**
     * 功能描述:新手任务
     *
     * @author: moruihai
     * @date: 2018/8/28 16:03
     * @param: [taskId]
     * @return: finance.model.finance.ResponseResult<java.lang.Boolean>
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<Boolean> finishNewRegisterTask(String taskId) {
        /** 1.jwt 获取用户user_id **/
        Long userId = jwtService.getUserInfo().getId();
        Long tasksId = Long.valueOf(taskId);
        // 增加验证，防刷接口
        Boolean isValid = false;
        CoinGameDO coinGameDO = financeCoinGameMapper.selectByPrimaryKey(tasksId);
        logger.info("coinGameDO:" + coinGameDO.toString());
        logger.info("coinGameDO.getTaskName().equals(GameTaskType.withdrawalTask.getCode()):"
                + coinGameDO.getTaskName().equals(GameTaskType.withdrawalTask.getCode()));
        if (GameTaskType.perfectInfoTask.getCode().equals(coinGameDO.getTaskName())) {
            IdCardInfoDO idCardInfoDO = financeIdCardInfoMapper.selectByUserId(userId);
            if (idCardInfoDO != null && idCardInfoDO.getAuthStatus().intValue() == 1) {
                isValid = true;
            }

        } else if (GameTaskType.withdrawalTask.getCode().equals(coinGameDO.getTaskName())) {
            OrderDO orderDO = new OrderDO();
            orderDO.setUserId(userId);
            orderDO.setTransType(OrderType.debit.getCode());
            Long withdrawCount = financeOrderMapper.selectCountByOrder(orderDO);
            if (withdrawCount != null && withdrawCount.longValue() > 0) {
                isValid = true;
            }
        }
        UserTaskInfoDO newRegisterTaskRecord = financeUserTaskInfoMapper
                .selectByUserIdAndTaskId(userId, tasksId);
        if (newRegisterTaskRecord != null) {
            isValid = false;
        }
        if (!isValid) {
            return ResponseResult.error(CodeEnum.invalidOperation);
        }

        /** 2.写人用户任务完成表 **/
        UserTaskInfoDO userTaskInfoDO = new UserTaskInfoDO();
        userTaskInfoDO.setUserId(userId);
        userTaskInfoDO.setTaskId(tasksId);
        userTaskInfoDO.setFinishStatus(TaskStatus.receive.getCode());
        financeUserTaskInfoMapper.insertSelective(userTaskInfoDO);

        /** 3.写入金币记录表 **/
        // 3.1.获取任务信息
        // CoinGameDO coinGameDO =
        // financeCoinGameMapper.selectByPrimaryKey(tasksId);
        CoinLogDO coinLogDO = new CoinLogDO();
        coinLogDO.setUserId(userId);
        coinLogDO.setNum(coinGameDO.getNum());
        coinLogDO.setTaskId(tasksId);
        coinLogDO.setTaskName(GameTaskType.getParam().get(coinGameDO.getTaskName()).getMsg());
        financeCoinLogMapper.insertSelective(coinLogDO);

        return ResponseResult.success(true);
    }

    /**
     * 功能描述:成长任务：邀请好友
     *
     * @author: moruihai
     * @date: 2018/8/28 16:02
     * @param: [taskId]
     * @return: finance.model.finance.ResponseResult<java.lang.Boolean>
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<Boolean> finishGrowTask(String taskId) {
        /** 1.jwt 获取用户user_id **/
        Long userId = jwtService.getUserInfo().getId();
        Long tasksId = Long.valueOf(taskId);
        // 防刷
        Boolean isValid = true;
        /** 2.查询用户邀请人数 **/
        List<Long> parentIdList = new ArrayList<>();
        parentIdList.add(userId);
        Long financeUserInviteInfo = financeUserInviteInfoMapper.selectCountByParentId(parentIdList,
                "");
        UserTaskInfoDO inviteTaskInfo = financeUserTaskInfoMapper.selectByUserIdAndTaskId(userId,
                tasksId);
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

        UserTaskInfoDO userTaskInfoDO = new UserTaskInfoDO();
        userTaskInfoDO.setUserId(userId);
        userTaskInfoDO.setTaskId(tasksId);
        userTaskInfoDO.setFinishStatus(TaskStatus.unfinish.getCode());
        /** 2.写人用户任务完成表 **/
        // 查询邀请好友用户任务
        // UserTaskInfoDO inviteTaskInfo =
        // financeUserTaskInfoMapper.selectByUserIdAndTaskId(userId,tasksId);
        if (inviteTaskInfo != null) {
            userTaskInfoDO.setId(inviteTaskInfo.getId());
            userTaskInfoDO
                    .setNextStageNum(inviteTaskInfo.getNextStageNum() + growTaskInviteStageNum);
            financeUserTaskInfoMapper.updateByPrimaryKeySelective(userTaskInfoDO);
        } else {
            userTaskInfoDO.setNextStageNum(growTaskInviteStageNum * 2);
            financeUserTaskInfoMapper.insertSelective(userTaskInfoDO);
        }

        /** 3.写入金币记录表 **/
        // 3.1.获取任务信息
        CoinGameDO coinGameDO = financeCoinGameMapper.selectByPrimaryKey(tasksId);
        CoinLogDO coinLogDO = new CoinLogDO();
        coinLogDO.setUserId(userId);
        coinLogDO.setNum(coinGameDO.getNum());
        coinLogDO.setTaskId(tasksId);
        coinLogDO.setTaskName(GameTaskType.invitePerson.getMsg());
        financeCoinLogMapper.insertSelective(coinLogDO);

        return ResponseResult.success(true);

    }

    /**
     * 功能描述:获取当月的天数
     *
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
