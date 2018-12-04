package finance.domainservice.repository;

import java.util.List;

import finance.api.model.base.Page;
import finance.api.model.condition.WeiXinInviteInfoQueryCondition;
import finance.domain.weixin.WeiXinInviteInfo;

/**
 * <p>微信用户邀请关系</p>
 *
 * @author lili
 * @version 1.0: WeiXinInviteInfoRepository.java, v0.1 2018/12/3 5:20 PM PM lili Exp $
 */
public interface WeiXinInviteInfoRepository {

    int save(WeiXinInviteInfo weiXinInviteInfo);

    int delete(String activityCode, String openId);

    void batchSave(List<WeiXinInviteInfo> weiXinInviteInfoList);

    Page<WeiXinInviteInfo> query4Page(WeiXinInviteInfoQueryCondition condition);

    int update(WeiXinInviteInfo weiXinInviteInfo);
}
