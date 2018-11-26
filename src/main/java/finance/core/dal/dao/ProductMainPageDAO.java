package finance.core.dal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import finance.api.model.base.Page;
import finance.api.model.vo.productmainpage.ProductMainpageVO;
import finance.core.dal.dataobject.ProductMainPageDO;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: ProductMainPageDAO.java, v0.1 2018/11/14 12:57 PM lili Exp $
 */
public interface ProductMainPageDAO extends BaseDAO<ProductMainPageDO, Long> {

	/**
	 * 根据产品类型分类查询集合页数据
	 * 
	 * @param productType
	 * @param page
	 * @return
	 */

	List<ProductMainpageVO> selectHomePageListByProductType(@Param("productType") Long productType,
			@Param("page") Page<ProductMainpageVO> page);
}