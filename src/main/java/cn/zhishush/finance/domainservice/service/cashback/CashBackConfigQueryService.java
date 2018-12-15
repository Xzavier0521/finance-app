package cn.zhishush.finance.domainservice.service.cashback;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.vo.cashback.CashBackConfigRuleVO;
import cn.zhishush.finance.core.common.enums.ProductTypeEnum;

/**
 * <p>返现规则查询</p>
 *
 * @author lili
 * @version 1.0: CashBackConfigQueryService.java, v0.1 2018/12/14 5:41 PM PM lili Exp $
 */
public interface CashBackConfigQueryService {

    Page<CashBackConfigRuleVO> queryCashBackRule(ProductTypeEnum productType);
}
