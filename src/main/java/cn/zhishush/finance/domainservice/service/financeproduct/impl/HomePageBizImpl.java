package cn.zhishush.finance.domainservice.service.financeproduct.impl;

import java.util.List;

import javax.annotation.Resource;

import cn.zhishush.finance.core.dal.dao.product.ProductMainPageDAO;
import org.springframework.stereotype.Service;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.vo.productmainpage.ProductMainpageVO;
import cn.zhishush.finance.domainservice.service.financeproduct.HomePageBiz;

/**
 * @program: finance-app
 *
 * @description: 集合页BIZ实现类
 *
 * @author: MORUIHAI
 *
 * @create: 2018-07-13 09:23
 **/
@Service
public class HomePageBizImpl implements HomePageBiz {
	@Resource
	private ProductMainPageDAO financeProductMainPageMapper;

	@Override
	public List<ProductMainpageVO> findHomePageList(Long productType, Page<ProductMainpageVO> page) {
		return financeProductMainPageMapper.selectHomePageListByProductType(productType, page);
	}
}
