package cn.zhishush.finance.domainservice.service.financeproduct.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.zhishush.finance.core.dal.dao.product.LoanDetailDAO;
import cn.zhishush.finance.core.dal.dataobject.product.LoanDetailDO;
import cn.zhishush.finance.core.dal.dataobject.product.ProductMain;
import org.springframework.stereotype.Service;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.vo.financeproduct.LoanProductDetailVO;
import cn.zhishush.finance.api.model.vo.financeproduct.LoanProductListVO;
import cn.zhishush.finance.domainservice.service.financeproduct.LoanProductBiz;
import cn.zhishush.finance.core.dal.dao.product.ProductMainDAO;

/**
 * <p>
 * 贷款产品Biz实现层
 * </p>
 * 
 * @author MORUIHAI
 * @version 1.0: LoanProductBizImpl.java, v0.1 2018/07/12 18:28 PM MORUIHAI Exp
 *          $
 * @version 1.1: 增加字段[金额类型] 2018/10/08
 */
@Service
public class LoanProductBizImpl implements LoanProductBiz {
	@Resource
	private LoanDetailDAO financeLoanDetailMapper;
	@Resource
	private ProductMainDAO financeProductMainMapper;

	@Override
	public List<LoanProductListVO> findProductList(Page<ProductMain> financeProductPage) {
		List<LoanProductListVO> loanProductList = new ArrayList<>();
		// 根据类型查主表数据
		List<ProductMain> productMainList = financeProductMainMapper.selectProductByType(3,
				financeProductPage);

		if (productMainList != null && productMainList.size() > 0) {
			// 遍历主表数据取得id
			List<Long> financeMainIds = new ArrayList<>();
			productMainList.forEach(financeProductMain -> financeMainIds.add(financeProductMain.getId()));
			// 根据主表ID查询出办卡明细表
			List<LoanDetailDO> loanDetailDOS = financeLoanDetailMapper.selectByProductId(financeMainIds);
			LoanProductListVO loanProductListVO;
			LoanDetailDO loanDetailDO = null;

			Map<Long, LoanDetailDO> ffpdMap = new HashMap<>();
			loanDetailDOS.forEach(ffpd -> ffpdMap.put(ffpd.getProductId(), ffpd));

			for (ProductMain fpm : productMainList) {
				loanDetailDO = ffpdMap.get(fpm.getId());
				loanProductListVO = new LoanProductListVO();

				loanProductListVO.setId(fpm.getId());
				loanProductListVO.setProductName(fpm.getProductName());
				loanProductListVO.setLogoUrl(fpm.getLogoUrl());
				loanProductListVO.setTotalBonus(fpm.getTotalBonus(), fpm.getAmountType());
				loanProductList.add(loanProductListVO);
			}

		}

		return loanProductList;
	}

	@Override
	public LoanProductDetailVO findProductDetailByProductId(Long productId) {
		// 根据id查主表数据
		ProductMain productMain = financeProductMainMapper.selectByPrimaryKey(productId);
		LoanDetailDO loanDetailDO = financeLoanDetailMapper.selectProductDetailByProductId(productId);

		LoanProductDetailVO loanProductDetailVO = new LoanProductDetailVO();
		loanProductDetailVO.setId(productMain.getId());
		loanProductDetailVO.setProductName(productMain.getProductName());
		loanProductDetailVO.setMark1(loanDetailDO.getMark1());
		loanProductDetailVO.setMark2(loanDetailDO.getMark2());
		loanProductDetailVO.setAmountScope(loanDetailDO.getAmountScope());
		loanProductDetailVO.setDateScope(loanDetailDO.getDateScope());
		loanProductDetailVO.setLogoUrl(productMain.getLogoUrl());
		loanProductDetailVO.setTerminalBonus(productMain.getTerminalBonus(), productMain.getAmountType());
		loanProductDetailVO.setDirectBonus(productMain.getDirectBonus(), productMain.getAmountType());
		loanProductDetailVO.setIndirectBonus(productMain.getIndirectBonus(), productMain.getAmountType());
		loanProductDetailVO.setCashbackDate(productMain.getCashbackDate());
		loanProductDetailVO.setRedirectUrl(productMain.getRedirectUrl());

		loanProductDetailVO.setProductDesc(productMain.getProductDesc());
		loanProductDetailVO.setPromotionUrl(productMain.getPromotionUrl());
		// 金额类型
		loanProductDetailVO.setAmountType(String.valueOf(productMain.getAmountType()));

		return loanProductDetailVO;
	}
}
