package finance.core.common.exception;

/**
 * jwt自定义异常.
 * @author hewenbin
 * @version v1.0 2018年7月17日 上午11:05:37 hewenbin
 */
public class JwtException extends RuntimeException {
	private static final long serialVersionUID = -5822403559904464496L;

	public JwtException(String message) {
		super(message);
	}

}
