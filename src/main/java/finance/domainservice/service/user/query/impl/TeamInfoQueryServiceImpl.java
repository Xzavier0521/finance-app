package finance.domainservice.service.user.query.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import finance.api.model.base.Page;
import finance.domain.coin.CardInfo;
import finance.domain.team.FirstLevelTeamUserInfo;
import finance.domain.team.SecondLevelTeamUserInfo;
import finance.domain.team.TeamInfoQueryResult;
import finance.domain.user.UserInfo;
import finance.domainservice.repository.UserInviteInfoRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import finance.core.common.enums.CustomerQueryTypeEnum;
import finance.core.common.util.ConvertBeanUtil;
import finance.domainservice.repository.CardInfoRepository;
import finance.domainservice.repository.UserInfoRepository;
import finance.domainservice.service.user.query.TeamInfoQueryService;
import finance.core.dal.dataobject.UserInviteInfoDO;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: TeamInfoQueryServiceImpl.java, v0.1 2018/10/5 8:07 PM lili Exp
 *          $
 */
@Service("teamInfoQueryService")
public class TeamInfoQueryServiceImpl implements TeamInfoQueryService {

	@Resource
	private UserInviteInfoRepository userInviteInfoRepository;

	@Resource
	private UserInfoRepository userInfoRepository;

	@Resource
	private CardInfoRepository cardInfoRepository;

	/**
	 * 查询用户的好友列表-一级和二级用户
	 * 
	 * @param userId
	 *            用户id
	 * @param type
	 *            用户类型
	 * @param maxCount
	 *            最大记录数
	 * @return TeamInfoQueryResult
	 */
	@Override
	public TeamInfoQueryResult queryTeamUserList(Long userId, CustomerQueryTypeEnum type, int maxCount) {
		TeamInfoQueryResult result = new TeamInfoQueryResult();
		Map<String, Object> parameters = Maps.newHashMap();
		parameters.put("parentUserIds", Lists.newArrayList(userId));
		// 查询一级用户
		// 查询总数
		int firstLevelUserCount = userInviteInfoRepository.count(parameters);
		result.setFirstLevelCount(firstLevelUserCount);
		if (firstLevelUserCount == 0) {
			result.setSecondLevelCount(firstLevelUserCount);
			result.setAllLevelCount(firstLevelUserCount);
			return result;
		}
		// 查询明细
		List<UserInviteInfoDO> firstLevelInviteInfoList = userInviteInfoRepository
				.queryByCondition(1, maxCount, userId).getDataList();
		// 一级用户id列表
		List<Long> firstLevelUserIdList = firstLevelInviteInfoList.stream().map(UserInviteInfoDO::getUserId)
				.collect(Collectors.toList());
		List<SecondLevelTeamUserInfo> firstLevelUserInfoList = buildTeamUserInfo(firstLevelInviteInfoList);
		result.setFirstLevelUserList(firstLevelUserInfoList);
		// 类型为-一级用户不需要查询二级用户
		if (CustomerQueryTypeEnum.FIRST_LEVEL == type) {
			return result;
		}
		// 查询二级用户
		parameters.put("parentUserIds", firstLevelUserIdList);
		// 查询总数
		int secondLevelUserCount = userInviteInfoRepository.countSecondLevelInviteUser(userId);
		result.setSecondLevelCount(secondLevelUserCount);
		// 无二级用户
		if (secondLevelUserCount == 0) {
			result.setAllLevelCount(secondLevelUserCount + firstLevelUserCount);
			List<FirstLevelTeamUserInfo> allTeamUserInfoList = Lists.newArrayListWithCapacity(firstLevelUserCount);
			FirstLevelTeamUserInfo firstTeamUserInfo;
			for (SecondLevelTeamUserInfo secondTeamUserInfo : firstLevelUserInfoList) {
				firstTeamUserInfo = new FirstLevelTeamUserInfo();
				ConvertBeanUtil.copyBeanProperties(secondTeamUserInfo, firstTeamUserInfo);
				allTeamUserInfoList.add(firstTeamUserInfo);
			}
			result.setAllLevelUserList(allTeamUserInfoList);
			return result;
		}
		// 查询明细
		List<UserInviteInfoDO> secondLevelInviteInfoList = userInviteInfoRepository
				.querySecondLevelInviteUser(userId, 1, maxCount).getDataList();
		// 二级用户id列表
		List<SecondLevelTeamUserInfo> secondLevelUserInfoList = buildTeamUserInfo(secondLevelInviteInfoList);
		result.setSecondLevelUserList(secondLevelUserInfoList);
		// 所有一级和二级用户
		int allLevelUserCount = firstLevelUserCount + secondLevelUserCount;
		result.setAllLevelCount(allLevelUserCount);
		// 所有用户
		if (CustomerQueryTypeEnum.ALl == type) {
			List<FirstLevelTeamUserInfo> allTeamUserInfoList = Lists.newArrayListWithCapacity(firstLevelUserCount);
			Long[] parentUserIds = new Long[firstLevelUserIdList.size()];
			firstLevelUserIdList.toArray(parentUserIds);
			// 一级用户对应的二级用户
			Page<UserInviteInfoDO> userInviteInfoPage = userInviteInfoRepository.queryByCondition(1, 300,
					parentUserIds);
			List<SecondLevelTeamUserInfo> secondTeamUserInfoList = Lists.newArrayList();
			if (userInviteInfoPage.getTotalCount() > 0) {
				List<UserInviteInfoDO> financeUserInviteInfoList = userInviteInfoPage.getDataList();
				secondTeamUserInfoList = buildTeamUserInfo(financeUserInviteInfoList);
			}
			FirstLevelTeamUserInfo allTeamUserInfo;
			for (SecondLevelTeamUserInfo firstTeamUserInfo : firstLevelUserInfoList) {
				allTeamUserInfo = new FirstLevelTeamUserInfo();
				ConvertBeanUtil.copyBeanProperties(firstTeamUserInfo, allTeamUserInfo);
				List<SecondLevelTeamUserInfo> secondLevelTeamUserInfoList = secondTeamUserInfoList.stream()
						.filter(secondLevelTeamUserInfo -> secondLevelTeamUserInfo.getParentUserId()
								.equals(firstTeamUserInfo.getUserId()))
						.collect(Collectors.toList());
				if (CollectionUtils.isNotEmpty(secondLevelTeamUserInfoList)) {
					allTeamUserInfo.setInviteList(secondLevelTeamUserInfoList);
				}
				allTeamUserInfoList.add(allTeamUserInfo);
			}

			result.setAllLevelUserList(allTeamUserInfoList);
		}

		return result;
	}

	private List<SecondLevelTeamUserInfo> buildTeamUserInfo(List<UserInviteInfoDO> userInviteInfoList) {
		List<Long> userIdList = userInviteInfoList.stream().map(UserInviteInfoDO::getUserId)
				.collect(Collectors.toList());
		List<SecondLevelTeamUserInfo> teamUserInfoList = Lists.newArrayListWithCapacity(userIdList.size());
		// 用户信息
		List<UserInfo> userInfoList = userInfoRepository.queryByCondition(userIdList);
		// 卡信息
		List<CardInfo> cardInfoList = cardInfoRepository.selectByUserIdList(userIdList);
		userInfoList.forEach(userInfo -> {
			List<CardInfo> cardInfos = cardInfoList.stream()
					.filter(cardInfo -> cardInfo.getId().equals(userInfo.getId())).collect(Collectors.toList());
			String userName = "***";
			if (CollectionUtils.isNotEmpty(cardInfos)) {
				if (StringUtils.isNotBlank(cardInfos.get(0).getRealName())) {
					userName = cardInfos.get(0).getRealName();
				}
			}
			UserInviteInfoDO userInviteInfo = userInviteInfoList.stream()
					.filter(financeUserInviteInfo -> financeUserInviteInfo.getUserId().equals(userInfo.getId()))
					.collect(Collectors.toList()).get(0);
			teamUserInfoList.add(SecondLevelTeamUserInfo.builder().userId(userInfo.getId())
					.parentUserId(userInviteInfo.getParentUserId()).mobileNum(userInfo.getMobileNum())
					.registerDate(userInfo.getRegisterTime()).name(userName).build());
		});
		teamUserInfoList.sort(Comparator.comparing(SecondLevelTeamUserInfo::getUserId).reversed());
		return teamUserInfoList;
	}
}
