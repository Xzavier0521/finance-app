package finance.domainservice.repository.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import finance.api.model.base.Page;
import finance.api.model.condition.RedEnvelopeDetailQueryCondition;
import finance.core.common.enums.LeaderBoardTypeEnum;
import finance.domain.LeaderBoard;
import finance.domain.ParticipantInfo;
import finance.domainservice.converter.LeaderBoardConverter;
import finance.domainservice.converter.ParticipantInfoConverter;
import finance.domainservice.repository.RedEnvelopeRepository;
import finance.mapper.RedEnvelopeDAO;
import finance.model.po.ParticipantInfoDO;

/**
 * <p>排行榜</p>
 * @author lili
 * @version $Id: LeaderBoardRepositoryImpl.java, v0.1 2018/10/19 8:41 PM lili Exp $
 */
@Slf4j
@Repository("leaderBoardRepository")
public class RedEnvelopeRepositoryImpl implements RedEnvelopeRepository {

    @Resource
    private RedEnvelopeDAO redEnvelopeDAO;

    /**
     * 根据类型查询排行榜
     * @param leaderBoardType 榜单类型
     * @param activityCode 活动代码
     * @param num 排行榜人数
     * @return List<LeaderBoard>
     */
    @Override
    public List<LeaderBoard> queryByType(LeaderBoardTypeEnum leaderBoardType, String activityCode,
                                         int num) {

        List<LeaderBoard> leaderBoards = Lists.newArrayList();
        switch (leaderBoardType) {
            case ALL_LEVEL:
                leaderBoards = LeaderBoardConverter
                    .convert2List(redEnvelopeDAO.queryTotalRanking(activityCode, num));
                break;
            case FIRST_LEVEL:
                leaderBoards = LeaderBoardConverter
                    .convert2List(redEnvelopeDAO.queryFirstRanking(activityCode, num));
                break;
            case SECOND_LEVEL:
                leaderBoards = LeaderBoardConverter
                    .convert2List(redEnvelopeDAO.querySecondRanking(activityCode, num));
            default:
        }
        return leaderBoards;
    }

    @Override
    public Long queryJoinNum(String activityCode) {
        return redEnvelopeDAO.queryJoinNum(activityCode);
    }

    @Override
    public Page<ParticipantInfo> queryDetail4Page(RedEnvelopeDetailQueryCondition condition) {
        Map<String, Object> parameters = Maps.newHashMap();
        Page<ParticipantInfo> page = new Page<>(condition.getPageSize(),
            (long) condition.getCurrentPage());
        parameters.put("page", page);
        parameters.put("activityCode", condition.getActivityCode());
        Long totalCount = redEnvelopeDAO.countJoinNum(parameters);
        if (Objects.nonNull(totalCount) && totalCount > 0) {
            page.setTotalCount(totalCount);
            List<ParticipantInfoDO> participantInfoDOList = redEnvelopeDAO
                .queryJoinDetail4Page(parameters);
            page.setDataList(ParticipantInfoConverter.convert2List(participantInfoDOList));
        } else {
            page.setTotalCount(0L);
        }
        return page;
    }

    /**
     * 查询单个用户的红包互动明细
     *
     * @param userId       用户id
     * @param activityCode 活动代码
     * @return ParticipantInfo
     */
    @Override
    public ParticipantInfo querySingleDetail(Long userId, String activityCode) {
        ParticipantInfo participantInfo;
        participantInfo = ParticipantInfoConverter
            .convert(redEnvelopeDAO.querySingleDetail(userId));
        if (Objects.isNull(participantInfo)) {
            participantInfo = new ParticipantInfo();
            participantInfo.setSecondInviteNum(0L);
            participantInfo.setSecondRewardAmount(BigDecimal.ZERO);
            participantInfo.setSecondUnitPrice(BigDecimal.valueOf(0.2));
        }
        return participantInfo;
    }

}
