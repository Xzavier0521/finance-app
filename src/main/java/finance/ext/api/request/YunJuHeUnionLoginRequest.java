package finance.ext.api.request;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>云聚合联合登陆请求参数</p>
 *
 * @author lili
 * @version 1.0: YunJuHeUnionLoginRequest.java, v0.1 2018/11/28 6:56 PM PM lili Exp $
 */
@Data
public class YunJuHeUnionLoginRequest implements Serializable {
    private static final long serialVersionUID = 5910076237760054682L;

    /**
     *  渠道商编号
     */
    private String            deptId;

    /**
     * 渠道商用户标识
     */
    private String            userId;

    /**
     * 真实姓名
     */
    private String            realName;

    /**
     * 手机号码
     */
    private String            mobile;

    /**
     * 身份证号码
     */
    private String            certcode;

    /**
     * 签名
     */
    private String            sign;
}
