package cn.zhishush.finance.domainservice.service.activity.impl;

import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import cn.zhishush.finance.domainservice.repository.coin.CoinLogRepository;
import cn.zhishush.finance.domainservice.repository.third.ThirdAccountInfoRepository;
import cn.zhishush.finance.domainservice.repository.user.UserInfoRepository;
import cn.zhishush.finance.domainservice.repository.user.UserInviteInfoRepository;
import cn.zhishush.finance.domainservice.repository.weixin.WeiXinMessageTemplateRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cn.zhishush.finance.core.common.enums.WeiXinMessageTemplateCodeEnum;
import cn.zhishush.finance.domain.dto.LoginParamDto;
import cn.zhishush.finance.domain.user.ThirdAccountInfo;
import cn.zhishush.finance.domain.user.UserInfo;
import cn.zhishush.finance.domain.user.UserInviteInfo;
import cn.zhishush.finance.domain.weixin.WeiXinMessageTemplate;
import cn.zhishush.finance.domainservice.service.activity.RedEnvelopeRainRegisterRewardService;
import cn.zhishush.finance.domainservice.service.wechat.WeiXinTemplateMessageSendService;

/**
 * <p>红包雨活动邀请注册金额奖励</p>
 * @author lili
 * @version $Id: RedEnvelopeRainRegisterRewardServiceImpl.java, v0.1 2018/11/26 9:30 AM lili Exp $
 */
@Slf4j
@Service("redEnvelopeRainRegisterRewardService")
public class RedEnvelopeRainRegisterRewardServiceImpl implements RedEnvelopeRainRegisterRewardService {

	private static final Integer coinNum = 500;

	@Value("${red.envelope.rain.switch}")
	private String redEnvelopRainSwitch;

	@Resource
	private CoinLogRepository coinLogRepository;

	@Resource
	private UserInfoRepository userInfoRepository;

	@Resource
	private UserInviteInfoRepository userInviteInfoRepository;

	@Resource
	private WeiXinTemplateMessageSendService weiXinTemplateMessageSendService;

	@Resource
	private WeiXinMessageTemplateRepository weiXinMessageTemplateRepository;

	@Resource
	private ThirdAccountInfoRepository thirdAccountInfoRepository;

	@Override
	public void process(UserInfo userInfo, LoginParamDto paramDto) {
		log.info("红包雨活动邀请用户注册奖励");
		if (!"1".equals(redEnvelopRainSwitch)) {
			log.info("红包雨活动已经结束");
			return;
		}
		// 查询用户邀请关系
		UserInviteInfo userInviteInfo = userInviteInfoRepository.queryByCondition(userInfo.getId());
		if (Objects.isNull(userInviteInfo)) {
			log.info("用户:{}无邀请关系，不发放金币奖励!", userInfo.getMobileNum());
			return;
		}
		coinLogRepository.save(userInviteInfo.getParentUserId(), coinNum, "红包雨活动邀请好友注册奖励");
		WeiXinMessageTemplate weiXinMessageTemplate = weiXinMessageTemplateRepository
				.query(WeiXinMessageTemplateCodeEnum.RED_ENVELOPE_RAIN_INVITE_REWARD_NOTICE.getCode());
		if (Objects.isNull(weiXinMessageTemplate)) {
			log.info("微信消息模版不存在，不发送模版消息！");
		}
		ThirdAccountInfo thirdAccountInfo = thirdAccountInfoRepository
				.queryByCondition(userInviteInfo.getParentUserId());
		UserInfo parentUserInfo = userInfoRepository
				.queryByCondition(Lists.newArrayList(userInviteInfo.getParentUserId())).get(0);
		if (Objects.isNull(thirdAccountInfo)) {
			log.info("用户:{}无open_info不发送模版消息!", parentUserInfo.getMobileNum());
			return;
		}
		Map<String, String> parameters = Maps.newHashMap();
		parameters.put("coinNum", String.valueOf(coinNum));
		weiXinTemplateMessageSendService.send(parentUserInfo, thirdAccountInfo, weiXinMessageTemplate, parameters);
	}
}
