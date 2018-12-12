package cn.zhishush.finance.domainservice.service.user.query.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import cn.zhishush.finance.domainservice.repository.log.OperationLogRepository;
import cn.zhishush.finance.domainservice.repository.user.UserInfoRepository;
import cn.zhishush.finance.domainservice.repository.user.UserInviteInfoRepository;
import cn.zhishush.finance.core.dal.dataobject.user.UserInviteInfoDO;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.core.common.util.CommonUtils;
import cn.zhishush.finance.domain.log.OperationLog;
import cn.zhishush.finance.domain.user.UserInfo;
import cn.zhishush.finance.domainservice.service.user.query.UserInfoQueryService;

/**
 * <p>
 * 用户信息查询
 * </p>
 *
 * @author lili
 * @version 1.0: UserInfoQueryServiceImpl.java, v 0.1 2018/9/27 下午9:20 lili Exp
 *          $
 */
@Slf4j
@Service("userInfoQueryService")
public class UserInfoQueryServiceImpl implements UserInfoQueryService {

	@Resource
	private UserInviteInfoRepository userInviteInfoRepository;
	@Resource
	private UserInfoRepository userInfoRepository;
	@Resource
	private OperationLogRepository operationLogRepository;

	/**
	 * 查询用户邀请的好友，在两天之内没有做任何操作的
	 *
	 * @param userId
	 *            用户id
	 * @param pageNum
	 *            第几页
	 * @param pageSize
	 *            每页记录数
	 * @return Page<UserInfo>
	 */
	@Override
	public Page<UserInfo> querySleepUserInfo(Long userId, int pageNum, int pageSize) {
		Page<UserInfo> responsePage = new Page<>(pageSize, (long) pageNum);
		// 查询用户的所有邀请好友
		List<OperationLog> operationLogList = operationLogRepository.queryLatestLog(userId, Lists.newArrayList(),
				pageNum, pageSize);
		responsePage.setTotalCount((long) operationLogList.size());
		if (CollectionUtils.isNotEmpty(operationLogList)) {
			List<Long> userIds = operationLogList.stream().map(OperationLog::getUserId).collect(Collectors.toList());
			List<UserInfo> userInfoList = userInfoRepository.queryByCondition(userIds);
			// 查询用户是否支付金币显示手机号码
			Map<String, Object> parameters = Maps.newHashMap();
			parameters.put("userIds", userIds);
			List<UserInviteInfoDO> userInviteInfoList = userInviteInfoRepository.queryByCondition(parameters);
			if (CollectionUtils.isNotEmpty(userInfoList)) {
				userInfoList.forEach(userInfo -> {
					List<UserInviteInfoDO> userInviteInfos = userInviteInfoList.stream()
							.filter(userInviteInfo -> userInviteInfo.getUserId().equals(userInfo.getId()))
							.collect(Collectors.toList());
					if (CollectionUtils.isNotEmpty(userInviteInfos)) {
						UserInviteInfoDO f = userInviteInfos.get(0);
						if (!f.getIsPayCoin().equals("Y")) {
							userInfo.setMobileNum(CommonUtils.mobileEncrypt(userInfo.getMobileNum()));
							userInfo.setPayCoin(false);
						} else {
							userInfo.setPayCoin(true);
						}
					}

				});
				responsePage.setDataList(userInfoList);
			}
		}
		return responsePage;
	}
}
