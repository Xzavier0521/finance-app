package cn.zhishush.finance.domainservice.service.popularize.impl;

import java.math.RoundingMode;
import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.vo.popularize.*;
import cn.zhishush.finance.core.common.enums.CashBackTypeEnum;
import cn.zhishush.finance.core.common.enums.PopularizeModuleTypeEnum;
import cn.zhishush.finance.core.common.enums.PopularizeSubModuleTypeEnum;
import cn.zhishush.finance.core.common.enums.ReturnCode;
import cn.zhishush.finance.core.common.util.ConvertBeanUtil;
import cn.zhishush.finance.core.common.util.PreconditionUtils;
import cn.zhishush.finance.domain.cashbak.CashBackConfig;
import cn.zhishush.finance.domain.creditcard.CreditCardDetails;
import cn.zhishush.finance.domain.creditcard.CreditCardInfo;
import cn.zhishush.finance.domain.loan.LoanDetails;
import cn.zhishush.finance.domain.loan.LoanInfo;
import cn.zhishush.finance.domain.popularize.*;
import cn.zhishush.finance.domainservice.repository.popularize.*;
import cn.zhishush.finance.domainservice.repository.third.impl.product.*;
import cn.zhishush.finance.domainservice.service.popularize.PopularizeModuleInfoQueryService;

import com.google.common.collect.Lists;

/**
 * <p>推广模块信息查询</p>
 *
 * @author lili
 * @version 1.0: PopularizeModuleInfoQueryServiceImpl.java, v0.1 2018/12/9 10:35 PM PM lili Exp $
 */
@Slf4j
@Service("popularizeModuleInfoQueryService")
public class PopularizeModuleInfoQueryServiceImpl implements PopularizeModuleInfoQueryService {

    @Resource
    private PopularizeModuleInfoRepository    popularizeModuleInfoRepository;

    @Resource
    private PopularizeSubModuleInfoRepository popularizeSubModuleInfoRepository;

    @Resource
    private PopularizeProductInfoRepository   popularizeProductInfoRepository;

    @Resource
    private PopularizeMaterialInfoRepository  popularizeMaterialInfoRepository;

    @Resource
    private PopularizeActivityInfoRepository  popularizeActivityInfoRepository;

    @Resource
    private CreditCardInfoRepository          creditCardInfoRepository;

    @Resource
    private LoanInfoRepository                loanInfoRepository;

    @Resource
    private CashBackConfigRepository          cashBackConfigRepository;

    @Resource
    private CreditCardDetailsRepository       creditCardDetailsRepository;

    @Resource
    private LoanDetailsRepository             loanDetailsRepository;

    @Override
    public Page<PopularizeModuleInfoVO> query4Page(int pageSize, int pageNum) {
        Page<PopularizeModuleInfoVO> page = new Page<>(pageSize, (long) pageNum);
        // 模块信息
        Page<PopularizeModuleInfo> moduleInfoPage = popularizeModuleInfoRepository
            .query4Page(pageSize, pageNum);
        page.setTotalCount(moduleInfoPage.getTotalCount());
        if (moduleInfoPage.getTotalCount() == 0) {
            return page;
        }
        //
        List<PopularizeModuleInfo> moduleInfoList = moduleInfoPage.getDataList();
        if (CollectionUtils.isEmpty(moduleInfoList)) {
            return page;
        }

        PopularizeModuleInfoVO popularizeModuleInfoVO;
        List<PopularizeModuleInfoVO> moduleInfoVOList = Lists
            .newArrayListWithCapacity(moduleInfoList.size());
        List<PopularizeSubModuleInfoVO> subModules;
        for (PopularizeModuleInfo popularizeModuleInfo : moduleInfoList) {
            PopularizeModuleTypeEnum moduleType = PopularizeModuleTypeEnum
                .getByCode(popularizeModuleInfo.getModuleType());
            PreconditionUtils.checkArgument(Objects.nonNull(moduleType), ReturnCode.SYS_ERROR);
            popularizeModuleInfoVO = new PopularizeModuleInfoVO();
            ConvertBeanUtil.copyBeanProperties(popularizeModuleInfo, popularizeModuleInfoVO);
            // 子模块
            List<PopularizeSubModuleInfo> popularizeSubModuleInfoList = popularizeSubModuleInfoRepository
                .query(popularizeModuleInfo.getModuleCode());
            if (CollectionUtils.isNotEmpty(popularizeSubModuleInfoList)) {
                subModules = Lists.newArrayListWithCapacity(popularizeSubModuleInfoList.size());
                PopularizeSubModuleInfoVO subModuleInfoVO;
                for (PopularizeSubModuleInfo popularizeSubModuleInfo : popularizeSubModuleInfoList) {
                    subModuleInfoVO = new PopularizeSubModuleInfoVO();
                    ConvertBeanUtil.copyBeanProperties(popularizeSubModuleInfo, subModuleInfoVO);
                    subModules.add(subModuleInfoVO);
                }
                //
                switch (moduleType) {
                    case ACTIVITY:
                        // 活动
                        subModules.forEach(popularizeSubModuleInfoVO -> {
                            List<PopularizeActivityInfo> popularizeActivityInfoList = popularizeActivityInfoRepository
                                .query(popularizeSubModuleInfoVO.getSubModuleCode());
                            List<PopularizeActivityInfoVO> popularizeActivityInfoVOList = Lists
                                .newArrayListWithCapacity(popularizeActivityInfoList.size());
                            PopularizeActivityInfoVO popularizeActivityInfoVO;
                            for (PopularizeActivityInfo popularizeActivityInfo : popularizeActivityInfoList) {
                                popularizeActivityInfoVO = new PopularizeActivityInfoVO();
                                ConvertBeanUtil.copyBeanProperties(popularizeActivityInfo,
                                    popularizeActivityInfoVO);
                                popularizeActivityInfoVOList.add(popularizeActivityInfoVO);
                            }
                            popularizeSubModuleInfoVO.setItems(popularizeActivityInfoVOList);
                        });
                        break;
                    case PRODUCT:
                        // 产品
                        subModules.forEach(popularizeSubModuleInfoVO -> {
                            PopularizeSubModuleTypeEnum subModuleType = PopularizeSubModuleTypeEnum
                                .getByCode(popularizeSubModuleInfoVO.getSubModuleType());
                            PreconditionUtils.checkArgument(Objects.nonNull(subModuleType),
                                ReturnCode.SYS_ERROR);
                            List<PopularizeProductInfo> productInfoList = popularizeProductInfoRepository
                                .query(popularizeSubModuleInfoVO.getSubModuleCode());
                            if (CollectionUtils.isNotEmpty(productInfoList)) {
                                switch (subModuleType) {
                                    case CREDIT_CARD:
                                        // 信用卡
                                        popularizeSubModuleInfoVO
                                            .setItems(buildCreditCardData(productInfoList));
                                        break;
                                    case LOAN:
                                        // 贷款
                                        popularizeSubModuleInfoVO
                                            .setItems(buildLoanData(productInfoList));
                                        break;
                                    default:
                                }
                            }

                        });
                        //
                        break;
                    case MATERIAL:
                        // 素材
                        subModules.forEach(popularizeSubModuleInfoVO -> {
                            List<PopularizeMaterialInfo> materialInfoList = popularizeMaterialInfoRepository
                                .query(popularizeSubModuleInfoVO.getSubModuleCode());
                            List<PopularizeMaterialInfoVO> items = Lists
                                .newArrayListWithCapacity(materialInfoList.size());
                            PopularizeMaterialInfoVO popularizeMaterialInfoVO;
                            for (PopularizeMaterialInfo popularizeMaterialInfo : materialInfoList) {
                                popularizeMaterialInfoVO = new PopularizeMaterialInfoVO();
                                ConvertBeanUtil.copyBeanProperties(popularizeMaterialInfo,
                                    popularizeMaterialInfoVO);
                                items.add(popularizeMaterialInfoVO);
                            }
                            popularizeSubModuleInfoVO.setItems(items);
                        });
                        break;
                    default:
                }
                popularizeModuleInfoVO.setSubModules(subModules);
            }
            moduleInfoVOList.add(popularizeModuleInfoVO);

        }
        page.setDataList(moduleInfoVOList);
        return page;
    }

    private List<PopularizeCreditCardInfoVO> buildCreditCardData(List<PopularizeProductInfo> productInfoList) {
        List<PopularizeCreditCardInfoVO> items = Lists
            .newArrayListWithCapacity(productInfoList.size());
        PopularizeCreditCardInfoVO popularizeCreditCardInfoVO;
        CreditCardInfo creditCardInfo;
        for (PopularizeProductInfo productInfo : productInfoList) {
            popularizeCreditCardInfoVO = new PopularizeCreditCardInfoVO();
            ConvertBeanUtil.copyBeanProperties(productInfo, popularizeCreditCardInfoVO);
            popularizeCreditCardInfoVO.setCardCode(productInfo.getProductCode());
            creditCardInfo = creditCardInfoRepository.query(productInfo.getProductCode());
            ConvertBeanUtil.copyBeanProperties(creditCardInfo, popularizeCreditCardInfoVO);
            popularizeCreditCardInfoVO.setCardLimitStr(MessageFormat.format("{0}~{1}",
                creditCardInfo.getCardLimitMin(), creditCardInfo.getCardLimitMax()));
            popularizeCreditCardInfoVO
                .setPredictPassingRateStr(creditCardInfo.getPredictPassingRate());
            popularizeCreditCardInfoVO.setOrder(productInfo.getProductOrder());
            // 返现规则
            CreditCardDetails creditCardDetails = creditCardDetailsRepository
                .query(productInfo.getProductCode());
            if (Objects.nonNull(creditCardDetails)
                && StringUtils.isNotBlank(creditCardDetails.getCashbackConfigId())) {
                CashBackConfig cashBackConfig = cashBackConfigRepository
                    .query(creditCardDetails.getCashbackConfigId());
                if (Objects.nonNull(cashBackConfig)) {
                    CashBackTypeEnum cashBackType = CashBackTypeEnum
                        .getByCode(cashBackConfig.getCashbackType());
                    PreconditionUtils.checkArgument(Objects.nonNull(cashBackType),
                        ReturnCode.SYS_ERROR);
                    switch (cashBackType) {
                        case AMOUNT:
                            popularizeCreditCardInfoVO.setTerminalBonus(
                                MessageFormat.format("{0}元/张", cashBackConfig.getTerminalBonus()));
                            popularizeCreditCardInfoVO.setDirectBonus(
                                MessageFormat.format("{0}元/张", cashBackConfig.getDirectBonus()));
                            popularizeCreditCardInfoVO.setIndirectBonus(
                                MessageFormat.format("{0}元/张", cashBackConfig.getIndirectBonus()));
                            break;
                        case PERCENTAGE:
                            popularizeCreditCardInfoVO.setTerminalBonus(
                                MessageFormat.format("{0}%", cashBackConfig.getTerminalBonus()
                                    .setScale(2, RoundingMode.HALF_UP).toPlainString()));
                            popularizeCreditCardInfoVO.setDirectBonus(
                                MessageFormat.format("{0}%", cashBackConfig.getDirectBonus()
                                    .setScale(2, RoundingMode.HALF_UP).toPlainString()));
                            popularizeCreditCardInfoVO.setIndirectBonus(
                                MessageFormat.format("{0}%", cashBackConfig.getIndirectBonus()
                                    .setScale(2, RoundingMode.HALF_UP).toPlainString()));
                            break;
                        default:
                    }
                }

            }
            items.add(popularizeCreditCardInfoVO);
        }
        return items;
    }

    private List<PopularizeLoanInfoVO> buildLoanData(List<PopularizeProductInfo> productInfoList) {
        List<PopularizeLoanInfoVO> items = Lists.newArrayListWithCapacity(productInfoList.size());
        PopularizeLoanInfoVO popularizeLoanInfoVO;
        LoanInfo loanInfo;
        for (PopularizeProductInfo productInfo : productInfoList) {
            popularizeLoanInfoVO = new PopularizeLoanInfoVO();
            ConvertBeanUtil.copyBeanProperties(productInfo, popularizeLoanInfoVO);
            loanInfo = loanInfoRepository.query(productInfo.getProductCode());
            //
            if (Objects.isNull(loanInfo)) {
                continue;
            }
            ConvertBeanUtil.copyBeanProperties(loanInfo, popularizeLoanInfoVO);
            popularizeLoanInfoVO.setPredictPassingRateStr(loanInfo.getPredictPassingRate());
            popularizeLoanInfoVO.setOrder(productInfo.getProductOrder());
            // 返现规则
            LoanDetails loanDetails = loanDetailsRepository.query(productInfo.getProductCode());
            if (Objects.nonNull(loanDetails)
                && StringUtils.isNotBlank(loanDetails.getCashbackConfigId())) {
                CashBackConfig cashBackConfig = cashBackConfigRepository
                    .query(loanDetails.getCashbackConfigId());
                if (Objects.nonNull(cashBackConfig)) {
                    CashBackTypeEnum cashBackType = CashBackTypeEnum
                        .getByCode(cashBackConfig.getCashbackType());
                    PreconditionUtils.checkArgument(Objects.nonNull(cashBackType),
                        ReturnCode.SYS_ERROR);
                    //
                    switch (cashBackType) {
                        case AMOUNT:
                            popularizeLoanInfoVO.setTerminalBonus(
                                MessageFormat.format("{0}元", cashBackConfig.getTerminalBonus()));
                            popularizeLoanInfoVO.setDirectBonus(
                                MessageFormat.format("{0}元", cashBackConfig.getDirectBonus()));
                            popularizeLoanInfoVO.setIndirectBonus(
                                MessageFormat.format("{0}元", cashBackConfig.getIndirectBonus()));
                            break;
                        case PERCENTAGE:
                            popularizeLoanInfoVO.setTerminalBonus(
                                MessageFormat.format("{0}%", cashBackConfig.getTerminalBonus()
                                    .setScale(2, RoundingMode.HALF_UP).toPlainString()));
                            popularizeLoanInfoVO.setDirectBonus(
                                MessageFormat.format("{0}%", cashBackConfig.getDirectBonus()
                                    .setScale(2, RoundingMode.HALF_UP).toPlainString()));
                            popularizeLoanInfoVO.setIndirectBonus(
                                MessageFormat.format("{0}%", cashBackConfig.getIndirectBonus()
                                    .setScale(2, RoundingMode.HALF_UP).toPlainString()));
                            break;
                        default:
                    }

                }

            }
            items.add(popularizeLoanInfoVO);
        }
        return items;
    }
}
