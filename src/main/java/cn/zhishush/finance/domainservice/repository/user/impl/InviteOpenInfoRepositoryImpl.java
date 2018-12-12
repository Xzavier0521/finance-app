package cn.zhishush.finance.domainservice.repository.user.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

import cn.zhishush.finance.core.common.enums.ConcernStatusEnum;
import cn.zhishush.finance.core.dal.dao.weixin.InviteOpenInfoDAO;
import cn.zhishush.finance.domain.weixin.InviteOpenInfo;
import cn.zhishush.finance.domain.weixin.WeChatSubscribeInfo;
import cn.zhishush.finance.domainservice.converter.user.InviteOpenInfoConverter;
import cn.zhishush.finance.domainservice.converter.weixin.WeChatSubscribeInfoConverter;
import cn.zhishush.finance.domainservice.repository.user.InviteOpenInfoRepository;

/**
 * <p>邀请码-open_info绑定</p>
 * 
 * @author lili
 * @version $Id: InviteOpenInfoRepositoryImpl.java, v0.1 2018/10/31 11:10 PM lili Exp $
 */
@Slf4j
@Repository("inviteOpenInfoRepository")
public class InviteOpenInfoRepositoryImpl implements InviteOpenInfoRepository {
    @Resource
    private InviteOpenInfoDAO inviteOpenInfoDAO;

    /**
     * 保存邀请码-open_info绑定 信息
     * @param inviteOpenInfo 记录
     * @return int
     */
    @Override
    public int save(InviteOpenInfo inviteOpenInfo) {
        return inviteOpenInfoDAO.insertSelective(InviteOpenInfoConverter.convert(inviteOpenInfo));
    }

    @Override
    public List<InviteOpenInfo> queryByCondition(Map<String, Object> parameters) {
        return InviteOpenInfoConverter.convert2List(inviteOpenInfoDAO.query(parameters));
    }

    @Override
    public Long count(Map<String, Object> parameters) {
        return inviteOpenInfoDAO.count(parameters);
    }

    @Override
    public WeChatSubscribeInfo countSubscribeInfo(String inviteCode) {
        return WeChatSubscribeInfoConverter
            .convert(inviteOpenInfoDAO.countSubscribeInfo(inviteCode));
    }

    @Override
    public int batchUpdateStatusByOpenId(List<String> openIds, ConcernStatusEnum concernStatus) {
        return inviteOpenInfoDAO.batchUpdateStatusByOpenId(openIds, concernStatus.getCode());
    }

    @Override
    public Long countUnSubscribeNum(String inviteCode, Long parentUserId) {
        return inviteOpenInfoDAO.countUnSubscribeNum(inviteCode, parentUserId);
    }

    @Override
    public InviteOpenInfo queryInviteOpenInfo(String openId) {
        InviteOpenInfo inviteOpenInfo = null;
        Map<String, Object> param = Maps.newHashMap();
        param.put("openId", openId);
        List<InviteOpenInfo> inviteOpenInfoList = InviteOpenInfoConverter
            .convert2List(inviteOpenInfoDAO.query(param));
        if (CollectionUtils.isNotEmpty(inviteOpenInfoList)) {
            inviteOpenInfo = inviteOpenInfoList.get(0);
        }
        return inviteOpenInfo;
    }
}
