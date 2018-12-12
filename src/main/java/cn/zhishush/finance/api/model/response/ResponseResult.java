package cn.zhishush.finance.api.model.response;

import cn.zhishush.finance.core.common.enums.CodeEnum;

/**
 * API返回数据结构.
 *
 * @author hewenbin
 * @version v1.0 2018年7月3日 下午3:28:41 hewenbin
 */
public class ResponseResult<T> {

	public static final String SUCCESS_CODE = "0000000";
	private String errorCode;
	private String errorMessage;
	private T data;

	public ResponseResult(String code, String msg) {
		this.errorCode = code;
		this.errorMessage = msg;
	}

	public ResponseResult(String code, String msg, T data) {
		this.errorCode = code;
		this.errorMessage = msg;
		this.data = data;
	}

	public static <T> ResponseResult<T> success(String message, T data) {
		return new ResponseResult<T>(SUCCESS_CODE, message, data);
	}

	public static <T> ResponseResult<T> success(T data) {
		return new ResponseResult<T>(SUCCESS_CODE, "success", data);
	}

	/**
	 * 建议使用error(CodeEnum codeEnum)
	 *
	 * @param errorCode
	 *            七位字符
	 * @param message
	 *            错误提示说明
	 * @return
	 * @author hewenbin
	 * @version ResponseResult.java, v1.0 2018年7月3日 下午3:29:33 hewenbin
	 */
	@Deprecated
	public static <T> ResponseResult<T> error(String errorCode, String message) {
		return new ResponseResult<T>(errorCode, message, null);
	}

	/**
	 * @return
	 * @author hewenbin
	 * @version ResponseResult.java, v1.0 2018年7月3日 下午3:29:33 hewenbin
	 */
	public static <T> ResponseResult<T> error(CodeEnum codeEnum) {
		return new ResponseResult<T>(codeEnum.getCode(), codeEnum.getMsg(), null);
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public T getData() {
		return this.data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public boolean isSucceed() {
		return SUCCESS_CODE.equals(this.errorCode);
	}

	@Override
	public String toString() {
		return this.data != null
				? "ResponseResult [errorCode=" + this.errorCode + ", errorMessage=" + this.errorMessage + ", data="
						+ this.data.toString() + "]"
				: "ResponseResult [errorCode=" + this.errorCode + ", errorMessage=" + this.errorMessage + "]";
	}

}
