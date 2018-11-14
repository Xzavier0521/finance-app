package finance.core.common.exception;

import finance.core.common.enums.ReturnCode;

/**
 * <p>系统异常</p>
 * @author lili
 * @version $Id: ServiceException.java, v0.1 2018/11/13 1:41 PM lili Exp $
 */
public class ServiceException extends BaseException {

    private static final long serialVersionUID = 2429828589069742088L;

    public ServiceException(String errorMessage) {
        super(errorMessage);
    }

    public ServiceException(String errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public ServiceException(ReturnCode returnCode) {
        super(returnCode.getCode(), returnCode.getDesc());
    }

    public ServiceException(String errorCode, String errorMsg, Exception e) {
        super(errorCode, errorMsg, e);
    }
}
