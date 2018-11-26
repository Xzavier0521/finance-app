package finance.core.common.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import finance.api.model.response.BasicResponse;
import finance.core.common.enums.ReturnCode;
import finance.core.common.exception.BizException;
import finance.core.common.exception.ServiceException;

/**
 * 
 * <p>
 * 注释
 * </p>
 * 
 * @author lin
 * @version $Id: ResponseUtils.java, v 0.1 2018年4月11日 上午10:22:14 lin Exp $
 */
public class ResponseUtils {

	private static final Logger logger = LoggerFactory.getLogger(ResponseUtils.class);

	public static void buildResp(BasicResponse response, Exception e) {
		buildResp(response, e, false);
	}

	public static void buildResp(BasicResponse response, ReturnCode returnCode) {
		response.setSuccess(ReturnCode.SUCCESS == returnCode);
		response.setReturnCode(returnCode.getCode());
		response.setReturnMessage(returnCode.getDesc());
	}

	public static BasicResponse buildResp(ReturnCode returnCode, String message) {
		BasicResponse basicResponse = new BasicResponse();
		basicResponse.setSuccess(ReturnCode.SUCCESS == returnCode);
		basicResponse.setReturnCode(returnCode.getCode());
		basicResponse.setReturnMessage(StringUtils.isBlank(message) ? returnCode.getDesc() : message);
		return basicResponse;
	}

	public static BasicResponse buildResp(ReturnCode returnCode) {
		BasicResponse basicResponse = new BasicResponse();
		basicResponse.setSuccess(ReturnCode.SUCCESS == returnCode);
		basicResponse.setReturnCode(returnCode.getCode());
		basicResponse.setReturnMessage(returnCode.getDesc());
		return basicResponse;
	}

	public static void buildResp(BasicResponse response, Exception e, Boolean useErrorMsg) {
		if (e instanceof IllegalArgumentException) {
			// 参数异常
			response.setReturnCode(ReturnCode.ILLEGAL_ARGUMENT.getCode());
			response.setReturnMessage(e.getMessage());
		} else if (e instanceof BizException) {
			response.setReturnCode(((BizException) e).getErrorCode());
			response.setReturnMessage(((BizException) e).getErrorMsg());
		} else if (e instanceof ServiceException) {
			logger.error("ResponseUtils.buildResp处理异常,ServiceException: {} ", e.getMessage(), e);
			response.setReturnCode(((ServiceException) e).getErrorCode());
			response.setReturnMessage(((ServiceException) e).getErrorMsg());
		} else {
			logger.error("ResponseUtils.buildResp处理异常处,Exception: {}", e.getMessage(), e);
			response.setReturnCode(ReturnCode.SYS_ERROR.getCode());
			response.setReturnMessage(ReturnCode.SYS_ERROR.getDesc());
		}
	}

}
