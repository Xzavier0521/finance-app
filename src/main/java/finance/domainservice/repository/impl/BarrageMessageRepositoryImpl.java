package finance.domainservice.repository.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

import finance.api.model.base.Page;
import finance.api.model.condition.BarrageMessageQueryCondition;
import finance.core.dal.dao.BarrageMessageDAO;
import finance.core.dal.dataobject.BarrageMessageDO;
import finance.domain.log.BarrageMessage;
import finance.domainservice.converter.BarrageMessageConverter;
import finance.domainservice.repository.BarrageMessageRepository;

/**
 * <p>弹幕消息：现阶段保存到数据库，后期可以考虑放入redis或者采用rabbitmq</p>
 *
 * @author lili
 * @version 1.0: BarrageMessageRepositoryImpl.java, v 0.1 2018/9/29 上午10:07 lili Exp $
 */
@Slf4j
@Repository("barrageMessageRepository")
public class BarrageMessageRepositoryImpl implements BarrageMessageRepository {

    @Resource
    private BarrageMessageDAO financeBarrageMessageMapper;

    /**
     * 保存弹幕消息
     *
     * @param barrageMessage
     *            弹幕消息
     * @return int
     */
    @Override
    public int save(BarrageMessage barrageMessage) {
        return financeBarrageMessageMapper
            .insertSelective(BarrageMessageConverter.convert(barrageMessage));
    }

    /**
     * 查询带分页
     *
     * @param condition
     *            查询条件
     * @return Page<BarrageMessage>
     */
    @Override
    public Page<BarrageMessage> query(BarrageMessageQueryCondition condition) {
        Page<BarrageMessage> page = new Page<>(condition.getPageSize(),
            (long) condition.getPageNum());
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("page", page);
        int count = financeBarrageMessageMapper.count(parameters);
        page.setTotalCount((long) count);
        if (count > 0) {
            long totalPage = page.getTotalPage();
            int pageNum = (int) (1 + Math.random() * (totalPage - 1 + 1));
            page = new Page<>(condition.getPageSize(), (long) pageNum);
            parameters.put("page", page);
            List<BarrageMessageDO> barrageMessageDOList = financeBarrageMessageMapper
                .query(parameters);
            if (CollectionUtils.isNotEmpty(barrageMessageDOList)) {
                page.setDataList(BarrageMessageConverter.convert2List(barrageMessageDOList));
            }
        }
        return page;
    }
}
