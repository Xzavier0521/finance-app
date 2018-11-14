package finance.domainservice.repository.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import finance.mapper.FinanceProductMainDAO;
import finance.model.po.FinanceProductMain;
import finance.domainservice.repository.ProductMainRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *  <p>产品信息</p>
 * @author  lili
 * @version : ProductMainRepositoryImpl.java.java, v 0.1 2018/9/27 下午8:33 lili Exp $
 */

@Repository("productMainRepository")
public class ProductMainRepositoryImpl implements ProductMainRepository {

    @Resource
    private FinanceProductMainDAO financeProductMainMapper;
    @Override
    public List<FinanceProductMain> queryByCondition(String... ids) {
        List<FinanceProductMain> financeProductMainList = Lists.newArrayList();
        Map<String,Object> parameters = Maps.newHashMap();
        if (Objects.nonNull(ids) && ids.length >0){
            parameters.put("ids",ids);
            financeProductMainList = financeProductMainMapper.query(parameters);
        }
        return financeProductMainList;
    }

    @Override
    public FinanceProductMain sumBonus(List<Long> ids) {
        return financeProductMainMapper.sumBonusByIds(ids);
    }
}
