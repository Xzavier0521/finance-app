package cn.zhishush.finance.core.dal.dao.product;

import java.util.List;

import cn.zhishush.finance.core.dal.dao.base.BaseDAO;
import org.apache.ibatis.annotations.Param;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.vo.productmainpage.ProductMainpageVO;
import cn.zhishush.finance.core.dal.dataobject.product.ProductMainPageDO;

/**
 * <p>注释</p>
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