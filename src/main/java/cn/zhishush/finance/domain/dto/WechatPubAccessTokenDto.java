package cn.zhishush.finance.domain.dto;

/**
 * 微信公众号获取access_token返回数据
 * 
 * @author panzhongkang
 * @date 2018/9/13 16:44
 */
public class WechatPubAccessTokenDto {
	private String errcode; // 没有成功获取到时，不为空，示例："errcode":40029,"errmsg":"invalid code"
	private String errmsg;
	private String access_token; // 接口调用凭证
	private String expires_in; // access_token接口调用凭证超时时间，单位（秒）

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

	@Override
	public String toString() {
		return "WechatPubAccessTokenDto{" + "errcode='" + errcode + '\'' + ", errmsg='" + errmsg + '\''
				+ ", access_token='" + access_token + '\'' + ", expires_in='" + expires_in + '\'' + '}';
	}
}
