package finance.domain.dto;

import org.springframework.util.StringUtils;

import finance.core.common.constants.Constant;
import finance.core.common.enums.SmsUseType;

/**
 * 发送短信验证码参数接收类.
 * 
 * @author hewenbin
 * @version v1.0 2018年7月10日 下午2:42:46 hewenbin
 */
public class SendSmsCodeDto {

	private String mobileNum;
	private String useType;
	private String platformCode;
	private String imgCodeId;
	private String imgCode;

	public Boolean validateParam() {
		// 公共验证
		if (StringUtils.isEmpty(mobileNum) || StringUtils.isEmpty(useType) || !SmsUseType.contains(useType)) {
			return false;
		}
		// 不同的登录类型做不同的验证
		if (!SmsUseType.simpleRegist.toString().equals(useType)
				&& (StringUtils.isEmpty(imgCodeId) || imgCodeId.length() != 32 || StringUtils.isEmpty(imgCode)
						|| imgCode.length() != Constant.imgcode_length)) {
			return false;
		}
		return true;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	public String getImgCodeId() {
		return imgCodeId;
	}

	public void setImgCodeId(String imgCodeId) {
		this.imgCodeId = imgCodeId;
	}

	public String getImgCode() {
		return imgCode;
	}

	public void setImgCode(String imgCode) {
		this.imgCode = imgCode;
	}

	public String getPlatformCode() {
		return platformCode;
	}

	public void setPlatformCode(String platformCode) {
		this.platformCode = platformCode;
	}

	@Override
	public String toString() {
		return "SendSmsCodeDto{" + "mobileNum='" + mobileNum + '\'' + ", useType='" + useType + '\''
				+ ", platformCode='" + platformCode + '\'' + ", imgCodeId='" + imgCodeId + '\'' + ", imgCode='"
				+ imgCode + '\'' + '}';
	}

}
