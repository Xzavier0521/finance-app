package finance.api.model.response;

import lombok.Builder;
import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version 1.0: KeyGeneratorResponse.java, v0.1 2018/11/26 7:03 PM lili Exp $
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
