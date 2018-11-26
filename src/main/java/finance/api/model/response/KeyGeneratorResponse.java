package finance.api.model.response;

import lombok.Builder;
import lombok.Data;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: KeyGeneratorResponse.java, v0.1 2018/11/6 3:04 PM lili Exp $
 */
@Data
@Builder
public class KeyGeneratorResponse {

	/**
	 * 自定义代码
	 */
	private String code;
	/**
	 * 生成的key
	 */
	private Long key;
}
