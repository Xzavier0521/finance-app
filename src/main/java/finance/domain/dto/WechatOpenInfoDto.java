package finance.domain.dto;

import lombok.Data;

/**
 * 获取微信 openId 时返回的数据.
 * 
 * @author hewenbin
 * @version v1.0 2018年8月16日 下午3:04:04 hewenbin
 */
@Data
public class WechatOpenInfoDto {

	/**
	 * 没有成功获取到时，不为空，示例："errcode":40029,"errmsg":"invalid code"
	 */
	private String errcode;
	private String errmsg;
	/**
	 * 接口调用凭证
	 */
	private String access_token;
	/**
	 * //access_token接口调用凭证超时时间，单位（秒）
	 */
	private String expires_in;
	/**
	 * 用户刷新access_token
	 */
	private String refresh_token;
	/**
	 * 授权用户唯一标识
	 */
	private String openid;
	/**
	 * 用户授权的作用域，使用逗号（,）分隔
	 */
	private String scope;
	/**
	 * 当且仅当该移动应用已获得该用户的userinfo授权时，才会出现该字段
	 */
	private String unionid;
}
