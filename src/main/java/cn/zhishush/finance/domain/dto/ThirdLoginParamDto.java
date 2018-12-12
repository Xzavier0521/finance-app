package cn.zhishush.finance.domain.dto;

import org.springframework.util.StringUtils;

import cn.zhishush.finance.core.common.enums.ThirdLoginChannel;

/**
 * 第三方账号登录参数接收类.
 * 
 * @author hewenbin
 * @version v1.0 2018年8月16日 下午1:51:32 hewenbin
 */
public class ThirdLoginParamDto {

	/** 1：金融家 */
	private String zsAppId;
	/** 手机操作系统 */
	private String osType;
	/** 第三方渠道:qq、wechat */
	private String channel;
	/** channel == wechat 时，wechat给的临时票据 */
	private String code;
	/** channel == qq 时，qq给的用户openId */
	private String openId;
	private String platformCode;
	private String platformDetail;

	public Boolean validateParam() {
		// 公共验证
		if (StringUtils.isEmpty(channel) || StringUtils.isEmpty(zsAppId) || StringUtils.isEmpty(osType)) {
			return false;
		}

		// 不同的登录类型做不同的验证
		if (ThirdLoginChannel.qq.toString().equals(channel) && StringUtils.isEmpty(openId)) {
			return false;
		}
		if (ThirdLoginChannel.wechat.toString().equals(channel) && StringUtils.isEmpty(code)) {
			return false;
		}
		return true;
	}

	public String getZsAppId() {
		return zsAppId;
	}

	public void setZsAppId(String zsAppId) {
		this.zsAppId = zsAppId;
	}

	public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getPlatformCode() {
		return platformCode;
	}

	public void setPlatformCode(String platformCode) {
		this.platformCode = platformCode;
	}

	public String getPlatformDetail() {
		return platformDetail;
	}

	public void setPlatformDetail(String platformDetail) {
		this.platformDetail = platformDetail;
	}

	@Override
	public String toString() {
		return "ThirdLoginParamDto{" + "zsAppId='" + zsAppId + '\'' + ", osType='" + osType + '\'' + ", channel='"
				+ channel + '\'' + ", code='" + code + '\'' + ", openId='" + openId + '\'' + ", platformCode='"
				+ platformCode + '\'' + ", platformDetail='" + platformDetail + '\'' + '}';
	}

}
