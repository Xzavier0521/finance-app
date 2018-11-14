package finance.domainservice.repository.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import finance.api.model.base.Page;
import finance.domain.UserInviteInfo;
import finance.domainservice.converter.UserInviteInfoConverter;
import finance.domainservice.repository.UserInviteRepository;
import finance.mapper.FinanceUserInviteInfoDAO;
import finance.model.po.FinanceUserInviteInfo;

/**
 * <p>用户邀请信息查询</p>
 *
 * @author lili
 * @version :1.0 UserInviteRepositoryImpl.java.java, v 0.1 2018/9/27 下午8:35 lili Exp $
 */
@Slf4j
@Repository("userInviteRepository")
public class UserInviteRepositoryImpl implements UserInviteRepository {

    @Resource
    private FinanceUserInviteInfoDAO financeUserInviteInfoMapper;

    @Override
    public List<FinanceUserInviteInfo> query(Map<String, Object> parameters) {
        return financeUserInviteInfoMapper.query(parameters);
    }

    @Override
    public int count(Map<String, Object> parameters) {
        return financeUserInviteInfoMapper.count(parameters);
    }

    @Override
    public int countSecondLevelInviteUser(Long userId) {
        Long count = financeUserInviteInfoMapper
            .selectCountByGrandParentId(Lists.newArrayList(userId), null);
        if (Objects.nonNull(count)) {
            return count.intValue();
        } else {
            return 0;
        }
    }

    @Override
    public Long countFirstInviteNum(Long userId) {
        return financeUserInviteInfoMapper.countFirstInviteNum(Lists.newArrayList(userId));
    }

    @Override
    public List<FinanceUserInviteInfo> selectFirstInviteUsers(Long userId) {
        return null;
    }

    @Override
    public Page<FinanceUserInviteInfo> querySecondLevelInviteUser(Long userId, int pageNum,
                                                                  int pageSize) {
        Page<FinanceUserInviteInfo> page = new Page<>(pageSize, (long) pageNum);
        List<Long> grandParentIds = Lists.newArrayList(userId);
        Long count = financeUserInviteInfoMapper.selectCountByGrandParentId(grandParentIds, null);
        if (Objects.nonNull(count)) {
            page.setTotalCount(count);
            if (count.intValue() > 0) {
                page.setDataList(
                    financeUserInviteInfoMapper.selectByGrandParentId(grandParentIds, null, page));
            }
        }
        return page;
    }

    @Override
    public Page<FinanceUserInviteInfo> queryByCondition(int pageNum, int pageSize,
                                                        Long... parentUserIds) {
        Page<FinanceUserInviteInfo> financeUserInviteInfoPage = new Page<>(pageSize,
            (long) pageNum);
        Map<String, Object> parameters = Maps.newHashMap();
        if (Objects.nonNull(parentUserIds) && parentUserIds.length > 0) {
            parameters.put("parentUserIds", parentUserIds);
            int count = financeUserInviteInfoMapper.count(parameters);
            financeUserInviteInfoPage.setTotalCount((long) count);
            if (count > 0) {
                parameters.put("page", financeUserInviteInfoPage);
                List<FinanceUserInviteInfo> financeUserInviteInfoList = financeUserInviteInfoMapper
                    .query(parameters);
                financeUserInviteInfoPage.setDataList(financeUserInviteInfoList);
            }
        }

        return financeUserInviteInfoPage;
    }

    @Override
    public List<FinanceUserInviteInfo> queryByCondition(Map<String, Object> parameters) {
        return financeUserInviteInfoMapper.query(parameters);
    }

    /**
     * 更新支付金币标志
     *
     * @param userId 用户id
     * @return int
     */
    @Override
    public int updatePayCoinFlag(Long userId) {
        return financeUserInviteInfoMapper.updatePayCoinFlag(userId);
    }

    @Override
    public UserInviteInfo queryByCondition(Long userId) {
        UserInviteInfo userInviteInfo = null;
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("userId", userId);
        List<FinanceUserInviteInfo> userInviteInfoList = financeUserInviteInfoMapper
            .query(parameters);
        if (CollectionUtils.isNotEmpty(userInviteInfoList)) {
            userInviteInfo = UserInviteInfoConverter.convert(userInviteInfoList.get(0));
        }

        return userInviteInfo;
    }

}
