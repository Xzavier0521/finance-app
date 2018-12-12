package cn.zhishush.finance.core.common.exception;

/**
 * 签名错误自定义异常.
 * 
 * @author hewenbin
 * @version v1.0 2018年7月17日 上午10:09:30 hewenbin
 */
public class SignatureException extends RuntimeException {

	private static final long serialVersionUID = 8179282833171544608L;

	public SignatureException(String message) {
		super(message);
	}
}
