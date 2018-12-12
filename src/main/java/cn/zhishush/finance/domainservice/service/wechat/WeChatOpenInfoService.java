package cn.zhishush.finance.domainservice.service.wechat;

import java.util.List;

import cn.zhishush.finance.domain.weixin.InviteOpenInfo;

/**
 * <p>微信公众号open_info和邀请代码绑定信息</p>
 * 
 * @author lili
 * @version 1.0: WeChatOpenInfoService.java, v0.1 2018/10/31 6:25 PM lili Exp $
 */
public interface WeChatOpenInfoService {

    /**
     * 保存用户open_info
     * 
     * @param activityCode
     *            活动代码
     * @param inviteCode
     *            邀请代码
     * @param openId
     *            open_id
     */
    void save(String activityCode, String inviteCode, String openId);

    /**
     * 查询微信公众号open_info和邀请代码绑定信息
     * 
     * @param inviteCode
     *            邀请代码
     * @return List<InviteOpenInfo>
     */
    List<InviteOpenInfo> queryByInviteCode(String inviteCode);

    /**
     * 查询微信公众号open_info和邀请代码绑定信息
     * 
     * @param openId
     *            open_id
     * @return InviteOpenInfo
     */
    InviteOpenInfo queryByOpenId(String openId);
}
