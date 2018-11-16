package finance.domainservice.service.user.query;

import finance.api.model.base.Page;
import finance.domain.team.InviteInfoAndIncome;

/**
 * 用户邀请信息查询
 *
 * @author lili
 * @version v1.0 2018年9月27日 下午8:58:57 lili
 */
public interface UserInviteQueryService {

    /**
     * 查询用户的邀请好友信息带收益
     * @param userId 用户id
     * @param pageSize 第几页
     * @param pageNum 每页记录数
     * @return Page<InviteInfoAndIncome>
     */
    Page<InviteInfoAndIncome> queryInviteInfoAndIncome(Long userId, int pageSize, int pageNum);

}
