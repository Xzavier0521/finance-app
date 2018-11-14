package finance.ext.api.request;

import lombok.Data;
/**
 * <p>注释</p>
 * @author lili
 * @version $Id: WeiXinQueryTokenRequest.java, v0.1 2018/10/21 8:41 PM lili Exp $
 */
@Data
public class WeiXinQueryTokenRequest {

    /**
     *
     */
    private String appid;

    /**
     *
     */
    private String appsecret;
}
