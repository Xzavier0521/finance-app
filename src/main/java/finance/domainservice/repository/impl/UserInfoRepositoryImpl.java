package finance.domainservice.repository.impl;

import com.google.common.collect.Lists;
import finance.domain.UserInfo;
import finance.domainservice.converter.UserInfoConverter;
import finance.mapper.FinanceUserInfoDAO;
import finance.model.po.FinanceUserInfo;
import finance.domainservice.repository.UserInfoRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 *  <p>用户信息</p>
 * @author  lili
 * @version : UserInfoRepositoryImpl.java.java, v 0.1 2018/9/27 下午8:34 lili Exp $
 */
@Repository("userInfoRepository")
public class UserInfoRepositoryImpl implements UserInfoRepository {

    @Resource
    private FinanceUserInfoDAO financeUserInfoMapper;

    /**
     * 查询用户信息列表
     *
     * @param ids 用户id
     * @return List<FinanceUserInfo>
     */
    @Override
    public List<UserInfo> queryByCondition(List<Long> ids) {
        List<FinanceUserInfo> financeUserInfoList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(ids)) {
            financeUserInfoList = financeUserInfoMapper.selectByPrimaryKeys(ids);
        }
        return UserInfoConverter.convert2List(financeUserInfoList);
    }

    /**
     * 根据邀请码查询用户信息
     *
     * @param inviteCode 邀请码
     * @return UserInfo
     */
    @Override
    public UserInfo queryByCondition(String inviteCode) {
        return UserInfoConverter.convert(financeUserInfoMapper.selectByInviteCode(inviteCode));
    }
}
