package finance.domainservice.repository;

import java.util.List;
import java.util.Map;

import finance.core.common.enums.ConcernStatusEnum;
import finance.domain.InviteOpenInfo;
import finance.domain.WeChatSubscribeInfo;

/**
 * <p>邀请码-open_info绑定</p>
 * @author lili
 * @version $Id: InviteOpenInfoRepository.java, v0.1 2018/10/31 11:08 PM lili Exp $
 */
public interface InviteOpenInfoRepository {

    /**
     *  保存邀请码-open_info绑定 信息
     * @param inviteOpenInfo 记录
     * @return int
     */
    int save(InviteOpenInfo inviteOpenInfo);

    /**
     * 查询邀请码和open_id 绑定信息
     * @param parameters 查询参数
     * @return List<InviteOpenInfo>
     */
    List<InviteOpenInfo> queryByCondition(Map<String, Object> parameters);

    /**
     *  统计记录
     * @param parameters 查询参数
     * @return Long
     */
    Long count(Map<String, Object> parameters);

    /**
     *  查询用户的下级用户关注信息
     * @param inviteCode 邀请码
     * @return WeChatSubscribeInfo
     */
    WeChatSubscribeInfo countSubscribeInfo(String inviteCode);

    /**
     * 更新关注状态
     * @param openIds 微信open_id
     * @param concernStatus 关注状态
     * @return int
     */
    int batchUpdateStatusByOpenId(List<String> openIds, ConcernStatusEnum concernStatus);

    Long countUnSubscribeNum(String inviteCode, Long parentUserId);

}
