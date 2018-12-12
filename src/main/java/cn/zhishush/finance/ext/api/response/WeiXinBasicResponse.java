package cn.zhishush.finance.ext.api.response;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>微信响应消息</p>
 * 
 * @author lili
 * @version $Id: WeiXinBasicResponse.java, v0.1 2018/10/21 7:54 PM lili Exp $
 */
@Data
public class WeiXinBasicResponse implements Serializable {
    private static final long serialVersionUID = 2881235421284479273L;
    /**
     * 返回码
     */
    private String            errcode;
    /**
     * 错误消息
     */
    private String            errmsg;
}
