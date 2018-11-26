package finance.domainservice.service.wechat.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

import finance.core.common.enums.ConcernStatusEnum;
import finance.domain.weixin.InviteOpenInfo;
import finance.domainservice.repository.InviteOpenInfoRepository;
import finance.domainservice.service.wechat.WeChatOpenInfoService;

/**
 * <p>
 * 微信公众号open_info和邀请代码绑定
 * </p>
 * 
 * @author lili
 * @version $Id: WeChatOpenInfoServiceImpl.java, v0.1 2018/10/31 6:27 PM lili
 *          Exp $
 */
@Slf4j
@Service("weChatOpenInfoService")
public class WeChatOpenInfoServiceImpl implements WeChatOpenInfoService {

	@Resource
	private InviteOpenInfoRepository inviteOpenInfoRepository;

	/**
	 * 保存用户open_info
	 *
	 * @param activityCode
	 *            活动代码
	 * @param inviteCode
	 *            邀请代码
	 * @param openId
	 *            open_id
	 */
	@Override
	public void save(String activityCode, String inviteCode, String openId) {
		log.info("[开始绑定邀请代码和open_info],请求参数,activityCode:{},inviteCode:{},openId:{}", activityCode, inviteCode, openId);
		this.process(activityCode, inviteCode, openId);
		log.info("[结束绑定邀请代码和open_info],请求参数,activityCode:{},inviteCode:{},openId:{}", activityCode, inviteCode, openId);
	}

	private void process(String activityCode, String inviteCode, String openId) {
		Map<String, Object> param = Maps.newHashMap();
		param.put("openId", openId);
		List<InviteOpenInfo> inviteOpenInfoList = inviteOpenInfoRepository.queryByCondition(param);
		if (CollectionUtils.isNotEmpty(inviteOpenInfoList)) {
			log.info("open_id:{},已经绑定了邀请关系,结束绑定邀请代码和open_info", openId);
			return;
		}
		inviteOpenInfoRepository.save(InviteOpenInfo.builder().activityCode(activityCode).inviteCode(inviteCode)
				.openId(openId).concernStatus(ConcernStatusEnum.SUBSCRIBE).build());
	}

	/**
	 * 查询微信公众号open_info和邀请代码绑定信息
	 *
	 * @param inviteCode
	 *            邀请代码
	 * @return InviteOpenInfo
	 */
	@Override
	public List<InviteOpenInfo> queryByInviteCode(String inviteCode) {
		Map<String, Object> param = Maps.newHashMap();
		param.put("inviteCode", inviteCode);
		return inviteOpenInfoRepository.queryByCondition(param);
	}

	/**
	 * 查询微信公众号open_info和邀请代码绑定信息
	 *
	 * @param openId
	 *            open_id
	 * @return InviteOpenInfo
	 */
	@Override
	public InviteOpenInfo queryByOpenId(String openId) {
		InviteOpenInfo inviteOpenInfo = null;
		Map<String, Object> param = Maps.newHashMap();
		param.put("openId", openId);
		List<InviteOpenInfo> inviteOpenInfoList = inviteOpenInfoRepository.queryByCondition(param);
		if (CollectionUtils.isNotEmpty(inviteOpenInfoList)) {
			inviteOpenInfo = inviteOpenInfoList.get(0);
		}
		return inviteOpenInfo;
	}
}
