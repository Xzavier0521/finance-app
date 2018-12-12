package cn.zhishush.finance.domainservice.repository.user.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cn.zhishush.finance.core.dal.dao.user.UserInfoDAO;
import cn.zhishush.finance.core.dal.dataobject.user.UserInfoDO;
import cn.zhishush.finance.domain.user.UserInfo;
import cn.zhishush.finance.domainservice.converter.user.UserInfoConverter;
import cn.zhishush.finance.domainservice.repository.user.UserInfoRepository;

/**
 * <p>用户信息</p>
 * 
 * @author lili
 * @version : UserInfoRepositoryImpl.java.java, v 0.1 2018/9/27 下午8:34 lili Exp$
 */
@Repository("userInfoRepository")
public class UserInfoRepositoryImpl implements UserInfoRepository {

    @Resource
    private UserInfoDAO userInfoDAO;

    /**
     * 查询用户信息列表
     *
     * @param ids
     *            用户id
     * @return List<UserInfoDO>
     */
    @Override
    public List<UserInfo> queryByCondition(List<Long> ids) {
        List<UserInfoDO> financeUserInfoList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(ids)) {
            financeUserInfoList = userInfoDAO.selectByPrimaryKeys(ids);
        }
        return UserInfoConverter.convert2List(financeUserInfoList);
    }

    /**
     * 根据邀请码查询用户信息
     * 
     * @param inviteCode
     *            邀请码
     * @return UserInfo
     */
    @Override
    public UserInfo queryByCondition(String inviteCode) {
        return UserInfoConverter.convert(userInfoDAO.selectByInviteCode(inviteCode));
    }

    /**
     * 根据手机号码查询用户信息
     * 
     * @param mobileNum
     *            手机号码
     * @return UserInfo
     */
    @Override
    public UserInfo queryByMobileNum(String mobileNum) {
        UserInfo userInfo = null;
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("mobileNum", mobileNum);
        List<UserInfo> userInfoList = UserInfoConverter.convert2List(userInfoDAO.query(parameters));
        if (CollectionUtils.isNotEmpty(userInfoList)) {
            userInfo = userInfoList.get(0);
        }
        return userInfo;
    }
}
