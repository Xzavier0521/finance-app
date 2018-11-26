package finance.api.model.request;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: BasicRequest.java, v0.1 2018/11/7 6:32 PM lili Exp $
 */
@Data
public class BasicRequest implements Serializable {

	private static final long serialVersionUID = -8402730426096360936L;
	/**
	 * 时间戳
	 */
	private Long timestamp;
	private String noncestr;
	private String signature;
	private String appId;
}
