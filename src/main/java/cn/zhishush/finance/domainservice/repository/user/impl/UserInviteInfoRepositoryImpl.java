package cn.zhishush.finance.domainservice.repository.user.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.core.dal.dao.user.UserInviteInfoDAO;
import cn.zhishush.finance.core.dal.dataobject.user.UserInviteInfoDO;
import cn.zhishush.finance.domain.user.UserInviteInfo;
import cn.zhishush.finance.domainservice.converter.user.UserInviteInfoConverter;
import cn.zhishush.finance.domainservice.repository.user.UserInviteInfoRepository;

/**
 * <p>用户邀请信息查询</p>
 * @author lili
 * @version 1.0: UserInviteInfoRepositoryImpl.java, v0.1 2018/11/26 10:22 AM lili Exp $
 */
@Slf4j
@Repository("userInviteInfoRepository")
public class UserInviteInfoRepositoryImpl implements UserInviteInfoRepository {

    @Resource
    private UserInviteInfoDAO userInviteInfoDAO;

    @Override
    public List<UserInviteInfoDO> query(Map<String, Object> parameters) {
        return userInviteInfoDAO.query(parameters);
    }

    @Override
    public int count(Map<String, Object> parameters) {
        return userInviteInfoDAO.count(parameters);
    }

    @Override
    public int countSecondLevelInviteUser(Long userId) {
        Long count = userInviteInfoDAO.selectCountByGrandParentId(Lists.newArrayList(userId), null);
        if (Objects.nonNull(count)) {
            return count.intValue();
        } else {
            return 0;
        }
    }

    @Override
    public Long countFirstInviteNum(Long userId) {
        return userInviteInfoDAO.countFirstInviteNum(Lists.newArrayList(userId));
    }

    @Override
    public List<UserInviteInfoDO> selectFirstInviteUsers(Long userId) {
        return null;
    }

    @Override
    public Page<UserInviteInfoDO> querySecondLevelInviteUser(Long userId, int pageNum,
                                                             int pageSize) {
        Page<UserInviteInfoDO> page = new Page<>(pageSize, (long) pageNum);
        List<Long> grandParentIds = Lists.newArrayList(userId);
        Long count = userInviteInfoDAO.selectCountByGrandParentId(grandParentIds, null);
        if (Objects.nonNull(count)) {
            page.setTotalCount(count);
            if (count.intValue() > 0) {
                page.setDataList(
                    userInviteInfoDAO.selectByGrandParentId(grandParentIds, null, page));
            }
        }
        return page;
    }

    @Override
    public Page<UserInviteInfoDO> queryByCondition(int pageNum, int pageSize,
                                                   Long... parentUserIds) {
        Page<UserInviteInfoDO> financeUserInviteInfoPage = new Page<>(pageSize, (long) pageNum);
        Map<String, Object> parameters = Maps.newHashMap();
        if (Objects.nonNull(parentUserIds) && parentUserIds.length > 0) {
            parameters.put("parentUserIds", parentUserIds);
            int count = userInviteInfoDAO.count(parameters);
            financeUserInviteInfoPage.setTotalCount((long) count);
            if (count > 0) {
                parameters.put("page", financeUserInviteInfoPage);
                List<UserInviteInfoDO> financeUserInviteInfoList = userInviteInfoDAO
                    .query(parameters);
                financeUserInviteInfoPage.setDataList(financeUserInviteInfoList);
            }
        }

        return financeUserInviteInfoPage;
    }

    @Override
    public List<UserInviteInfoDO> queryByCondition(Map<String, Object> parameters) {
        return userInviteInfoDAO.query(parameters);
    }

    /**
     * 更新支付金币标志
     *
     * @param userId
     *            用户id
     * @return int
     */
    @Override
    public int updatePayCoinFlag(Long userId) {
        return userInviteInfoDAO.updatePayCoinFlag(userId);
    }

    @Override
    public UserInviteInfo queryByCondition(Long userId) {
        UserInviteInfo userInviteInfo = null;
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("userId", userId);
        List<UserInviteInfoDO> userInviteInfoList = userInviteInfoDAO.query(parameters);
        if (CollectionUtils.isNotEmpty(userInviteInfoList)) {
            userInviteInfo = UserInviteInfoConverter.convert(userInviteInfoList.get(0));
        }

        return userInviteInfo;
    }

    @Override
    public Long countFirstInviteNum(Long parentUserId, String activityCode) {
        return userInviteInfoDAO.countFirstInviteNumByCode(parentUserId, activityCode);
    }

}
