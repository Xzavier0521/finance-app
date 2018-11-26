package finance.ext.api.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: WeiXinQueryTokenResponse.java, v0.1 2018/10/21 8:45 PM lili Exp
 *          $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeiXinQueryTokenResponse extends WeiXinBasicResponse {
	private static final long serialVersionUID = 3707852981512621585L;

	/**
	 * 接口凭证
	 */
	private String access_token;

	/**
	 * 有效时间
	 */
	private Long expires_in;
}
