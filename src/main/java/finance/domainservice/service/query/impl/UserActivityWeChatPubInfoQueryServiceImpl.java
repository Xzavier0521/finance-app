package finance.domainservice.service.query.impl;

import java.util.Objects;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import finance.domain.weixin.UserActivityWeChatPubInfo;
import finance.domain.weixin.WeChatSubscribeInfo;
import finance.domainservice.repository.InviteOpenInfoRepository;
import finance.domainservice.service.query.UserActivityWeChatPubInfoQueryService;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: UserActivityWeChatPubInfoQueryServiceImpl.java, v0.1 2018/11/7
 *          4:39 PM lili Exp $
 */
@Slf4j
@Service("userActivityWeChatPubInfoQueryService")
public class UserActivityWeChatPubInfoQueryServiceImpl implements UserActivityWeChatPubInfoQueryService {

	@Resource
	private InviteOpenInfoRepository inviteOpenInfoRepository;

	/**
	 * 用户活动微信数据
	 *
	 * @param activityCode
	 *            活动代码
	 * @param inviteCode
	 *            邀请码
	 * @return UserActivityWeChatPubInfo
	 */
	@Override
	public UserActivityWeChatPubInfo query(String activityCode, String inviteCode) {
		UserActivityWeChatPubInfo userActivityWeChatPubInfo = new UserActivityWeChatPubInfo();
		WeChatSubscribeInfo weChatSubscribeInfo = inviteOpenInfoRepository.countSubscribeInfo(inviteCode);
		userActivityWeChatPubInfo.setActivityCode(activityCode);
		if (Objects.nonNull(weChatSubscribeInfo)) {
			userActivityWeChatPubInfo
					.setHistorySubscribeNum(Objects.nonNull(weChatSubscribeInfo.getHistorySubscribeNum())
							? weChatSubscribeInfo.getHistorySubscribeNum()
							: 0L);
			userActivityWeChatPubInfo.setSubscribeNum(Objects.nonNull(weChatSubscribeInfo.getSubscribeNum())
					? weChatSubscribeInfo.getSubscribeNum()
					: 0L);
			userActivityWeChatPubInfo.setUnsubscribeNum(Objects.nonNull(weChatSubscribeInfo.getUnsubscribeNum())
					? weChatSubscribeInfo.getUnsubscribeNum()
					: 0L);
		} else {
			userActivityWeChatPubInfo.setHistorySubscribeNum(0L);
			userActivityWeChatPubInfo.setSubscribeNum(0L);
			userActivityWeChatPubInfo.setUnsubscribeNum(0L);
		}
		return userActivityWeChatPubInfo;
	}

	/**
	 * 查询用户本次活动取消关注数
	 *
	 * @param activityCode
	 *            活动代码
	 * @param inviteCode
	 *            邀请码
	 * @param userId
	 *            用户id
	 * @return Long
	 */
	@Override
	public Long queryUnsubscribeNum(String activityCode, String inviteCode, Long userId) {
		return inviteOpenInfoRepository.countUnSubscribeNum(inviteCode, userId);
	}
}
