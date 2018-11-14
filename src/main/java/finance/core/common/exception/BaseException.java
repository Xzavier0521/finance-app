package finance.core.common.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
/**
 * <p>注释</p>
 * @author lili
 * @version $Id: BaseException.java, v0.1 2018/11/13 1:40 PM lili Exp $
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 2633519161550468988L;

    protected Throwable       rootCause        = null;

    /**
     * 异常的key
     */
    private String            errorCode;

    /**
     * 错误信息中的参数
     */
    protected String          errorMsg;

    public void setRootCause(Throwable rootCause) {
        this.rootCause = rootCause;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public BaseException() {
        super();
    }

    public BaseException(Throwable rootCause) {
        this.rootCause = rootCause;
    }

    public BaseException(String errorMessage) {
        super(errorMessage);
        this.errorMsg = errorMessage;
    }

    public BaseException(String errorMessage, Exception e) {
        super(errorMessage, e);
        this.errorMsg = errorMessage;
    }

    public BaseException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BaseException(String errorCode, String errorMsg, Exception e) {
        super(e);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;

    }

    @Override
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    @Override
    public void printStackTrace(PrintStream outStream) {
        printStackTrace(new PrintWriter(outStream));
    }

    @Override
    public void printStackTrace(PrintWriter writer) {
        System.err.print(this.getMessage());
        super.printStackTrace(writer);
        if (getRootCause() != null) {
            this.getRootCause().printStackTrace(writer);
        }
        writer.flush();
    }

    public Throwable getRootCause() {
        return rootCause;
    }
}
