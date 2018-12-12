package cn.zhishush.finance.api.model.response;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

/**
 * <p>微信公众号二维码</p>
 * @author lili
 * @version 1.0: WeChatCreateQrResponse.java, v0.1 2018/11/26 7:05 PM lili Exp $
 */
@Data
@Builder
public class WeChatCreateQrResponse implements Serializable {
	private static final long serialVersionUID = -1258498565348908199L;
	/**
	 * 生成的二维码url
	 */
	private String url;
	/**
	 * 获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码。
	 */
	private String ticket;

	/**
	 * 有效时间，最大30天
	 */
	private Long expireSeconds;
	/**
	 * 阿里云图片地址
	 */
	private String qrUrl;
}
