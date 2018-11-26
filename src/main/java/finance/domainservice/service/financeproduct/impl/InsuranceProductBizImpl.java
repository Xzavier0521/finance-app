package finance.domainservice.service.financeproduct.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import finance.core.dal.dataobject.ProductMain;
import org.springframework.stereotype.Service;

import finance.api.model.base.Page;
import finance.api.model.vo.financeproduct.RebackCashRuleVO;
import finance.domainservice.service.financeproduct.InsuranceProductBiz;
import finance.core.dal.dao.ProductMainDAO;

/**
 * <p>
 * 保险产品返现规则BIZ实现类
 * </p>
 * 
 * @author lili
 * @version $Id: InsuranceProductBizImpl.java, v0.1 2018/11/14 4:45 PM lili Exp
 *          $
 */
@Service
public class InsuranceProductBizImpl implements InsuranceProductBiz {
	@Resource
	private ProductMainDAO financeProductMainMapper;

	@Override
	public List<RebackCashRuleVO> findProductList(Page<ProductMain> financeProductPage) {
		List<RebackCashRuleVO> rebackCashRuleVOList = new ArrayList<>();
		List<ProductMain> productMainList = financeProductMainMapper
				.selectRebackCashRuleList(financeProductPage);
		if (productMainList != null && productMainList.size() > 0) {
			RebackCashRuleVO rebackCashRuleVO = null;
			for (ProductMain fpm : productMainList) {
				rebackCashRuleVO = new RebackCashRuleVO();
				rebackCashRuleVO.setId(fpm.getId());
				rebackCashRuleVO.setProductName(fpm.getProductName());
				rebackCashRuleVO.setTerminalBonus(fpm.getTerminalBonus(), fpm.getAmountType());
				rebackCashRuleVO.setDirectBonus(fpm.getDirectBonus(), fpm.getAmountType());
				rebackCashRuleVO.setIndirectBonus(fpm.getIndirectBonus(), fpm.getAmountType());
				rebackCashRuleVO.setCashbackDate(fpm.getCashbackDate());
				rebackCashRuleVOList.add(rebackCashRuleVO);
			}
		}
		return rebackCashRuleVOList;
	}
}
