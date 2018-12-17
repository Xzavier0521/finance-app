package cn.zhishush.finance.domainservice.service.cashback.impl;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.vo.cashback.CashBackConfigRuleVO;
import cn.zhishush.finance.core.common.enums.CashBackTypeEnum;
import cn.zhishush.finance.core.common.enums.ProductTypeEnum;
import cn.zhishush.finance.core.common.enums.ReturnCode;
import cn.zhishush.finance.core.common.util.PreconditionUtils;
import cn.zhishush.finance.domain.cashbak.CashBackConfig;
import cn.zhishush.finance.domain.creditcard.CreditCardDetails;
import cn.zhishush.finance.domain.loan.LoanDetails;
import cn.zhishush.finance.domainservice.repository.third.impl.product.CashBackConfigRepository;
import cn.zhishush.finance.domainservice.repository.third.impl.product.CreditCardDetailsRepository;
import cn.zhishush.finance.domainservice.repository.third.impl.product.LoanDetailsRepository;
import cn.zhishush.finance.domainservice.service.cashback.CashBackConfigQueryService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>返现规则查询</p>
 *
 * @author lili
 * @version 1.0: CashBackConfigQueryServiceImpl.java, v0.1 2018/12/14 5:42 PM PM lili Exp $
 */
@Service("cashBackConfigQueryService")
public class CashBackConfigQueryServiceImpl implements CashBackConfigQueryService {

    @Resource
    private CreditCardDetailsRepository creditCardDetailsRepository;

    @Resource
    private LoanDetailsRepository loanDetailsRepository;

    @Resource
    private CashBackConfigRepository cashBackConfigRepository;

    @Override
    public Page<CashBackConfigRuleVO> queryCashBackRule(ProductTypeEnum productType) {
        Page<CashBackConfigRuleVO> page = new Page<>(10000, 1L);
        List<CashBackConfigRuleVO> cashBackConfigRuleVOList = Lists.newArrayList();
        List<CashBackConfig> cashBackConfigList;
        CashBackConfig cashBackConfig;
        CashBackConfigRuleVO cashBackConfigRuleVO;

        switch (productType) {
            case CREDIT_CARD:
                List<CreditCardDetails> creditCardDetailsList = creditCardDetailsRepository.query();
                cashBackConfigList = cashBackConfigRepository.query(creditCardDetailsList.stream()
                        .map(CreditCardDetails::getCashbackConfigId).collect(Collectors.toList()));
                for (CreditCardDetails creditCardDetails : creditCardDetailsList) {
                    cashBackConfigRuleVO = new CashBackConfigRuleVO();
                    cashBackConfigRuleVO.setProductCode(creditCardDetails.getCardCode());
                    cashBackConfigRuleVO.setProductName(creditCardDetails.getCardName());
                    cashBackConfig = cashBackConfigList.stream()
                            .filter(config -> config.getConfigId()
                                    .equals(creditCardDetails.getCashbackConfigId()))
                            .collect(Collectors.toList()).get(0);
                    buildData(cashBackConfigRuleVO, cashBackConfig);
                    cashBackConfigRuleVOList.add(cashBackConfigRuleVO);
                }
                break;
            case LOAN:
                List<LoanDetails> loanDetailsList = loanDetailsRepository.query();
                cashBackConfigList = cashBackConfigRepository.query(loanDetailsList.stream()
                        .map(LoanDetails::getCashbackConfigId).collect(Collectors.toList()));
                for (LoanDetails loanDetails : loanDetailsList) {
                    cashBackConfigRuleVO = new CashBackConfigRuleVO();
                    cashBackConfigRuleVO.setProductCode(loanDetails.getProductCode());
                    cashBackConfigRuleVO.setProductName(loanDetails.getProductName());
                    cashBackConfig = cashBackConfigList.stream()
                            .filter(config -> config.getConfigId()
                                    .equals(loanDetails.getCashbackConfigId()))
                            .collect(Collectors.toList()).get(0);
                    buildData(cashBackConfigRuleVO, cashBackConfig);
                    cashBackConfigRuleVOList.add(cashBackConfigRuleVO);
                }
                break;
            default:
        }
        page.setDataList(cashBackConfigRuleVOList);
        page.setTotalCount((long) cashBackConfigRuleVOList.size());
        return page;
    }

    private void buildData(CashBackConfigRuleVO cashBackConfigRuleVO,
                           CashBackConfig cashBackConfig) {
        CashBackTypeEnum cashBackType = CashBackTypeEnum
                .getByCode(cashBackConfig.getCashbackType());
        PreconditionUtils.checkArgument(Objects.nonNull(cashBackType), ReturnCode.SYS_ERROR);
        switch (cashBackType) {
            case AMOUNT:
                cashBackConfigRuleVO.setTotalBonus(formatMoney(cashBackConfig.getTotalBonus()));
                cashBackConfigRuleVO
                        .setTerminalBonus(formatMoney(cashBackConfig.getTerminalBonus()));
                cashBackConfigRuleVO.setDirectBonus(formatMoney(cashBackConfig.getDirectBonus()));
                cashBackConfigRuleVO
                        .setIndirectBonus(formatMoney(cashBackConfig.getIndirectBonus()));
                break;
            case PERCENTAGE:
                cashBackConfigRuleVO.setTotalBonus(
                        MessageFormat.format("{0}%", formatMoney(cashBackConfig.getTotalBonus())));
                cashBackConfigRuleVO.setTerminalBonus(
                        MessageFormat.format("{0}%", formatMoney(cashBackConfig.getTerminalBonus())));
                cashBackConfigRuleVO.setDirectBonus(
                        MessageFormat.format("{0}%", formatMoney(cashBackConfig.getDirectBonus())));
                cashBackConfigRuleVO.setIndirectBonus(
                        MessageFormat.format("{0}%", formatMoney(cashBackConfig.getIndirectBonus())));
                break;
            default:
        }
    }

    private String formatMoney(BigDecimal money) {
        return money.setScale(2, RoundingMode.HALF_UP).toString();
    }
}
