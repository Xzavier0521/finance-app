package cn.zhishush.finance.domainservice.service.creditcard;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.vo.creditCard.CreditCardDetailVO;
import cn.zhishush.finance.api.model.vo.creditCard.CreditCardApplyInfoVO;

/**
 * <p>信用卡明细查询</p>
 *
 * @author lili
 * @version 1.0: CreditCardQueryService.java, v0.1 2018/11/29 1:42 AM PM lili Exp $
 */
public interface CreditCardQueryService {

    CreditCardDetailVO queryCreditCardDetail(String cardCode);

    Page<CreditCardApplyInfoVO> queryApplyInfo(Long userId, int pageSize, int pageNum);
}
