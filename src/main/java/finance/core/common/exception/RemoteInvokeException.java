package finance.core.common.exception;

import finance.core.common.enums.ReturnCode;

/**
 * 
 * <p>调用后台网络异常: 如后台系统系统无返回等</p>
 * @author  lin
 * @version $Id: RemoteInvokeException.java, v 0.1 2018年4月13日 上午10:01:09 lin Exp $
 */
public class RemoteInvokeException extends BaseException {

    private static final long serialVersionUID = -8215424335942344579L;

    public RemoteInvokeException(String errorMessage) {
        super(errorMessage);
    }

    public RemoteInvokeException(String errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public RemoteInvokeException(ReturnCode returnCode) {
        super(returnCode.getCode(), returnCode.getDesc());
    }

    public RemoteInvokeException(ReturnCode returnCode, String msg) {
        super(returnCode.getCode(), msg);
    }

    public RemoteInvokeException(ReturnCode returnCode, Exception e) {
        super(returnCode.getCode(), returnCode.getDesc(), e);
    }

    public RemoteInvokeException(String errorCode, String errorMsg, Exception e) {
        super(errorCode, errorMsg, e);
    }

}