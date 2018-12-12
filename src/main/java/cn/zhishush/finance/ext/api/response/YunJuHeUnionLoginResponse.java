package cn.zhishush.finance.ext.api.response;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>云聚合联合登陆返回结果</p>
 *
 * @author lili
 * @version 1.0: YunJuHeUnionLoginResponse.java, v0.1 2018/11/28 6:58 PM PM lili Exp $
 */
@Data
public class YunJuHeUnionLoginResponse implements Serializable {
    private static final long serialVersionUID = -26007171344836120L;

    /**
     *  响应业务状态
     */
    private int               code;

    /**
     * 响应消息
     */
    private String            msg;
    /**
     * 平台用户标识
     */
    private String            data;
}
