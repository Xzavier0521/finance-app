package finance.ext.api.request;

import finance.ext.api.model.ActionInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: WeiXinTempQrCreateRequest.java, v0.1 2018/10/29 3:31 PM lili
 *          Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeiXinTempQrCreateRequest extends WeiXinBasicRequest {

	private static final long serialVersionUID = 4320857905668049858L;
	/**
	 * 二维码类型，QR_SCENE为临时的整型参数值，QR_STR_SCENE为临时的字符串参数值，QR_LIMIT_SCENE为永久的整型参数值，QR_LIMIT_STR_SCENE为永久的字符串参数值
	 */
	private String action_name;

	/**
	 * 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
	 */
	private Long expire_seconds;

	/**
	 * 二维码详细信息
	 */
	private ActionInfo action_info;

}
