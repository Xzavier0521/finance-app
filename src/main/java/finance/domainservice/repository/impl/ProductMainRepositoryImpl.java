package finance.domainservice.repository.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import finance.core.dal.dao.ProductMainDAO;
import finance.core.dal.dataobject.ProductMain;
import finance.domainservice.repository.ProductMainRepository;

/**
 * <p>产品信息</p>
 * 
 * @author lili
 * @version : ProductMainRepositoryImpl.java.java, v 0.1 2018/9/27 下午8:33 lili Exp $
 */

@Repository("productMainRepository")
public class ProductMainRepositoryImpl implements ProductMainRepository {

    @Resource
    private ProductMainDAO financeProductMainMapper;

    @Override
    public List<ProductMain> queryByCondition(String... ids) {
        List<ProductMain> productMainList = Lists.newArrayList();
        Map<String, Object> parameters = Maps.newHashMap();
        if (Objects.nonNull(ids) && ids.length > 0) {
            parameters.put("ids", ids);
            productMainList = financeProductMainMapper.query(parameters);
        }
        return productMainList;
    }

    @Override
    public ProductMain sumBonus(List<Long> ids) {
        return financeProductMainMapper.sumBonusByIds(ids);
    }
}
