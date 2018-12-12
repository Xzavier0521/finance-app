package cn.zhishush.finance.domainservice.repository.third.impl.product;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.domain.creditcard.CreditCardApplyInfo;

/**
 * <p>信用卡申请信息</p>
 *
 * @author lili
 * @version 1.0: CreditCardApplyInfoRepository.java, v0.1 2018/11/29 5:24 PM PM lili Exp $
 */
public interface CreditCardApplyInfoRepository {

    int save(CreditCardApplyInfo creditCardApplyInfo);

    int update(CreditCardApplyInfo creditCardApplyInfo);

    CreditCardApplyInfo query(Long userId, String productCode);

    Page<CreditCardApplyInfo> query(Long userId, int pageSize, int pageNum);
}
