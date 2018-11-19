package finance.domainservice.service.activity.impl;

import static finance.core.common.constants.RedEnvelopConstant.RED_ENVELOPE_RAIN_PHONE_NUMBERS;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import com.google.common.collect.Maps;

import finance.api.model.response.BasicResponse;
import finance.core.common.enums.RedEnvelopeRainTimeCodeEnum;
import finance.core.common.enums.ReturnCode;
import finance.core.common.enums.RewardTypeEnum;
import finance.core.common.enums.WeiXinMessageTemplateCodeEnum;
import finance.core.common.util.DateUtils;
import finance.core.common.util.ResponseUtils;
import finance.domain.activity.RedEnvelopeRainData;
import finance.domain.activity.RedEnvelopeRainReward;
import finance.domain.user.ThirdAccountInfo;
import finance.domain.user.UserInfo;
import finance.domain.weixin.WeiXinMessageTemplate;
import finance.domainservice.repository.*;
import finance.domainservice.service.activity.RedEnvelopeRainDataService;
import finance.domainservice.service.wechat.WeiXinTemplateMessageSendService;

/**
 * <p>红包雨活动数据</p>
 *
 * @author lili
 * @version 1.0: RedEnvelopeRainDataServiceImpl.java, v0.1 2018/11/14 10:15 PM PM lili Exp $
 */
@Slf4j
@Service("redEnvelopeRainDataService")
public class RedEnvelopeRainDataServiceImpl implements RedEnvelopeRainDataService {
    @Resource
    private RedisTemplate<String, Object>    redisTemplate;
    @Resource
    private RedEnvelopeRainRewardRepository  redEnvelopeRainRewardRepository;
    @Resource
    private RedEnvelopeRainDataRepository    redEnvelopeRainDataRepository;
    @Resource
    private CoinLogRepository                coinLogRepository;
    @Resource
    private ThirdAccountInfoRepository       thirdAccountInfoRepository;
    @Resource
    private TransactionTemplate              transactionTemplate;
    @Resource
    private WeiXinMessageTemplateRepository  weiXinMessageTemplateRepository;
    @Resource
    private WeiXinTemplateMessageSendService weiXinTemplateMessageSendService;

    /**
     * 保存红包雨活动数据
     *
     * @param userInfo    用户信息
     * @param timeCode    时间编码
     * @param totalNum    总红包数
     * @param totalAmount 总金额/金币
     */
    @Override
    public BasicResponse localData(UserInfo userInfo, String activityCode,
                                   RedEnvelopeRainTimeCodeEnum timeCode, Long totalNum,
                                   BigDecimal totalAmount) {
        BasicResponse response = new BasicResponse();
        try {
            Integer activityDay = DateUtils.getCurrentDay(LocalDate.now());
            RedEnvelopeRainData redEnvelopeRainData = RedEnvelopeRainData.builder()
                .activityCode(activityCode).timeCode(timeCode).userId(userInfo.getId())
                .mobilePhone(userInfo.getMobileNum()).totalNum(totalNum).totalAmount(totalAmount)
                .activityDay(activityDay).build();
            ThirdAccountInfo thirdAccountInfo = thirdAccountInfoRepository
                .queryByCondition(userInfo.getId());
            // 启用事务处理
            response = transactionTemplate.execute(status -> {
                // 记录红包雨数据
                redEnvelopeRainDataRepository.save(redEnvelopeRainData);
                // 发放金币奖励
                coinLogRepository.save(userInfo.getId(), totalAmount.intValue(), "红包雨活动奖励");
                // 记录金币奖励日志
                redEnvelopeRainRewardRepository.save(RedEnvelopeRainReward.builder()
                    .userId(userInfo.getId()).mobilePhone(userInfo.getMobileNum())
                    .activityCode(activityCode).activityDay(String.valueOf(activityDay))
                    .totalNum(totalNum).totalAmount(totalAmount.longValue())
                    .rewardType(RewardTypeEnum.RED_ENVELOPE_RAIN).build());
                // 发送微信模版消息
                if (Objects.nonNull(thirdAccountInfo)
                    && StringUtils.isNotBlank(thirdAccountInfo.getOpenId())) {
                    // 查询微信消息模版
                    WeiXinMessageTemplate weiXinMessageTemplate = weiXinMessageTemplateRepository
                        .query(WeiXinMessageTemplateCodeEnum.SEND_COIN_NOTICE.getCode());
                    Map<String, String> parameters = Maps.newHashMap();
                    parameters.put("coinNum", String.valueOf(totalAmount.longValue()));
                    weiXinTemplateMessageSendService.send(userInfo, thirdAccountInfo,
                        weiXinMessageTemplate, parameters);
                } else {
                    log.info("用户{}，未获取到open_id不发送微信模版消息", userInfo.getMobileNum());
                }
                // 参加活动的手机号码列表
                String key = MessageFormat.format("{0}:{1}:{}", RED_ENVELOPE_RAIN_PHONE_NUMBERS,
                    String.valueOf(activityDay), timeCode.getCode());
                redisTemplate.opsForSet().add(key, userInfo.getMobileNum());
                // 有效时间1天
                redisTemplate.expire(key, 1440, TimeUnit.MINUTES);
                return ResponseUtils.buildResp(ReturnCode.SUCCESS);
            });
        } catch (DuplicateKeyException e) {
            ResponseUtils.buildResp(response, ReturnCode.ACTIVITY_HAS_JOIN);
            log.error("用户:{}已经参加红包雨活动", userInfo.getMobileNum());
        } catch (Exception ex) {
            ResponseUtils.buildResp(response, ReturnCode.SYS_ERROR);
            log.error("[保存红包雨活动数据]，异常:{}", ExceptionUtils.getStackTrace(ex));
        }
        return response;
    }

}
