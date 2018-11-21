package finance.domainservice.service.trans.impl;

import java.math.BigDecimal;
import java.util.*;

import javax.annotation.Resource;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;

import finance.api.model.base.Page;
import finance.api.model.response.ResponseResult;
import finance.api.model.vo.FixedAmountInvitedInfoVO;
import finance.api.model.vo.FixedAmountOpenedPacketInfo;
import finance.api.model.vo.FixedAmountPageVO;
import finance.core.common.constants.Constant;
import finance.core.common.enums.ActivityFinishStatus;
import finance.core.common.enums.ActivityType;
import finance.core.common.enums.CodeEnum;
import finance.core.common.util.LogUtil;
import finance.core.dal.dao.*;
import finance.core.dal.dataobject.*;
import finance.domain.dto.FixedAmountPageDto;
import finance.domain.dto.LoginParamDto;
import finance.domainservice.repository.UserInviteRepository;
import finance.domainservice.service.activity.LeaderBoardDataSyncToCacheService;
import finance.domainservice.service.finance.trans.TransBiz;
import finance.domainservice.service.jwt.JwtService;
import finance.domainservice.service.register.RegisterSendMessageService;
import finance.domainservice.service.trans.AccountService;
import finance.domainservice.service.trans.InviteActivityService;

/**
 * 邀请红包活动实现类
 * @author panzhongkang
 * @date 2018/9/10 14:37
 */
@Service
public class InviteActivityServiceImpl implements InviteActivityService {
    private static final Logger                 logger = LoggerFactory
        .getLogger(InviteActivityServiceImpl.class);
    @Resource
    private FinanceUserInviteInfoDAO            inviteInfoMapper;
    @Resource
    private TransBiz                            transBiz;
    @Resource
    private FinanceStepRewardsAmountDAO         stepRewardsAmountMapper;
    @Resource
    private FinanceStepRewardsActivityDAO       stepRewardsActivityMapper;
    @Resource
    private FinanceActivityFixedAmountMainDAO   financeActivityFixedAmountMainMapper;
    @Resource
    private JwtService                          jwtService;
    @Resource
    private FinanceUserInfoDAO                  userInfoMapper;
    @Resource
    private FinanceActivityFixedAmountDetailDAO financeActivityFixedAmountDetailMapper;

    @Resource
    private UserInviteRepository                userInviteRepository;
    @Resource
    private AccountService                      accountService;
    @Value("${activity.fixAmount.switch}")
    private String                              fixAmountSwitch;
    @Value("${activity.fixAmount.RedPacket.min}")
    private double                              fixAmountMinRedPacket;
    @Value("${activity.fixAmount.RedPacket.max}")
    private double                              fixAmountMaxRedPacket;
    @Value("${activity.fixAmount.RedPacket.num}")
    private int                                 fixAmountRedPacketNum;

    /**
     * 阶梯红包开关
     */
    @Value("${step.red.envelope.switch}")
    private String                              stepRedEnvelopeSwitch;
    @Resource
    private RegisterSendMessageService          registerSendMessageService;

    @Resource
    private LeaderBoardDataSyncToCacheService   leaderBoardDataSyncToCacheService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void stepRewards(FinanceUserInfo userInfo, LoginParamDto paramDto) {
        if (!"1".equals(stepRedEnvelopeSwitch)) {
            //阶梯红包活动开关关闭时，结束活动
            logger.info(LogUtil.getFormatLog("step.red.envelope.switch=" + stepRedEnvelopeSwitch,
                "阶梯红包活动开关关闭，结束活动"));
            return;
        }
        // 总奖池扣除对应金额
        // 加锁（初始化id=1）
        FinanceStepRewardsAmount rewardsPoolAmount = stepRewardsAmountMapper
            .selectByPrimaryKeyForUpdate(1L);
        //总奖池低于1500元时不充值（最多一阶奖励金额）
        if (rewardsPoolAmount.getCurrentAmount().compareTo(this.queryEndAmount()) < 0) {
            logger.info(LogUtil.getFormatLog("总奖池金额=" + rewardsPoolAmount.getCurrentAmount(),
                "总奖池金额低于" + this.queryEndAmount() + "元，结束活动"));
            return;
        }

        // 查询是否保存了阶梯红包的邀请关系
        FinanceUserInviteInfo userInviteInfo = inviteInfoMapper.selectOneByUserId(userInfo.getId());
        if (userInviteInfo == null || ActivityType.step_red_envelope == ActivityType
            .getByCode(String.valueOf(userInviteInfo.getInviteType()))) {
            return;
        }
        // 更新邀请人数
        // 加锁
        FinanceStepRewardsActivity stepRewardsActivity = stepRewardsActivityMapper
            .selectOneByUserIdForUpdate(userInviteInfo.getParentUserId());
        if (stepRewardsActivity == null) {
            return;
        }
        FinanceStepRewardsActivity updateStepRewardsActivity = new FinanceStepRewardsActivity();
        updateStepRewardsActivity.setId(stepRewardsActivity.getId());
        updateStepRewardsActivity.setInviteNum(stepRewardsActivity.getInviteNum() + 1);
        stepRewardsActivityMapper.updateByPrimaryKeySelective(updateStepRewardsActivity);

        //
        // 每个用户新增一个二级用户，奖励0.2元
        logger.info("userInviteInfo:{}", userInviteInfo);
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("userId", userInviteInfo.getParentUserId());
        List<FinanceUserInviteInfo> grandInviteInfoList = userInviteRepository
            .queryByCondition(parameters);
        BigDecimal secondInviteAmount = BigDecimal.ZERO;
        if (CollectionUtils.isNotEmpty(grandInviteInfoList)) {
            secondInviteAmount = BigDecimal.valueOf(0.2);
            FinanceUserInviteInfo grandInviteInfo = grandInviteInfoList.get(0);
            logger.info("grandInviteInfo:{}", grandInviteInfo);
            FinanceUserInfo grandUserInfo = userInfoMapper
                .selectByPrimaryKey(grandInviteInfo.getParentUserId());
            accountService.charge("新增二级用户奖励", grandUserInfo.getId(), secondInviteAmount,
                grandUserInfo.getMobileNum());
            // 佣金提醒
            registerSendMessageService.sendRewardMessage(grandUserInfo.getId(), secondInviteAmount);
            //更新总奖池金额
            FinanceStepRewardsAmount updateRewardsPoolAmount = new FinanceStepRewardsAmount();
            updateRewardsPoolAmount.setId(rewardsPoolAmount.getId());
            updateRewardsPoolAmount.setCurrentAmount(
                rewardsPoolAmount.getCurrentAmount().subtract(secondInviteAmount));
            stepRewardsAmountMapper.updateByPrimaryKeySelective(updateRewardsPoolAmount);
            // 更新二级人数排行榜
            leaderBoardDataSyncToCacheService.secondLeaderBoardSync(grandUserInfo.getId());
            // 更新邀请人数总榜
            leaderBoardDataSyncToCacheService.allLeaderBoardSync(grandUserInfo.getId());
        }
        // 判断是否达到该阶段奖励邀请人数,如果达到，计算奖励金额
        BigDecimal rewardAmount = this.queryRewardAmount(updateStepRewardsActivity.getInviteNum());
        if (rewardAmount == null) {
            return;
        }
        // 一级邀请人数排行榜
        leaderBoardDataSyncToCacheService.fistLeaderBoardSync(userInviteInfo.getParentUserId());
        leaderBoardDataSyncToCacheService.allLeaderBoardSync(userInviteInfo.getParentUserId());

        //更新总奖池金额
        FinanceStepRewardsAmount updateRewardsPoolAmount = new FinanceStepRewardsAmount();
        updateRewardsPoolAmount.setId(rewardsPoolAmount.getId());
        updateRewardsPoolAmount.setCurrentAmount(rewardsPoolAmount.getCurrentAmount()
            .subtract(rewardAmount).subtract(secondInviteAmount));
        stepRewardsAmountMapper.updateByPrimaryKeySelective(updateRewardsPoolAmount);

        // 充值奖励金额
        FinanceUserInfo parentUserInfo = userInfoMapper
            .selectByPrimaryKey(userInviteInfo.getParentUserId());
        accountService.charge("阶梯红包奖励", userInviteInfo.getParentUserId(), rewardAmount,
            parentUserInfo.getMobileNum());
        // 佣金提醒
        registerSendMessageService.sendRewardMessage(userInviteInfo.getParentUserId(),
            rewardAmount);

    }

    private BigDecimal queryRewardAmount(Integer inviteNum) {
        LinkedMap linkedMap = Constant.step_reward_map;
        BigDecimal rewardAmount = null;
        Iterator iter = linkedMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (!inviteNum.equals(key)) {
                continue;
            }
            BigDecimal previousValue = linkedMap.previousKey(key) == null ? BigDecimal.valueOf(0)
                : (BigDecimal) linkedMap.get(linkedMap.previousKey(key));
            rewardAmount = ((BigDecimal) value).subtract(previousValue);
        }
        return rewardAmount;
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

    @Override
    public Map<String, Object> getFixedAmountEntry() {
        /**1.jwt 获取用户user_id **/
        Long userId = jwtService.getUserInfo().getId();
        /**2.根据userId 查询活动信息 **/
        Page<FinanceActivityFixedAmountMain> financeActivityFixedAmountMainPage = new Page<FinanceActivityFixedAmountMain>(
            1, 1l);
        FinanceActivityFixedAmountMain financeActivityFixedAmountMain = financeActivityFixedAmountMainMapper
            .selectByUserId(userId, financeActivityFixedAmountMainPage);
        Map<String, Object> returnMap = new HashMap<>();
        //参与状态,1:首次参加,2:参加未完成,3:参加已完成
        if (financeActivityFixedAmountMain == null) {
            returnMap.put("joinStatus", ActivityFinishStatus.firstJoin.getCode());
            returnMap.put("activityId", null);
        } else {
            if (financeActivityFixedAmountMain.getState().intValue() == 0) {
                returnMap.put("joinStatus", ActivityFinishStatus.unfinish.getCode());
                returnMap.put("activityId", financeActivityFixedAmountMain.getId());
            } else if (financeActivityFixedAmountMain.getState().intValue() != 0) {
                returnMap.put("joinStatus", ActivityFinishStatus.finish.getCode());
                returnMap.put("activityId", financeActivityFixedAmountMain.getId());
            }
        }

        return returnMap;
    }

    @Override
    public FixedAmountPageVO queryFixedAmountPageInfo(FixedAmountPageDto fixedAmountPageDto) {

        String pageType = fixedAmountPageDto.getPageType();
        /**1.jwt 获取用户user_id **/
        Long userId = jwtService.getUserInfo().getId();
        FixedAmountPageVO fixedAmountPageVO = null;

        if (pageType.equals("total")) {
            fixedAmountPageVO = getFixedAmountEntryPageInfo(fixedAmountPageDto, userId);
        } else if (pageType.equals("detail")) {
            Long activityId = fixedAmountPageDto.getActivityId();
            fixedAmountPageVO = getUserDetailActivityInfo(userId, activityId);

        } else if (pageType.equals("other")) {
            Long activityId = fixedAmountPageDto.getActivityId();
            fixedAmountPageVO = getUserOtherActivityInfo(userId, activityId);

        }
        return fixedAmountPageVO;
    }

    private FixedAmountPageVO getFixedAmountEntryPageInfo(FixedAmountPageDto fixedAmountPageDto,
                                                          Long userId) {
        int pageSize = fixedAmountPageDto.getPageSize();
        Long pageNum = fixedAmountPageDto.getPageNum();
        Page<FinanceActivityFixedAmountMain> financeActivityFixedAmountMainPage = new Page<>(
            pageSize, pageNum);
        Long total = financeActivityFixedAmountMainMapper.queryJoinFixedAmountActivityCount();
        FixedAmountPageVO fixedAmountPageVO = new FixedAmountPageVO();
        fixedAmountPageVO.setTotalJoinNum(total);
        FinanceUserInfo financeUserInfo = userInfoMapper.selectByPrimaryKey(userId);
        fixedAmountPageVO.setSponsorMobilNum(financeUserInfo.getMobileNum());

        if (total > 0) {
            //根据类型查主表数据
            List<FinanceActivityFixedAmountMain> financeActivityFixedAmountMains = financeActivityFixedAmountMainMapper
                .queryJoinFixedAmountActivityInfo(financeActivityFixedAmountMainPage);
            Map<Long, String> mobilNumMap = new HashMap<>();
            List<Long> userIds = new ArrayList<>();
            financeActivityFixedAmountMains.forEach(financeActivityFixedAmountMain -> userIds
                .add(financeActivityFixedAmountMain.getUserId()));
            List<FinanceUserInfo> userInfoList = userIds.isEmpty() ? new ArrayList<>()
                : userInfoMapper.selectByPrimaryKeys(userIds);
            userInfoList.forEach(fui -> mobilNumMap.put(fui.getId(), fui.getMobileNum()));

            if (financeActivityFixedAmountMains != null
                && financeActivityFixedAmountMains.size() > 0) {
                List<FixedAmountInvitedInfoVO> fixedAmountInvitedInfoVOList = new ArrayList<>();
                FixedAmountInvitedInfoVO fixedAmountInvitedInfoVO = null;
                for (FinanceActivityFixedAmountMain faf : financeActivityFixedAmountMains) {
                    fixedAmountInvitedInfoVO = new FixedAmountInvitedInfoVO();
                    fixedAmountInvitedInfoVO.setMobileNum(mobilNumMap.get(faf.getUserId()));
                    fixedAmountInvitedInfoVO.setInviteNum(faf.getJoinNum());
                    fixedAmountInvitedInfoVO.setGetAmount(faf.getTotalAmount());
                    fixedAmountInvitedInfoVOList.add(fixedAmountInvitedInfoVO);
                }
                fixedAmountPageVO.setInvitedInfoList(fixedAmountInvitedInfoVOList);

            }

        }

        return fixedAmountPageVO;
    }

    private FixedAmountPageVO getUserOtherActivityInfo(Long userId, Long activityId) {
        FinanceActivityFixedAmountMain financeActivityFixedAmountMain = financeActivityFixedAmountMainMapper
            .selectByPrimaryKey(activityId);
        FixedAmountPageVO fixedAmountPageVO = new FixedAmountPageVO();
        if (financeActivityFixedAmountMain == null) {
            return fixedAmountPageVO;
        }

        BigDecimal activityTotalAmount = financeActivityFixedAmountMain.getTotalAmount();
        BigDecimal activitySponsorGetAmount = financeActivityFixedAmountMain.getDividedAmount();
        fixedAmountPageVO.setRedPacketTotalAmount(activityTotalAmount);
        fixedAmountPageVO.setRedPacketSponsorAmount(activitySponsorGetAmount);

        return fixedAmountPageVO;

    }

    private FixedAmountPageVO getUserDetailActivityInfo(Long userId, Long activityId) {
        FinanceActivityFixedAmountMain financeActivityFixedAmountMain = financeActivityFixedAmountMainMapper
            .selectByPrimaryKey(activityId);
        FixedAmountPageVO fixedAmountPageVO = new FixedAmountPageVO();
        if (financeActivityFixedAmountMain == null) {
            return fixedAmountPageVO;
        }

        List<FixedAmountOpenedPacketInfo> fixedAmountOpenedPacketInfoList = new ArrayList<>();
        FixedAmountOpenedPacketInfo fixedAmountOpenedPacketInfo = null;
        List<FinanceActivityFixedAmountDetail> financeActivityFixedAmountDetailList = financeActivityFixedAmountDetailMapper
            .selectByActivityId(activityId);

        List<Long> userIds = new ArrayList<>();
        Map<Long, String> mobilNumMap = new HashMap<>();
        financeActivityFixedAmountDetailList.forEach(fafd -> userIds.add(fafd.getUserId()));
        List<FinanceUserInfo> userInfoList = userIds.isEmpty() ? new ArrayList<>()
            : userInfoMapper.selectByPrimaryKeys(userIds);
        userInfoList.forEach(fui -> mobilNumMap.put(fui.getId(), fui.getMobileNum()));
        BigDecimal activityTotalAmount = financeActivityFixedAmountMain.getTotalAmount();
        BigDecimal activitySponsorGetAmount = financeActivityFixedAmountMain.getDividedAmount();
        BigDecimal activityOtherPeopleDivideAmount = new BigDecimal(0);

        if (financeActivityFixedAmountDetailList != null
            && financeActivityFixedAmountDetailList.size() > 0) {
            for (FinanceActivityFixedAmountDetail fafad : financeActivityFixedAmountDetailList) {
                fixedAmountOpenedPacketInfo = new FixedAmountOpenedPacketInfo();
                fixedAmountOpenedPacketInfo.setMobileNum(mobilNumMap.get(fafad.getUserId()));
                fixedAmountOpenedPacketInfo.setOpenedAmount(fafad.getDividedAmount());
                fixedAmountOpenedPacketInfoList.add(fixedAmountOpenedPacketInfo);

                activityOtherPeopleDivideAmount.add(fafad.getDividedAmount());
            }

        }
        FinanceUserInfo financeUserInfo = userInfoMapper.selectByPrimaryKey(userId);
        fixedAmountPageVO.setSponsorMobilNum(financeUserInfo.getMobileNum());
        fixedAmountPageVO.setOpenedRedPacketList(fixedAmountOpenedPacketInfoList);
        fixedAmountPageVO.setOpenedRedPacketNum(fixedAmountOpenedPacketInfoList.size());
        fixedAmountPageVO.setUnopenedRedPacketNum(5 - fixedAmountOpenedPacketInfoList.size());
        fixedAmountPageVO.setRedPacketTotalAmount(activityTotalAmount);
        fixedAmountPageVO.setRedPacketSponsorAmount(activitySponsorGetAmount);
        fixedAmountPageVO.setRemainingAmount(activityTotalAmount.multiply(activitySponsorGetAmount)
            .multiply(activityOtherPeopleDivideAmount));

        return fixedAmountPageVO;

    }

    @Override
    @Transactional
    public ResponseResult<FixedAmountPageVO> joinFixedAmountActivity() {
        if (!"1".equals(fixAmountSwitch)) {
            return ResponseResult.error(CodeEnum.activityDownLine);
        }
        /**1.jwt 获取用户user_id **/
        Long userId = jwtService.getUserInfo().getId();

        /**2.根据userId 查询活动信息,验证是否存在已有活动 **/
        Page<FinanceActivityFixedAmountMain> financeActivityFixedAmountMainPage = new Page<FinanceActivityFixedAmountMain>(
            1, 1l);
        FinanceActivityFixedAmountMain financeActivityFixedAmountMain = financeActivityFixedAmountMainMapper
            .selectByUserId(userId, financeActivityFixedAmountMainPage);
        if (financeActivityFixedAmountMain != null
            && financeActivityFixedAmountMain.getState().intValue() == 0) {
            return ResponseResult.error(CodeEnum.unfinishFixedAmountActivity);
        }

        /**3. 随机生成一个 5-20 元的红包**/
        BigDecimal redPacketAmount = getRandom(fixAmountMinRedPacket, fixAmountMaxRedPacket);

        /**4.生成6个随机红包 **/
        List<BigDecimal> returnList = divideRedPacket(redPacketAmount, fixAmountRedPacketNum + 1,
            0.01);

        /** 5.插入主表 **/
        BigDecimal sponsorDivideAmount = returnList.get(0);
        FinanceActivityFixedAmountMain FixedAmountMain = new FinanceActivityFixedAmountMain();
        FixedAmountMain.setUserId(userId);
        FixedAmountMain.setTotalAmount(redPacketAmount);
        FixedAmountMain.setState(0l);
        FixedAmountMain.setJoinNum(0);
        FixedAmountMain.setDividedAmount(sponsorDivideAmount);
        financeActivityFixedAmountMainMapper.insertSelective(FixedAmountMain);

        Long activityId = FixedAmountMain.getId();
        FinanceActivityFixedAmountDetail fixedAmountDetail;
        for (int i = 1; i < returnList.size(); i++) {
            fixedAmountDetail = new FinanceActivityFixedAmountDetail();
            fixedAmountDetail.setActivityId(activityId);
            fixedAmountDetail.setDividedAmount(returnList.get(i));
            financeActivityFixedAmountDetailMapper.insertSelective(fixedAmountDetail);
        }
        FixedAmountPageVO fixedAmountPageVO = new FixedAmountPageVO();
        fixedAmountPageVO.setRedPacketSponsorAmount(sponsorDivideAmount);
        fixedAmountPageVO.setRedPacketTotalAmount(redPacketAmount);

        return ResponseResult.success(fixedAmountPageVO);
    }

    @Override
    @Transactional
    public Map<String, Object> handleFixedAmountActivity(FinanceUserInfo userInfo,
                                                         LoginParamDto paramDto) {

        Map<String, Object> returnMap = new HashMap<>();
        //开关判断
        if (!"1".equals(fixAmountSwitch)) {
            returnMap.put("code", CodeEnum.activityDownLine);
            return returnMap;
        }
        // 查询邀请码对应的 UserInfo,
        FinanceUserInfo parentUser = userInfoMapper.selectByInviteCode(paramDto.getInviteCode());

        Page<FinanceActivityFixedAmountMain> financeActivityFixedAmountMainPage = new Page<FinanceActivityFixedAmountMain>(
            1, 1l);
        FinanceActivityFixedAmountMain financeActivityFixedAmountMain = financeActivityFixedAmountMainMapper
            .selectByUserId(parentUser.getId(), financeActivityFixedAmountMainPage);
        if (financeActivityFixedAmountMain == null) {
            returnMap.put("code", CodeEnum.invalidActivityOperation);
            return returnMap;
        } else if (financeActivityFixedAmountMain.getState().intValue() != 0) {
            returnMap.put("code", CodeEnum.activityFinish);
            return returnMap;
        }
        //1.行级锁
        FinanceActivityFixedAmountMain fixedAmountMain = financeActivityFixedAmountMainMapper
            .selectForUpdate(financeActivityFixedAmountMain.getId());
        //更新明细表
        List<FinanceActivityFixedAmountDetail> fixedAmountDetails = financeActivityFixedAmountDetailMapper
            .selectNoUserByActivityId(financeActivityFixedAmountMain.getId());
        FinanceActivityFixedAmountDetail userFixedAmountDetail = fixedAmountDetails.get(0);
        userFixedAmountDetail.setUserId(userInfo.getId());
        financeActivityFixedAmountDetailMapper.updateByPrimaryKeySelective(userFixedAmountDetail);

        int beforUpdate = fixedAmountMain.getJoinNum().intValue();
        FinanceActivityFixedAmountMain insertFixedAmountMain = new FinanceActivityFixedAmountMain();
        insertFixedAmountMain.setId(financeActivityFixedAmountMain.getId());
        if (beforUpdate < 5 && beforUpdate == 4) {
            insertFixedAmountMain.setJoinNum(beforUpdate + 1);
            insertFixedAmountMain.setState(-fixedAmountMain.getId());
            //充值
            accountService.charge("固定金额活动奖励", parentUser.getId(),
                financeActivityFixedAmountMain.getTotalAmount(), parentUser.getMobileNum());

        } else if (beforUpdate < 5) {
            insertFixedAmountMain.setJoinNum(beforUpdate + 1);
        }
        financeActivityFixedAmountMainMapper.updateByPrimaryKeySelective(insertFixedAmountMain);

        returnMap.put("code", CodeEnum.succ);
        returnMap.put("userDivideMoney", userFixedAmountDetail.getDividedAmount());

        return returnMap;
    }

    @Override
    public FixedAmountPageVO queryFixedAmountLoginPageInfo(FixedAmountPageDto fixedAmountPageDto) {
        int pageSize = fixedAmountPageDto.getPageSize();
        Long pageNum = fixedAmountPageDto.getPageNum();
        Page<FinanceActivityFixedAmountMain> financeActivityFixedAmountMainPage = new Page<>(
            pageSize, pageNum);
        Long total = financeActivityFixedAmountMainMapper.queryJoinFixedAmountActivityCount();
        FixedAmountPageVO fixedAmountPageVO = new FixedAmountPageVO();
        fixedAmountPageVO.setTotalJoinNum(total);

        if (total > 0) {
            //根据类型查主表数据
            List<FinanceActivityFixedAmountMain> financeActivityFixedAmountMains = financeActivityFixedAmountMainMapper
                .queryJoinFixedAmountActivityInfo(financeActivityFixedAmountMainPage);
            Map<Long, String> mobilNumMap = new HashMap<>();
            List<Long> userIds = new ArrayList<>();
            financeActivityFixedAmountMains.forEach(financeActivityFixedAmountMain -> userIds
                .add(financeActivityFixedAmountMain.getUserId()));
            List<FinanceUserInfo> userInfoList = userIds.isEmpty() ? new ArrayList<>()
                : userInfoMapper.selectByPrimaryKeys(userIds);
            userInfoList.forEach(fui -> mobilNumMap.put(fui.getId(), fui.getMobileNum()));

            if (financeActivityFixedAmountMains != null
                && financeActivityFixedAmountMains.size() > 0) {
                List<FixedAmountInvitedInfoVO> fixedAmountInvitedInfoVOList = new ArrayList<>();
                FixedAmountInvitedInfoVO fixedAmountInvitedInfoVO = null;
                for (FinanceActivityFixedAmountMain faf : financeActivityFixedAmountMains) {
                    fixedAmountInvitedInfoVO = new FixedAmountInvitedInfoVO();
                    fixedAmountInvitedInfoVO.setMobileNum(mobilNumMap.get(faf.getUserId()));
                    fixedAmountInvitedInfoVO.setInviteNum(faf.getJoinNum());
                    fixedAmountInvitedInfoVO.setGetAmount(faf.getTotalAmount());
                    fixedAmountInvitedInfoVOList.add(fixedAmountInvitedInfoVO);
                }
                fixedAmountPageVO.setInvitedInfoList(fixedAmountInvitedInfoVOList);

            }

        }

        return fixedAmountPageVO;
    }

    /**
     * 计算两个数之间的随机值，结果保留两位小数
     * @param begin
     * @param end
     * @return
     */
    private BigDecimal getRandom(double begin, double end) {
        double random = Math.random();
        double amount = random * (end - begin) + begin;
        BigDecimal bg = new BigDecimal(amount);
        return bg.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
      *功能描述:拆分红包
      * @author: moruihai
      * @date: 2018/9/11 17:15
      * @param: [total, num]
      * @return: java.util.List<java.math.BigDecimal>
      */
    private List<BigDecimal> divideRedPacket(BigDecimal total, int num, double minNum) {
        if (total.longValue() < 0) {
            return null;
        }
        Random r = new Random();
        List<Integer> randomList = new ArrayList<Integer>();
        Integer randomTotal = new Integer(0);
        while (randomList.size() < num) {
            Integer i = r.nextInt(num * 5);
            if (!randomList.contains(i)) {
                randomList.add(i);
                randomTotal = randomTotal + i;
            }
        }
        List<BigDecimal> returnList = new ArrayList<BigDecimal>();
        BigDecimal returnTotal = new BigDecimal(0);
        for (int j = 1; j < randomList.size(); j++) {
            double b = (((double) randomList.get(j) / randomTotal) * total.longValue());
            BigDecimal bb = new BigDecimal(b).setScale(2, BigDecimal.ROUND_HALF_UP);
            returnList.add(new BigDecimal(b).setScale(2, BigDecimal.ROUND_HALF_UP));
            returnTotal = returnTotal.add(bb);
        }
        returnList.add(total.subtract(returnTotal));

        return returnList;
    }
}
