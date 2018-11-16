package finance.domainservice.service.businessinformation.impl;

import java.math.BigDecimal;
import java.util.*;

import javax.annotation.Resource;

import org.apache.commons.collections.map.LinkedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import finance.api.model.base.Page;
import finance.api.model.response.ResponseResult;
import finance.api.model.vo.ActivityListVO;
import finance.api.model.vo.StepRewardsDetailVo;
import finance.api.model.vo.StepRewardsJoinDetailVo;
import finance.core.common.constant.Constant;
import finance.core.common.enums.ActivityType;
import finance.core.common.enums.CodeEnum;
import finance.core.common.enums.GameType;
import finance.core.common.util.LogUtil;
import finance.domainservice.service.businessinformation.ActivityBiz;
import finance.core.dal.dao.*;
import finance.core.dal.dataobject.FinanceCoinGame;
import finance.core.dal.dataobject.FinanceStepRewardsActivity;
import finance.core.dal.dataobject.FinanceStepRewardsAmount;
import finance.core.dal.dataobject.FinanceUserInfo;

;

/**
 * @program: finance-server
 *
 * @description:
 *
 * @author: MORUIHAI
 *
 * @create: 2018-09-03 14:45
 **/
@Service
public class ActivityBizImpl implements ActivityBiz {
    private static final Logger           logger = LoggerFactory.getLogger(ActivityBizImpl.class);

    @Resource
    private FinanceCoinGameDAO            financeCoinGameMapper;
    @Resource
    private FinanceStepRewardsAmountDAO   amountMapper;
    @Resource
    private FinanceStepRewardsActivityDAO activityMapper;
    @Resource
    private FinanceUserInfoDAO            userInfoMapper;
    @Resource
    private FinanceUserInviteInfoDAO      inviteInfoMapper;

    //阶梯红包开关
    @Value("${step.red.envelope.switch}")
    private String                        stepRedEnvelopeSwitch;

    @Override
    public Map<String, List<ActivityListVO>> getActivityList() {
        Map<String, List<ActivityListVO>> returnMap = new HashMap<>();
        List<ActivityListVO> activityListVOList = new ArrayList<>();

        List<FinanceCoinGame> financeCoinGameList = financeCoinGameMapper
            .selectByGameType(Constant.COMMON_ACTIVITY, GameType.activity.getCode());
        ActivityListVO activityListVO = null;
        for (FinanceCoinGame financeCoinGame : financeCoinGameList) {

            activityListVO = new ActivityListVO();
            BeanUtils.copyProperties(financeCoinGame, activityListVO);
            activityListVOList.add(activityListVO);

        }
        returnMap.put("activityList", activityListVOList);
        return returnMap;
    }

    @Override
    public StepRewardsDetailVo queryStepRewardsInfo(Long userId, Page<StepRewardsDetailVo> page) {
        StepRewardsDetailVo stepRewardsDetailVo = new StepRewardsDetailVo();
        String isEnd = "0";

        if (!"1".equals(stepRedEnvelopeSwitch)) {
            //阶梯红包活动开关关闭时，结束活动
            isEnd = "1";
            logger.info(LogUtil.getFormatLog("step.red.envelope.switch=" + stepRedEnvelopeSwitch,
                "阶梯红包活动开关关闭，结束活动"));
        }

        //奖池金额(初始化id=1)
        FinanceStepRewardsAmount rewardsAmount = amountMapper.selectByPrimaryKey(1L);
        BigDecimal currentAmount = null;
        if (rewardsAmount != null) {
            currentAmount = rewardsAmount.getCurrentAmount();
            if (currentAmount.compareTo(this.queryEndAmount()) < 0) {
                isEnd = "1";
                currentAmount = BigDecimal.valueOf(0);
            }
        }

        //参加用户列表
        List<StepRewardsJoinDetailVo> joinDetailVoList = new ArrayList<>();
        //邀请用户列表
        List<String> inviteList = new ArrayList<>();

        //查询用户的红包信息
        FinanceStepRewardsActivity stepRewardsActivity = null;
        Integer inviteNum = 0;
        if (userId != null) {
            stepRewardsActivity = stepRewardsActivity = activityMapper.selectOneByUserId(userId);
        }
        if (stepRewardsActivity == null) {
            stepRewardsDetailVo.setIsNew("1");
            //查询参加用户列表
            List<FinanceStepRewardsActivity> rewardsActivityList = activityMapper
                .selectStepRewardList(page);
            int joinNum = activityMapper.selectStepRewardsCount().intValue();
            if (rewardsActivityList != null && !rewardsActivityList.isEmpty()) {
                //批量查询手机号
                List<Long> joinUserIdList = new ArrayList<>();
                rewardsActivityList
                    .forEach(rewardsActivity -> joinUserIdList.add(rewardsActivity.getUserId()));
                List<FinanceUserInfo> userInfos = userInfoMapper
                    .selectByPrimaryKeys(joinUserIdList);
                Map<Long, String> userInfoMap = new HashMap<>();
                userInfos.forEach(userInfo -> userInfoMap
                    .put(userInfo.getId(), userInfo.getMobileNum().substring(0, 3) + "****"
                                           + userInfo.getMobileNum().substring(7)));
                //组装参加用户列表信息
                for (FinanceStepRewardsActivity rewardsActivity : rewardsActivityList) {
                    StepRewardsJoinDetailVo stepRewardsJoinDetailVo = new StepRewardsJoinDetailVo();
                    stepRewardsJoinDetailVo
                        .setMobileNum(userInfoMap.get(rewardsActivity.getUserId()));
                    stepRewardsJoinDetailVo.setInviteNum(rewardsActivity.getInviteNum());
                    stepRewardsJoinDetailVo.setRewardAmount(this
                        .queryStepReward(new StepRewardsDetailVo(), rewardsActivity.getInviteNum())
                        .getHaveAmount());
                    joinDetailVoList.add(stepRewardsJoinDetailVo);
                }
            }
            stepRewardsDetailVo.setJoinNum(joinNum);
        } else {
            stepRewardsDetailVo.setIsNew("0");
            stepRewardsDetailVo.setInviteNum(stepRewardsActivity.getInviteNum());
            stepRewardsDetailVo = this.queryStepReward(stepRewardsDetailVo,
                stepRewardsActivity.getInviteNum());
            //根据红包类型查询邀请用户列表
            List<Long> userIdList = inviteInfoMapper.selectListByType(userId,
                Integer.valueOf(ActivityType.step_red_envelope.getCode()), page);
            List<FinanceUserInfo> userInfos = userIdList.isEmpty() ? new ArrayList<>()
                : userInfoMapper.selectByPrimaryKeys(userIdList);
            Map<Long, String> userInfoMap = new HashMap<>();
            userInfos.forEach(userInfo -> userInfoMap
                .put(userInfo.getId(), userInfo.getMobileNum().substring(0, 3) + "****"
                                       + userInfo.getMobileNum().substring(7)));
            userIdList.forEach(sunUserId -> inviteList.add(userInfoMap.get(sunUserId)));
        }
        stepRewardsDetailVo.setIsEnd(isEnd);
        stepRewardsDetailVo.setPoolAmount(currentAmount);
        stepRewardsDetailVo.setJoinList(joinDetailVoList);
        stepRewardsDetailVo.setInviteList(inviteList);

        return stepRewardsDetailVo;
    }

    @Override
    public ResponseResult<Boolean> saveStepRewardsActivty(Long userId) {
        if (!"1".equals(stepRedEnvelopeSwitch)) {
            //阶梯红包活动开关关闭时，结束活动
            logger.info(LogUtil.getFormatLog("step.red.envelope.switch=" + stepRedEnvelopeSwitch,
                "阶梯红包活动开关关闭，结束活动"));
            return ResponseResult.error(CodeEnum.stepRedEnvelopeEnd);
        }

        //查询奖池金额(初始化id=1)
        FinanceStepRewardsAmount rewardsAmount = amountMapper.selectByPrimaryKey(1L);
        BigDecimal currentAmount = null;
        if (rewardsAmount == null) {
            return ResponseResult.error(CodeEnum.dbException);
        }
        //奖池金额低于1500不可再参加活动(根据配置)
        if (rewardsAmount.getCurrentAmount().compareTo(this.queryEndAmount()) < 0) {
            return ResponseResult.error(CodeEnum.stepRedEnvelopeEnd);
        }

        FinanceStepRewardsActivity existStepRewardsActivity = activityMapper
            .selectOneByUserId(userId);
        if (existStepRewardsActivity != null) {
            return ResponseResult.error(CodeEnum.stepRedEnvelopeExist);
        }
        //保存该用户的红包记录
        FinanceStepRewardsActivity stepRewardsActivity = new FinanceStepRewardsActivity();
        stepRewardsActivity.setUserId(userId);
        activityMapper.insertSelective(stepRewardsActivity);
        return ResponseResult.success(true);
    }

    @Override
    public List<Map<String, String>> queryStepRewardRefList() {
        LinkedMap linkedMap = Constant.step_reward_map;
        Iterator iter = linkedMap.entrySet().iterator();
        List<Map<String, String>> ferenceList = new ArrayList<>();

        int i = 0;
        while (iter.hasNext()) {
            Map<String, String> resultMap = new HashMap<>();
            Map.Entry entry = (Map.Entry) iter.next();
            Object value = entry.getValue();
            resultMap.put("stepNum", "第" + ++i + "等级");
            resultMap.put("inviteNum", String.valueOf(entry.getKey()));
            resultMap.put("totalAmount", String.valueOf(entry.getValue()));
            ferenceList.add(resultMap);
        }
        return ferenceList;
    }

    private StepRewardsDetailVo queryStepReward(StepRewardsDetailVo stepRewardsDetailVo,
                                                Integer inviteNum) {
        LinkedMap linkedMap = Constant.step_reward_map;
        int stepNo = 0;
        if (inviteNum >= (Integer) linkedMap.lastKey()) {
            stepNo = linkedMap.size();
            stepRewardsDetailVo.setThisStepNum((Integer) linkedMap.lastKey());
            BigDecimal lastValue = (BigDecimal) linkedMap.get(linkedMap.lastKey());
            BigDecimal penultValue = (BigDecimal) linkedMap
                .get(linkedMap.previousKey(linkedMap.lastKey()));
            stepRewardsDetailVo.setThisStepMoney(lastValue.subtract(penultValue));
            stepRewardsDetailVo.setHaveAmount(lastValue);
        } else {
            Iterator iter = linkedMap.entrySet().iterator();
            while (iter.hasNext()) {
                stepNo++;
                Map.Entry entry = (Map.Entry) iter.next();
                Object key = entry.getKey();
                Object value = entry.getValue();
                if (inviteNum >= (Integer) key) {
                    continue;
                }
                stepRewardsDetailVo.setThisStepNum((Integer) key);
                BigDecimal previousValue = linkedMap.previousKey(key) == null ? new BigDecimal(0)
                    : (BigDecimal) linkedMap.get(linkedMap.previousKey(key));
                stepRewardsDetailVo.setThisStepMoney(((BigDecimal) value));
                Object nextKey = linkedMap.nextKey(key);
                if (nextKey != null) {
                    stepRewardsDetailVo.setNextStepNum((Integer) nextKey);
                    BigDecimal nextValue = (BigDecimal) linkedMap.get(nextKey);
                    stepRewardsDetailVo.setNextStepMoney(nextValue);
                    Object nextTwoKey = linkedMap.nextKey(nextKey);
                    if (nextTwoKey != null) {
                        stepRewardsDetailVo.setNextTwoStepNum((Integer) nextTwoKey);
                        BigDecimal nextTwoValue = (BigDecimal) linkedMap.get(nextTwoKey);
                        stepRewardsDetailVo.setNextTwoStepMoney(nextTwoValue);
                    }
                }
                stepRewardsDetailVo.setHaveAmount(previousValue);
                break;
            }
        }
        stepRewardsDetailVo.setStepNo(stepNo);
        return stepRewardsDetailVo;
    }

    private BigDecimal queryEndAmount() {
        LinkedMap linkedMap = Constant.step_reward_map;
        BigDecimal endAmount = BigDecimal.ZERO;
        BigDecimal lastValue = (BigDecimal) linkedMap.get(linkedMap.lastKey());
        BigDecimal penultValue = (BigDecimal) linkedMap
            .get(linkedMap.previousKey(linkedMap.lastKey()));
        endAmount = lastValue.subtract(penultValue);
        return endAmount;
    }
}
