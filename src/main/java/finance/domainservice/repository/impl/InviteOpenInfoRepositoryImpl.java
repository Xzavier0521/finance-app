package finance.domainservice.repository.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import finance.core.common.enums.ConcernStatusEnum;
import finance.domain.InviteOpenInfo;
import finance.domain.WeChatSubscribeInfo;
import finance.domainservice.converter.InviteOpenInfoConverter;
import finance.domainservice.converter.WeChatSubscribeInfoConverter;
import finance.domainservice.repository.InviteOpenInfoRepository;
import finance.mapper.InviteOpenInfoDAO;

/**
 * <p>邀请码-open_info绑定</p>
 * @author lili
 * @version $Id: InviteOpenInfoRepositoryImpl.java, v0.1 2018/10/31 11:10 PM lili Exp $
 */
@Repository("inviteOpenInfoRepository")
public class InviteOpenInfoRepositoryImpl implements InviteOpenInfoRepository {
    @Resource
    private InviteOpenInfoDAO inviteOpenInfoDAO;

    /**
     * 保存邀请码-open_info绑定 信息
     *
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

    /**
     * 更新关注状态
     *
     * @param openIds        微信open_id
     * @param concernStatus 关注状态
     * @return int
     */
    @Override
    public int batchUpdateStatusByOpenId(List<String> openIds, ConcernStatusEnum concernStatus) {
        return inviteOpenInfoDAO.batchUpdateStatusByOpenId(openIds, concernStatus.getCode());
    }

    @Override
    public Long countUnSubscribeNum(String inviteCode, Long parentUserId) {
        return inviteOpenInfoDAO.countUnSubscribeNum(inviteCode, parentUserId);
    }
}
