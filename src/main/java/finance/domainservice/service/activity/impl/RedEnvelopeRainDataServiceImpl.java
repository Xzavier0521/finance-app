package finance.domainservice.service.activity.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import com.google.common.collect.Maps;

import finance.api.model.response.BasicResponse;
import finance.core.common.enums.RedEnvelopeRainTimeCodeEnum;
import finance.core.common.enums.ReturnCode;
import finance.core.common.enums.WeiXinMessageTemplateCodeEnum;
import finance.core.common.util.DateUtils;
import finance.core.common.util.ResponseUtils;
import finance.core.dal.dao.FinanceCoinLogDAO;
import finance.core.dal.dataobject.FinanceCoinLog;
import finance.domain.activity.RedEnvelopeRainData;
import finance.domain.user.ThirdAccountInfo;
import finance.domain.user.UserInfo;
import finance.domain.weixin.WeiXinMessageTemplate;
import finance.domainservice.repository.RedEnvelopeRainDataRepository;
import finance.domainservice.repository.ThirdAccountInfoRepository;
import finance.domainservice.repository.WeiXinMessageTemplateRepository;
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
    private RedEnvelopeRainDataRepository    redEnvelopeRainDataRepository;
    @Resource
    private FinanceCoinLogDAO                financeCoinLogDAO;
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
            RedEnvelopeRainData redEnvelopeRainData = RedEnvelopeRainData.builder()
                .activityCode(activityCode).timeCode(timeCode).userId(userInfo.getId())
                .mobilePhone(userInfo.getMobileNum()).totalNum(totalNum).totalAmount(totalAmount)
                .activityDay(DateUtils.getCurrentDay(LocalDate.now())).build();
            ThirdAccountInfo thirdAccountInfo = thirdAccountInfoRepository
                .queryByCondition(userInfo.getId());
            response = transactionTemplate.execute(status -> {
                redEnvelopeRainDataRepository.save(redEnvelopeRainData);
                recordCoinLog(userInfo.getId(), totalAmount.intValue(), "红包雨活动奖励");
                WeiXinMessageTemplate weiXinMessageTemplate = weiXinMessageTemplateRepository
                    .query(WeiXinMessageTemplateCodeEnum.SEND_COIN_NOTICE.getCode());
                Map<String, String> parameters = Maps.newHashMap();
                parameters.put("coinNum", String.valueOf(totalAmount.longValue()));
                weiXinTemplateMessageSendService.send(userInfo, thirdAccountInfo,
                    weiXinMessageTemplate, parameters);
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

    /**
     *  记录金币日志
     * @param userId 用户id
     * @param coinNum 金币数目
     */
    private void recordCoinLog(Long userId, Integer coinNum, String reason) {
        FinanceCoinLog financeCoinLog = new FinanceCoinLog();
        financeCoinLog.setUserId(userId);
        financeCoinLog.setTaskId(userId);
        financeCoinLog.setTaskName(reason);
        financeCoinLog.setNum(coinNum);
        financeCoinLogDAO.insertSelective(financeCoinLog);
    }
}
