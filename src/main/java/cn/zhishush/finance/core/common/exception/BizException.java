package cn.zhishush.finance.core.common.exception;

import cn.zhishush.finance.core.common.enums.ReturnCode;

/**
 * <p>
 * 业务异常类，用来描述业务异常
 * </p>
 * 
 * @author lili
 * @version $Id: BizException.java, v0.1 2018/11/13 1:41 PM lili Exp $
 */
public class BizException extends BaseException {

	private static final long serialVersionUID = 8001972550549637348L;

	public BizException(String errorMessage) {
		super(errorMessage);
	}

	public BizException(String errorCode, String errorMsg) {
		super(errorCode, errorMsg);
	}

	public BizException(ReturnCode returnCode) {
		super(returnCode.getCode(), returnCode.getDesc());
	}

	public BizException(ReturnCode returnCode, String msg) {
		super(returnCode.getCode(), msg);
	}

	public BizException(String errorCode, String errorMsg, Exception e) {
		super(errorCode, errorMsg, e);
	}
}
