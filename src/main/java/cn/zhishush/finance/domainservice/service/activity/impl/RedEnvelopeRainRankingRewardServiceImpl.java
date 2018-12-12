package cn.zhishush.finance.domainservice.service.activity.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import cn.zhishush.finance.domainservice.repository.activity.RedEnvelopeRainDataRepository;
import cn.zhishush.finance.domainservice.repository.activity.RedEnvelopeRainRewardRepository;
import cn.zhishush.finance.domainservice.repository.coin.CoinLogRepository;
import cn.zhishush.finance.domainservice.repository.third.ThirdAccountInfoRepository;
import cn.zhishush.finance.domainservice.repository.user.UserInfoRepository;
import cn.zhishush.finance.domainservice.repository.weixin.WeiXinMessageTemplateRepository;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import com.google.common.collect.Maps;

import cn.zhishush.finance.core.common.enums.RedEnvelopeRainTimeCodeEnum;
import cn.zhishush.finance.core.common.enums.RewardTypeEnum;
import cn.zhishush.finance.core.common.enums.WeiXinMessageTemplateCodeEnum;
import cn.zhishush.finance.core.common.util.DateUtils;
import cn.zhishush.finance.domain.activity.RedEnvelopeRainData;
import cn.zhishush.finance.domain.activity.RedEnvelopeRainReward;
import cn.zhishush.finance.domain.user.ThirdAccountInfo;
import cn.zhishush.finance.domain.user.UserInfo;
import cn.zhishush.finance.domain.weixin.WeiXinMessageTemplate;
import cn.zhishush.finance.domainservice.service.activity.RedEnvelopeRainRankingRewardService;
import cn.zhishush.finance.domainservice.service.wechat.WeiXinTemplateMessageSendService;

/**
 * <p>红包雨活动-排行榜奖励</p>
 * @author lili
 * @version $Id: RedEnvelopeRainRankingRewardServiceImpl.java, v0.1 2018/11/26 9:30 AM lili Exp $
 */
@Slf4j
@Service("redEnvelopeRainRankingRewardService")
public class RedEnvelopeRainRankingRewardServiceImpl implements
                                                     RedEnvelopeRainRankingRewardService {

    @Resource
    private TransactionTemplate              transactionTemplate;
    @Resource
    private CoinLogRepository coinLogRepository;
    @Resource
    private RedEnvelopeRainRewardRepository redEnvelopeRainRewardRepository;
    @Resource
    private RedEnvelopeRainDataRepository redEnvelopeRainDataRepository;
    @Resource
    private ThirdAccountInfoRepository thirdAccountInfoRepository;
    @Resource
    private UserInfoRepository userInfoRepository;
    @Resource
    private WeiXinMessageTemplateRepository weiXinMessageTemplateRepository;
    @Resource
    private WeiXinTemplateMessageSendService weiXinTemplateMessageSendService;

    @Override
    public void process(LocalDate localDate, String activityCode,
                        RedEnvelopeRainTimeCodeEnum timeCode) {
        List<RedEnvelopeRainData> redEnvelopeRainDataList = redEnvelopeRainDataRepository
            .queryRankingList(activityCode, DateUtils.getCurrentDay(localDate), timeCode, 20, 1);
        if (CollectionUtils.isEmpty(redEnvelopeRainDataList)) {
            return;
        }
        // 记录最终的排行榜数据
        UserInfo userInfo;
        for (RedEnvelopeRainData redEnvelopeRainData : redEnvelopeRainDataList) {
            log.info("[开始处理用户{}排行榜奖励]", redEnvelopeRainData.getMobilePhone());
            userInfo = userInfoRepository.queryByMobileNum(redEnvelopeRainData.getMobilePhone());
            if (Objects.nonNull(userInfo)) {
                String activityDay = String.valueOf(DateUtils.getCurrentDay(localDate));
                // 记录发放日志
                RedEnvelopeRainReward redEnvelopeRainReward = redEnvelopeRainRewardRepository
                    .queryByCondition(activityCode, activityDay, timeCode, userInfo.getId(),
                        RewardTypeEnum.RED_ENVELOPE_RAIN_RANKING);
                if (Objects.nonNull(redEnvelopeRainReward)) {
                    log.info("用户:{},红包雨排行榜奖励已经发放！", userInfo.getMobileNum());
                    continue;
                }
                localData(activityCode, activityDay, timeCode, userInfo, redEnvelopeRainData);
                // 金币提醒
                // 查询用户openInfo
                ThirdAccountInfo thirdAccountInfo = thirdAccountInfoRepository
                    .queryByCondition(userInfo.getId());
                // 发送微信模版消息
                if (Objects.nonNull(thirdAccountInfo)
                    && StringUtils.isNotBlank(thirdAccountInfo.getOpenId())) {
                    // 查询微信消息模版
                    WeiXinMessageTemplate weiXinMessageTemplate = weiXinMessageTemplateRepository
                        .query(WeiXinMessageTemplateCodeEnum.SEND_COIN_NOTICE.getCode());
                    // 组装模版消息发送参数
                    Map<String, String> parameters = Maps.newHashMap();
                    parameters.put("coinNum",
                        String.valueOf(redEnvelopeRainData.getTotalAmount().longValue()));
                    // 发送微信模版消息
                    weiXinTemplateMessageSendService.send(userInfo, thirdAccountInfo,
                        weiXinMessageTemplate, parameters);
                } else {
                    log.info("用户{}，未获取到open_id不发送微信模版消息", redEnvelopeRainData.getMobilePhone());
                }

            }
            log.info("[结束处理用户{}排行榜奖励]", redEnvelopeRainData.getMobilePhone());
        }
    }

    private void localData(String activityCode, String activityDay,
                           RedEnvelopeRainTimeCodeEnum timeCode, UserInfo userInfo,
                           RedEnvelopeRainData redEnvelopeRainData) {
        transactionTemplate.execute(status -> {
            // 增加金币
            coinLogRepository.save(userInfo.getId(),
                redEnvelopeRainData.getTotalAmount().intValue(), "红包雨活动排行榜奖励");
            // 记录金币奖励日志
            redEnvelopeRainRewardRepository.save(RedEnvelopeRainReward.builder()
                .userId(userInfo.getId()).mobilePhone(userInfo.getMobileNum())
                .activityCode(activityCode).timeCode(timeCode).activityDay(activityDay)
                .totalNum(redEnvelopeRainData.getTotalNum())
                .totalAmount(redEnvelopeRainData.getTotalAmount().longValue())
                .rewardType(RewardTypeEnum.RED_ENVELOPE_RAIN_RANKING).build());
            return true;
        });
    }
}
