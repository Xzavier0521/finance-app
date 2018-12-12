package cn.zhishush.finance.api.model.request;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>基础请求参数</p>
 * @author lili
 * @version 1.0: BasicRequest.java, v0.1 2018/11/26 7:01 PM lili Exp $
 */
@Data
public class BasicRequest implements Serializable {

    private static final long serialVersionUID = -8402730426096360936L;
    /**
     * 时间戳
     */
    private Long              timestamp;
    private String            noncestr;
    private String            signature;
    private String            appId;
}
