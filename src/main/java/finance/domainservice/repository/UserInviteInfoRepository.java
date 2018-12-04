package finance.domainservice.repository;

import java.util.List;
import java.util.Map;

import finance.api.model.base.Page;
import finance.core.dal.dataobject.UserInviteInfoDO;
import finance.domain.user.UserInviteInfo;

/**
 * <p>用户邀请信息</p>
 * @author lili
 * @version 1.0: UserInviteInfoRepository.java, v0.1 2018/11/26 10:21 AM lili Exp $
 */
public interface UserInviteInfoRepository {

    /**
     * 分页查询邀请信息列表
     * @param parameters  查询参数
     * @return UserInviteInfoDO
     */
    List<UserInviteInfoDO> query(Map<String, Object> parameters);

    /**
     * 查询总记录数
     * @param parameters  查询参数
     * @return int
     */
    int count(Map<String, Object> parameters);

    /**
     * 查询用户下面的二级用户总数
     * @param userId 用户id
     * @return int
     */
    int countSecondLevelInviteUser(Long userId);

    Long countFirstInviteNum(Long userId);

    List<UserInviteInfoDO> selectFirstInviteUsers(Long userId);

    /**
     * 查询用户下面的二级用户
     * @return int
     */
    Page<UserInviteInfoDO> querySecondLevelInviteUser(Long userId, int pageNum, int pageSize);

    /**
     * 根据邀请人用户id 查询邀请关系
     * @param pageNum 第几页
     * @param pageSize 每页记录数
     * @param parentUserIds 邀请人用户id 列表
     * @return Page<UserInviteInfoDO>
     */
    Page<UserInviteInfoDO> queryByCondition(int pageNum, int pageSize, Long... parentUserIds);

    List<UserInviteInfoDO> queryByCondition(Map<String, Object> parameters);

    /**
     * 更新支付金币标志
     * @param userId  用户id
     * @return int
     */
    int updatePayCoinFlag(Long userId);

    /**
     * 查询用户邀请信息
     * @param userId 用户id
     * @return UserInviteInfo
     */
    UserInviteInfo queryByCondition(Long userId);

    /**
     * 查询用户一级邀请人数
     * @param parentUserId 用户id
     * @param activityCode 活动代码
     * @return Long
     */
    Long countFirstInviteNum(Long parentUserId, String activityCode);
}
