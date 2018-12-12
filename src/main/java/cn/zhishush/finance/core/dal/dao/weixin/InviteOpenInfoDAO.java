package cn.zhishush.finance.core.dal.dao.weixin;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.zhishush.finance.core.dal.dataobject.weixin.InviteOpenInfoDO;
import cn.zhishush.finance.core.dal.dataobject.weixin.WeChatSubscribeInfoDO;

/**
 * <p>邀请码和open_id 绑定关系</p>
 * 
 * @author lili
 * @version $Id: InviteOpenInfoDAO.java, v0.1 2018/10/31 11:06 PM lili Exp $
 */
public interface InviteOpenInfoDAO {

    int insertSelective(InviteOpenInfoDO record);

    InviteOpenInfoDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InviteOpenInfoDO record);

    List<InviteOpenInfoDO> query(Map<String, Object> parameters);

    Long count(Map<String, Object> parameters);

    WeChatSubscribeInfoDO countSubscribeInfo(@Param("inviteCode") String inviteCode);

    int batchUpdateStatusByOpenId(@Param("openIds") List<String> openIds,
                                  @Param("concernStatus") String concernStatus);

    Long countUnSubscribeNum(@Param("inviteCode") String inviteCode,
                             @Param("parentUserId") Long parentUserId);

}