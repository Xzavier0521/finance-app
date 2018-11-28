package finance.domainservice.service.creditcard.impl;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import finance.api.model.vo.cashback.CashBackConfigTableVO;
import finance.api.model.vo.common.BodyVO;
import finance.api.model.vo.creditCard.CreditCardDetailVO;
import finance.domain.cashbak.CashBackConfig;
import finance.domain.creditcard.CreditCardDetails;
import finance.domain.creditcard.CreditCardInfo;
import finance.domainservice.repository.CashBackConfigRepository;
import finance.domainservice.repository.CreditCardDetailsRepository;
import finance.domainservice.repository.CreditCardInfoRepository;
import finance.domainservice.service.creditcard.CreditCardQueryService;

/**
 * <p>信用卡明细查询</p>
 *
 * @author lili
 * @version 1.0: CreditCardQueryServiceImpl.java, v0.1 2018/11/29 1:43 AM PM lili Exp $
 */
@Slf4j
@Service("creditCardQueryService")
public class CreditCardQueryServiceImpl implements CreditCardQueryService {

    @Resource
    private CashBackConfigRepository    cashBackConfigRepository;
    @Resource
    private CreditCardInfoRepository    creditCardInfoRepository;

    @Resource
    private CreditCardDetailsRepository creditCardDetailsRepository;

    @Override
    public CreditCardDetailVO queryCreditCardDetail(String cardCode) {
        CreditCardDetailVO creditCardDetailVO = new CreditCardDetailVO();
        CreditCardInfo creditCardInfo = creditCardInfoRepository.query(cardCode);
        if (Objects.nonNull(creditCardInfo)) {
            creditCardDetailVO.setBankCode(creditCardInfo.getBankCode());
            creditCardDetailVO.setCardCode(creditCardInfo.getCardCode());
            creditCardDetailVO.setCardName(creditCardInfo.getCardName());
        }
        CreditCardDetails creditCardDetails = creditCardDetailsRepository.query(cardCode);
        if (Objects.nonNull(creditCardDetails)) {
            creditCardDetailVO.setCardBannerUrl(creditCardDetails.getCardBannerUrl());
            creditCardDetailVO.setRedirectUrl(creditCardDetails.getRedirectUrl());
            creditCardDetailVO.setShareImgUrl(creditCardDetails.getShareImgUrl());
            creditCardDetailVO.setCardDetailDesc(creditCardDetails.getCardDetailDesc());
            creditCardDetailVO.setCardDetailTag(creditCardDetails.getCardDetailTag());
            //
            CashBackConfig cashBackConfig = cashBackConfigRepository
                .query(creditCardDetails.getCashbackConfigId());
            if (Objects.nonNull(cashBackConfig)) {
                CashBackConfigTableVO cashBackConfigTableVO = new CashBackConfigTableVO();
                cashBackConfigTableVO.setTitle("角色|办卡人|直接推广者|间接推广者");
                String body = MessageFormat.format("返现金额|{0}|{1}|{2}|{3}",
                    cashBackConfig.getTerminalBonus(), cashBackConfig.getDirectBonus(),
                    cashBackConfig.getIndirectBonus());
                cashBackConfigTableVO.setBody(Lists.newArrayList(body));
                creditCardDetailVO.setCashBackConfigTable(cashBackConfigTableVO);
            }
            //
            List<BodyVO> bodyList = Lists.newArrayList();
            // 办卡福利
            BodyVO cardWelfare = BodyVO.builder().order(1).title("办卡福利").type("text")
                .value(creditCardDetails.getCardWelfare()).build();
            bodyList.add(cardWelfare);
            // 办卡条件
            BodyVO cardConditions = BodyVO.builder().order(2).title("办卡条件").type("text")
                .value(creditCardDetails.getCardConditions()).build();
            bodyList.add(cardConditions);
            // 基本信息
            BodyVO cardBasicInfo = BodyVO.builder().order(3).title("基本信息").type("text")
                .value(creditCardDetails.getCardBasicInfo()).build();
            bodyList.add(cardBasicInfo);
            // 办卡流程
            BodyVO cardProcess = BodyVO.builder().order(4).title("办卡流程").type("img")
                .value(creditCardDetails.getCardProcessUrl()).build();
            bodyList.add(cardProcess);
            creditCardDetailVO.setBodyList(bodyList);
        }
        return creditCardDetailVO;
    }
}
