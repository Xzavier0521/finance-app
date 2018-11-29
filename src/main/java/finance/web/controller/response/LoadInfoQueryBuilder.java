package finance.web.controller.response;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

import finance.api.model.base.Page;
import finance.api.model.vo.loan.LoanInfoVO;
import finance.core.common.util.ConvertBeanUtil;
import finance.domain.loan.LoanInfo;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: LoadInfoQueryBuilder.java, v0.1 2018/11/29 1:51 PM PM lili Exp $
 */
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
}
