package cn.zhishush.finance.core.dal.dao.product;

import java.util.List;
import java.util.Map;

import cn.zhishush.finance.core.dal.dao.base.BaseDAO;
import org.apache.ibatis.annotations.Param;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.core.dal.dataobject.product.ProductMain;

/**
 * <p>注释</p>
 * 
 * @author lili
 * @version $Id: ProductMainDAO.java, v0.1 2018/11/14 12:54 PM lili Exp $
 */
public interface ProductMainDAO extends BaseDAO<ProductMain, Long> {

    List<ProductMain> selectProductByType(@Param("typ") Integer typ,
                                          @Param("page") Page<ProductMain> page);

    // 保险产品-返现规则查询
    List<ProductMain> selectRebackCashRuleList(@Param("page") Page<ProductMain> page);

    /**
     * 分页查询
     * 
     * @param parameters
     *            查询参数
     * @return List<ProductMain>
     */
    List<ProductMain> query(Map<String, Object> parameters);

    /**
     * 统计收益
     * 
     * @param ids
     *            产品id列表
     * @return ProductMain
     */
    ProductMain sumBonusByIds(@Param("ids") List<Long> ids);
}