package cn.zhishush.finance.domainservice.repository.third.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.zhishush.finance.domain.third.ThirdUnionLoginLog;
import cn.zhishush.finance.domainservice.converter.third.ThirdUnionLoginLogConverter;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.core.dal.dao.third.ThirdUnionLoginLogDAO;
import cn.zhishush.finance.core.dal.dataobject.third.ThirdUnionLoginLogDO;
import cn.zhishush.finance.domainservice.repository.third.ThirdUnionLoginLogRepository;

/**
 * <p>第三方联合登陆日志p</p>
 *
 * @author lili
 * @version 1.0: ThirdUnionLoginLogRepositoryImpl.java, v0.1 2018/11/28 8:38 PM PM lili Exp $
 */
@Repository("thirdUnionLoginLogRepository")
public class ThirdUnionLoginLogRepositoryImpl implements ThirdUnionLoginLogRepository {

    @Resource
    private ThirdUnionLoginLogDAO thirdUnionLoginLogDAO;

    @Override
    public int save(ThirdUnionLoginLog thirdUnionLoginLog) {
        return thirdUnionLoginLogDAO
            .insertSelective(ThirdUnionLoginLogConverter.convert(thirdUnionLoginLog));
    }

    @Override
    public Page<ThirdUnionLoginLog> query(Long userId, int pageSize, int pageNum) {

        Page<ThirdUnionLoginLog> page = new Page<>(pageSize, (long) pageNum);
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("userId", userId);
        parameters.put("page", page);
        int count = thirdUnionLoginLogDAO.count(parameters);
        page.setTotalCount((long) count);
        if (count > 0) {
            List<ThirdUnionLoginLogDO> thirdUnionLoginLogDOList = thirdUnionLoginLogDAO
                .query(parameters);
            page.setDataList(ThirdUnionLoginLogConverter.convert2List(thirdUnionLoginLogDOList));
        }
        return page;
    }
}
