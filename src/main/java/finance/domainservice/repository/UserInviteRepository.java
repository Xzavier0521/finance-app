package finance.domainservice.repository;

import java.util.List;
import java.util.Map;

import finance.api.model.base.Page;
import finance.domain.user.UserInviteInfo;
import finance.core.dal.dataobject.FinanceUserInviteInfo;

/**
 *  <p>用户邀请信息</p>
 * @author  lili
 * @version :1.0  UserInviteRepository.java.java, v 0.1 2018/9/27 下午8:40 lili Exp $
 */
public interface UserInviteRepository {

    /**
     * 分页查询邀请信息列表
     * @param parameters 查询参数
     * @return FinanceUserInviteInfo
     */
    List<FinanceUserInviteInfo> query(Map<String, Object> parameters);

    /**
     * 查询总记录数
     * @param parameters 查询参数
     * @return int
     */
    int count(Map<String, Object> parameters);

    /**
     *  查询用户下面的二级用户总数
     * @param userId 用户id
     * @return int
     */
    int countSecondLevelInviteUser(Long userId);

    Long countFirstInviteNum(Long userId);

    List<FinanceUserInviteInfo> selectFirstInviteUsers(Long userId);

    /**
     *  查询用户下面的二级用户
     * @return int
     */
    Page<FinanceUserInviteInfo> querySecondLevelInviteUser(Long userId, int pageNum, int pageSize);

    /**
     * 根据邀请人用户id 查询邀请关系
     * @param pageNum  第几页∂
     * @param pageSize  每页记录数
     * @param parentUserIds 邀请人用户id 列表
     * @return Page<FinanceUserInviteInfo>
     */
    Page<FinanceUserInviteInfo> queryByCondition(int pageNum, int pageSize, Long... parentUserIds);

    List<FinanceUserInviteInfo> queryByCondition(Map<String, Object> parameters);

    /**
     * 更新支付金币标志
     * @param userId 用户id
     * @return int
     */
    int updatePayCoinFlag(Long userId);

    /**
     * 查询用户邀请信息
     * @param userId 用户id
     * @return UserInviteInfo
     */
    UserInviteInfo queryByCondition(Long userId);
}
