package finance.domain.dto;

/**
 * 第三方账号绑定参数接受类.
 * @author hewenbin
 * @version v1.0 2018年8月17日 上午11:08:14 hewenbin
 */
public class ThirdBindParamDto {
	
	/** 1：金融家*/
	private String appId;
	/** 手机操作系统*/
	private String osType;
	/** 第三方渠道:qq、wechat*/
	private String channel;
	/** channel == wechat 时，wechat给的临时票据*/
	private String code;
	/** channel == qq 时，qq给的用户openId*/
	private String openId;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
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

	@Override
	public String toString() {
		return "ThirdBindParamDto{" +
				"appId='" + appId + '\'' +
				", osType='" + osType + '\'' +
				", channel='" + channel + '\'' +
				", code='" + code + '\'' +
				", openId='" + openId + '\'' +
				'}';
	}

}
