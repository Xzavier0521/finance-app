package cn.zhishush.finance.domainservice.repository.weixin;

import java.util.List;

import cn.zhishush.finance.domain.weixin.WeiXinInviteInfo;
import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.condition.WeiXinInviteInfoQueryCondition;

/**
 * <p>微信用户邀请关系</p>
 *
 * @author lili
 * @version 1.0: WeiXinInviteInfoRepository.java, v0.1 2018/12/3 5:20 PM PM lili Exp $
 */
public interface WeiXinInviteInfoRepository {

    int save(WeiXinInviteInfo weiXinInviteInfo);

    int delete(String activityCode, String openId);

    WeiXinInviteInfo query(String activityCode, String openId);

    void batchSave(List<WeiXinInviteInfo> weiXinInviteInfoList);

    Page<WeiXinInviteInfo> query4Page(WeiXinInviteInfoQueryCondition condition);

    int update(WeiXinInviteInfo weiXinInviteInfo);
}
