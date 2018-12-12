package cn.zhishush.finance.domainservice.service.activity.query;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.condition.RedEnvelopeDetailQueryCondition;
import cn.zhishush.finance.domain.activity.ParticipantInfo;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: RedEnvelopeDetailQueryService.java, v0.1 2018/10/20 10:24 AM
 *          lili Exp $
 */
public interface RedEnvelopeDetailQueryService {

	/**
	 * 查询红包邀请人数
	 * 
	 * @param activityCode
	 *            活动代码
	 * @return Long
	 */
	Long queryJoinNum(String activityCode);

	/**
	 * 分页查询红包活动明细
	 * 
	 * @param condition
	 *            查询条件
	 * @return Page<ParticipantInfo>
	 */
	Page<ParticipantInfo> queryDetail4Page(RedEnvelopeDetailQueryCondition condition);
}
