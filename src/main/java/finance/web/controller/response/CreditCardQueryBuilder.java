package finance.web.controller.response;

import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

import finance.api.model.base.Page;
import finance.api.model.vo.creditCard.BankInfoVO;
import finance.api.model.vo.creditCard.CreditCardInfoVO;
import finance.core.common.util.ConvertBeanUtil;
import finance.domain.creditcard.BankInfo;
import finance.domain.creditcard.CreditCardInfo;

/**
 * <p>信用卡</p>
 *
 * @author lili
 * @version 1.0: CreditCardQueryBuilder.java, v0.1 2018/11/28 11:43 PM PM lili Exp $
 */
public class CreditCardQueryBuilder {

    public static Page<BankInfoVO> build(Page<BankInfo> fromPage) {
        Page<BankInfoVO> toPage = new Page<>(fromPage.getPageSize(), fromPage.getPageNum());
        toPage.setTotalCount(fromPage.getTotalCount());
        List<BankInfo> froms = fromPage.getDataList();
        if (CollectionUtils.isNotEmpty(froms)) {
            List<BankInfoVO> tos = Lists.newArrayListWithCapacity(fromPage.getDataList().size());
            for (BankInfo from : froms) {
                tos.add(convert(from));
            }
            toPage.setDataList(tos);
        }
        return toPage;
    }

    public static BankInfoVO convert(BankInfo from) {
        BankInfoVO to = new BankInfoVO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        // 额度
        to.setBankLimitStr(
            MessageFormat.format("{0}~{1}", from.getBankLimitMin(), from.getBankLimitMax()));
        // 预期通过率
        to.setPredictPassingRateStr(from.getPredictPassingRate());
        return to;
    }

    public static Page<CreditCardInfoVO> build4CreditCardInfo(Page<CreditCardInfo> fromPage) {
        Page<CreditCardInfoVO> toPage = new Page<>(fromPage.getPageSize(), fromPage.getPageNum());
        toPage.setTotalCount(fromPage.getTotalCount());
        List<CreditCardInfo> froms = fromPage.getDataList();
        if (CollectionUtils.isNotEmpty(froms)) {
            List<CreditCardInfoVO> tos = Lists
                .newArrayListWithCapacity(fromPage.getDataList().size());
            for (CreditCardInfo from : froms) {
                tos.add(convert(from));
            }
            toPage.setDataList(tos);
        }
        return toPage;
    }

    public static CreditCardInfoVO convert(CreditCardInfo from) {
        CreditCardInfoVO to = new CreditCardInfoVO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        to.setCardLimitStr(
            MessageFormat.format("{0}~{1}", from.getCardLimitMin(), from.getCardLimitMax()));
        to.setPredictPassingRateStr(from.getPredictPassingRate());
        return to;
    }
}
