package cn.zhishush.finance.domainservice.service.activity.query.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.condition.RedEnvelopeDetailQueryCondition;
import cn.zhishush.finance.domain.activity.ParticipantInfo;
import cn.zhishush.finance.domainservice.repository.activity.RedEnvelopeRepository;
import cn.zhishush.finance.domainservice.service.activity.query.RedEnvelopeDetailQueryService;

/**
 * <p>红包活动查询</p>
 * 
 * @author lili
 * @version $Id: RedEnvelopeDetailQueryServiceImpl.java, v0.1 2018/10/20 10:31
 *          AM lili Exp $
 */
@Service("redEnvelopeDetailQueryService")
public class RedEnvelopeDetailQueryServiceImpl implements RedEnvelopeDetailQueryService {

    @Resource
    private RedEnvelopeRepository redEnvelopeRepository;

    @Override
    public Long queryJoinNum(String activityCode) {
        return redEnvelopeRepository.queryJoinNum(activityCode);
    }

    /**
     * 分页查询红包活动明细
     * 
     * @param condition
     *            查询条件
     * @return Page<ParticipantInfo>
     */
    @Override
    public Page<ParticipantInfo> queryDetail4Page(RedEnvelopeDetailQueryCondition condition) {
        return redEnvelopeRepository.queryDetail4Page(condition);
    }
}
