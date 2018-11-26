package finance.api.model.request;

import lombok.Data;

/**
 * <p>
 * 微信公众号 绑定open_id
 * </p>
 * 
 * @author lili
 * @version $Id: WeCharBindOpenInfoRequest.java, v0.1 2018/10/26 9:58 AM lili
 *          Exp $
 */
@Data
public class WeChatBindOpenInfoRequest {

	/**
	 * open_id
	 */
	private String openId;
}
