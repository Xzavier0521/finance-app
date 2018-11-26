package finance.domainservice.service.userinfo.impl;

import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import finance.core.common.enums.ThirdLoginChannel;
import finance.core.dal.dataobject.UserInviteInfoDO;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.common.collect.Maps;

import finance.api.model.response.ResponseResult;
import finance.core.common.enums.CodeEnum;
import finance.domain.weixin.InviteOpenInfo;
import finance.domain.user.UserInfo;
import finance.domain.dto.ThirdLoginParamDto;
import finance.domainservice.repository.InviteOpenInfoRepository;
import finance.domainservice.repository.UserInfoRepository;
import finance.domainservice.service.user.ThirdBindService;
import finance.domainservice.service.userinfo.ThirdBindBiz;
import finance.domainservice.service.userinfo.UserInfoBiz;
import finance.core.dal.dao.UserInviteInfoDAO;

/**
 * <p>
 * 注释
 * </p>
 *
 * @author lili
 * @version $Id: ThirdBindBizImpl.java, v0.1 2018/10/23 5:01 PM lili Exp $
 */
@Slf4j
@Service
public class ThirdBindBizImpl implements ThirdBindBiz {

	@Resource
	private UserInfoBiz userInfoBiz;
	@Resource
	private ThirdBindService thirdBindService;
	@Resource
	private StringRedisTemplate stringRedisTemplate;

	@Value("${third.openId.cache.timeout.minute}")
	private Long openIdCacheTimeout;
	@Value("${third.openId.cacke.key.prefix}")
	private String openIdCacheKeyPrefix;

	@Resource
	private InviteOpenInfoRepository inviteOpenInfoRepository;

	@Resource
	private UserInfoRepository userInfoRepository;

	@Resource
	private UserInviteInfoDAO inviteInfoMapper;

	@Override
	public ResponseResult<Boolean> bindUser(Long userId, ThirdLoginParamDto bindDto) {
		String channel = bindDto.getChannel();
		String code = bindDto.getCode();
		String openId = bindDto.getOpenId();
		if (ThirdLoginChannel.wechat.toString().equals(channel)) {
			openId = thirdBindService.queryOpenInfo(channel, code, openId);
		}
		if ("wechatPub".equals(channel)) {
			openId = thirdBindService.queryOpenInfo(channel, code, openId);
		}
		if (StringUtils.isEmpty(openId)) {
			return ResponseResult.error(CodeEnum.getOpenIdFail);
		}
		ResponseResult<Boolean> res = thirdBindService.bindUser(userId, openId, channel, "");

		if (Objects.nonNull(res)) {
			if (res.getErrorCode().equals(CodeEnum.succ.getCode())) {
				process(userId, openId);
			} else {
				log.info(res.getErrorMessage());
			}

		}
		return res;
	}

	private void process(Long userId, String openId) {

		if (org.apache.commons.lang3.StringUtils.isNotBlank(openId)) {
			Map<String, Object> param = Maps.newHashMap();
			param.put("openId", openId);
			List<InviteOpenInfo> inviteOpenInfoList = inviteOpenInfoRepository.queryByCondition(param);
			if (CollectionUtils.isNotEmpty(inviteOpenInfoList)) {
				InviteOpenInfo inviteOpenInfo = inviteOpenInfoList.get(0);
				// 根据邀请码查询用户信息
				UserInfo parentUserInfo = userInfoRepository.queryByCondition(inviteOpenInfo.getInviteCode());
				if (Objects.nonNull(parentUserInfo)) {
					//
					Map<String, Object> parameters = Maps.newHashMap();
					parameters.put("userId", userId);
					parameters.put("parentUserId", parentUserInfo.getId());
					List<UserInviteInfoDO> inviteInfoList = inviteInfoMapper.query(parameters);
					if (CollectionUtils.isEmpty(inviteInfoList)) {
						// 查询user_id 邀请关系是否已经建立
						Map<String, Object> queryParameters = Maps.newHashMap();
						queryParameters.put("userId", userId);
						List<UserInviteInfoDO> inviteInfos = inviteInfoMapper.query(queryParameters);
						if (CollectionUtils.isEmpty(inviteInfos)) {
							UserInviteInfoDO inviteInfo = new UserInviteInfoDO();
							// 保存
							inviteInfo.setParentUserId(parentUserInfo.getId());
							inviteInfo.setUserId(userId);
							// 新增活动类型
							inviteInfo.setInviteType(1);
							inviteInfoMapper.insertSelective(inviteInfo);
						} else {
							log.info("user_id:{}邀请关系已经存在!", userId);
						}
					} else {
						log.info("parentUserId:{},userId:{}邀请关系已经存在!", parentUserInfo.getId(), userId);
					}
				} else {
					log.info("invite_code:{},查询用户信息无记录", inviteOpenInfo.getInviteCode());
				}
			} else {
				log.info("根据 open_id:{} 查询绑定关系无记录", openId);
			}
		} else {
			log.info("用户:{} 无open_id不生成邀请关系", userId);
		}
	}

	@Override
	public void unBindUser(Long userId, String channel) {
		thirdBindService.unBindUser(userId, channel);
	}

	@Override
	public ResponseResult<Map<String, String>> queryOpenId(String channel, String code, String qqOpenId) {
		// 获取 OpenId
		String openId = thirdBindService.queryOpenInfo(channel, code, qqOpenId);
		if (StringUtils.isEmpty(openId)) {
			return ResponseResult.error(CodeEnum.getOpenIdFail);
		}
		// 临时保存openId，10分钟过期，然后然后返回openId 对应的 KEY 返回给前端（在登录绑定时同时绑定该openId）
		String openKey = UUID.randomUUID().toString();
		Map<String, String> resMap = new HashMap<>();
		stringRedisTemplate.opsForValue().set(openIdCacheKeyPrefix + openKey, openId, openIdCacheTimeout,
				TimeUnit.MINUTES);
		resMap.put("openId", openId);
		// 是否关注微信公众号
		resMap.put("isFlowWeChatPub", userInfoBiz.isFlowWeChatPub(openId).toString());
		return ResponseResult.success(resMap);

	}
}
