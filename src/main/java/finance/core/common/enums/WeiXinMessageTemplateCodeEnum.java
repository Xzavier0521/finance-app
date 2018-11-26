package finance.core.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * 微信模版消息类型
 * </p>
 *
 * @author lili
 * @version 1.0: WeiXinMessageTemplateCodeEnum.java, v0.1 2018/10/24 11:13 AM
 *          lili Exp $
 */
public enum WeiXinMessageTemplateCodeEnum {

	/**
	 * 一级成员提醒
	 */
	FIRST_INVITE_NOTICE("first_invite_notice", "一级成员提醒"),
	/**
	 * 一级成员关注提醒
	 */
	FIRST_SUBSCRIBE_NOTICE("first_subscribe_notice", "一级成员关注提醒"),
	/**
	 * 二级成员提醒
	 */
	SECOND_INVITE_NOTICE("second_invite_notice",

			"二级成员提醒"),
	/**
	 * 金币发放提醒
	 */
	SEND_COIN_NOTICE("send_coin_notice", "金币发放提醒"),
	/**
	 * 红包雨活动提醒
	 */
	RED_ENVELOPE_RAIN_NOTICE("red_envelope_rain_notice", "红包雨活动提醒"),
	/**
	 * 红包雨活动邀请好友注册金币奖励提醒
	 */
	RED_ENVELOPE_RAIN_INVITE_REWARD_NOTICE("red_envelope_rain_invite_reward_notice", "红包雨活动邀请好友注册金币奖励提醒"),
	/**
	 * 佣金提醒
	 */
	BROKERAGE_ARRIVAL_NOTICE("brokerage_arrival_notice", "佣金提醒");
	private String code;

	private String desc;

	WeiXinMessageTemplateCodeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static WeiXinMessageTemplateCodeEnum getByCode(String code) {
		if (StringUtils.isBlank(code)) {
			return null;
		} else {
			WeiXinMessageTemplateCodeEnum[] var1 = values();
			for (WeiXinMessageTemplateCodeEnum value : var1) {
				if (value.code.equals(code)) {
					return value;
				}
			}
			return null;
		}
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
