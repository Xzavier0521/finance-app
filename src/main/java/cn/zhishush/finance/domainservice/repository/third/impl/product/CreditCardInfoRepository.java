package cn.zhishush.finance.domainservice.repository.third.impl.product;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.domain.creditcard.CreditCardInfo;

import java.util.List;

/**
 * <p>信用卡信息</p>
 *
 * @author lili
 * @version 1.0: CreditCardInfoRepository.java, v0.1 2018/11/29 12:58 AM PM lili Exp $
 */
public interface CreditCardInfoRepository {

    Page<CreditCardInfo> query(int pageSize, int pageNum, String cardCode, String bankCode);

    CreditCardInfo query(String cardCode);

    List<CreditCardInfo> query();
}
