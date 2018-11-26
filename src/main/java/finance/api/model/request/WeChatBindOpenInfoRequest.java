package finance.api.model.request;

import lombok.Data;

/**
 * <p>微信公众号 绑定open_id</p>
 * @author lili
 * @version 1.0: WeChatBindOpenInfoRequest.java, v0.1 2018/11/26 7:03 PM lili Exp $
 */
@Data
public class WeChatBindOpenInfoRequest {

	/**
	 * open_id
	 */
	private String openId;
}
