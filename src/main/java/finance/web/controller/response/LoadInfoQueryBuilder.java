package finance.web.controller.response;

import java.math.RoundingMode;
import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

import finance.api.model.base.Page;
import finance.api.model.vo.cashback.CashBackConfigTableVO;
import finance.api.model.vo.common.BodyVO;
import finance.api.model.vo.loan.LoanDetailsVO;
import finance.api.model.vo.loan.LoanInfoVO;
import finance.core.common.util.ConvertBeanUtil;
import finance.domain.cashbak.CashBackConfig;
import finance.domain.loan.LoanDetails;
import finance.domain.loan.LoanInfo;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: LoadInfoQueryBuilder.java, v0.1 2018/11/29 1:51 PM PM lili Exp $
 */
@Slf4j
public class LoadInfoQueryBuilder {

    public static Page<LoanInfoVO> build(Page<LoanInfo> fromPage) {
        Page<LoanInfoVO> toPage = new Page<>(fromPage.getPageSize(), fromPage.getPageNum());
        toPage.setTotalCount(fromPage.getTotalCount());
        List<LoanInfo> froms = fromPage.getDataList();
        if (CollectionUtils.isNotEmpty(froms)) {
            List<LoanInfoVO> tos = Lists.newArrayListWithCapacity(fromPage.getDataList().size());
            for (LoanInfo from : froms) {
                tos.add(convert(from));
            }
            toPage.setDataList(tos);
        }
        return toPage;
    }

    public static LoanInfoVO convert(LoanInfo from) {
        LoanInfoVO to = new LoanInfoVO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        to.setLoanAmountStr(from.getLoanAmount());
        to.setLoanInterestStr(from.getLoanInterest());
        to.setLoanTermStr(from.getLoanTerm());
        return to;
    }

    public static LoanDetailsVO build(LoanInfo loanInfo, LoanDetails loanDetails,
                                      CashBackConfig cashBackConfig) {

        LoanDetailsVO loanDetailsVO = new LoanDetailsVO();
        ConvertBeanUtil.copyBeanProperties(loanDetails, loanDetailsVO);
        loanDetailsVO.setLoanAmountStr(loanDetails.getLoanAmount());
        loanDetailsVO.setLoanTermStr(loanDetails.getLoanTerm());
        loanDetailsVO.setReferenceInterestStr(loanDetails.getReferenceInterest());
        loanDetailsVO.setAvgOrderAmount(
            loanDetails.getAvgOrderAmount().setScale(1, RoundingMode.HALF_UP).toString());
        // 返佣配置
        if (Objects.nonNull(cashBackConfig)) {
            CashBackConfigTableVO cashBackConfigTableVO = new CashBackConfigTableVO();
            cashBackConfigTableVO.setTitle("角色|办卡人|直接推广者|间接推广者");
            String body;
            if ("percentage".equalsIgnoreCase(cashBackConfig.getCashbackType())) {
                body = MessageFormat.format("返现比例|{0}%|{1}%|{2}%",
                    cashBackConfig.getTerminalBonus(), cashBackConfig.getDirectBonus(),
                    cashBackConfig.getIndirectBonus());
            } else {
                body = MessageFormat.format("返现比例|{0}元|{1}元|{2}元",
                        cashBackConfig.getTerminalBonus(), cashBackConfig.getDirectBonus(),
                        cashBackConfig.getIndirectBonus());
            }
            cashBackConfigTableVO.setBody(Lists.newArrayList(body));
            loanDetailsVO.setCashBackConfigTable(cashBackConfigTableVO);
        }
        //
        List<BodyVO> bodyList = Lists.newArrayList();
        // 贷款条件
        BodyVO loadConditions = BodyVO.builder().order(1).title("贷款条件").type("text")
            .value(loanDetails.getLoanConditions()).build();
        bodyList.add(loadConditions);
        // 申请资料
        BodyVO applyInfo = BodyVO.builder().order(2).title("申请资料").type("text")
            .value(loanDetails.getApplyInfo()).build();
        bodyList.add(applyInfo);
        // 贷款流程
        BodyVO loanProcess = BodyVO.builder().order(3).title("贷款流程").type("img")
            .value(loanDetails.getLoanProcessUrl()).build();
        bodyList.add(loanProcess);
        // 费用说明
        BodyVO feeDesc = BodyVO.builder().order(4).title("费用说明").type("text")
            .value(loanDetails.getFeeDesc()).build();
        bodyList.add(feeDesc);
        // 还款说明
        BodyVO repaymentDesc = BodyVO.builder().order(4).title("还款说明").type("text")
            .value(loanDetails.getRepaymentDesc()).build();
        bodyList.add(repaymentDesc);

        loanDetailsVO.setBodyList(bodyList);
        return loanDetailsVO;
    }
}
