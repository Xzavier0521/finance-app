package finance.ext.api.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: WeiXinTempQrCreateResponse.java, v0.1 2018/10/29 3:43 PM lili Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeiXinTempQrCreateResponse extends WeiXinBasicResponse {
    private static final long serialVersionUID = 6874477362870853759L;

    /**
     * 获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码。
     */
    private String            ticket;

    /**
     * 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天）
     */
    private Long              expire_seconds;
    /**
     * 二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片
     */
    private String            url;
}
