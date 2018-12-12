package cn.zhishush.finance.core.dal.dao.weixin;

import java.util.List;
import java.util.Map;

import cn.zhishush.finance.core.dal.dataobject.weixin.WeiXinInviteInfoDO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>微信用户邀请关系</p>
 * @author lili
 * @version 1.0: WeiXinInviteInfoDAO.java, v0.1 2018/12/3 5:09 PM lili Exp $
 */
public interface WeiXinInviteInfoDAO {

    int delete(@Param("activityCode") String activityCode, @Param("openId") String openId);

    int insertSelective(WeiXinInviteInfoDO record);

    int batchInsert(List<WeiXinInviteInfoDO> weiXinInviteInfoDOList);

    List<WeiXinInviteInfoDO> query(Map parameters);

    int count(Map parameters);

    int updateByPrimaryKeySelective(WeiXinInviteInfoDO record);

}