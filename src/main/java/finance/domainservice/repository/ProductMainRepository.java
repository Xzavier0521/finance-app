package finance.domainservice.repository;

import finance.model.po.FinanceProductMain;

import java.util.List;

/**
 *  <p>产品信息</p>
 * @author  lili
 * @version :1.0  ProductMainRepository.java.java, v 0.1 2018/9/27 下午8:40 lili Exp $
 */
public interface ProductMainRepository {
    /**
     * 查询产品信息
     * @param ids  产品id列表
     * @return List<FinanceProductMain>
     */
    List<FinanceProductMain> queryByCondition(String... ids);

    /**
     * 汇总收益
     * @param ids 产品id列表
     * @return FinanceProductMain
     */
    FinanceProductMain sumBonus(List<Long> ids);
}
