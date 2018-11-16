package finance.domainservice.repository;

import java.util.List;

import finance.api.model.base.Page;
import finance.api.model.condition.RedEnvelopeDetailQueryCondition;
import finance.core.common.enums.LeaderBoardTypeEnum;
import finance.domain.activity.LeaderBoard;
import finance.domain.activity.ParticipantInfo;

/**
 * <p>红包活动查询</p>
 * @author lili
 * @version $Id: LeaderBoardRepository.java, v0.1 2018/10/19 8:29 PM lili Exp $
 */
public interface RedEnvelopeRepository {

    /**
     * 根据类型查询排行榜
     * @param activityCode 活动类型 暂时固定为-0001
     * @param leaderBoardType 榜单类型
     * @param num 排行榜人数
     * @return List<LeaderBoard> 
     */
    List<LeaderBoard> queryByType(LeaderBoardTypeEnum leaderBoardType, String activityCode,
                                  int num);

    /**
     * 查询红包邀请人数
     * @param activityCode 活动代码
     * @return Long
     */
    Long queryJoinNum(String activityCode);

    /**
     *  分页查询红包活动明细
     * @param condition 查询条件
     * @return Page<ParticipantInfo>
     */
    Page<ParticipantInfo> queryDetail4Page(RedEnvelopeDetailQueryCondition condition);

    /**
     * 查询单个用户的红包互动明细
     * @param userId  用户id
     * @param activityCode 活动代码
     * @return ParticipantInfo
     */
    ParticipantInfo querySingleDetail(Long userId, String activityCode);
}
