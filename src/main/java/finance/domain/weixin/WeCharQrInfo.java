package finance.domain.weixin;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>微信二维码信息</p>
 *
 * @author lili
 * @version 1.0: WeCharQrInfo.java, v0.1 2018/11/20 5:21 PM PM lili Exp $
 */
@Data
public class WeCharQrInfo implements Serializable {

    private static final long serialVersionUID = -1589100526377245582L;
    /**
     * 生成的二维码url
     */
    private String            url;
    /**
     * 获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码。
     */
    private String            ticket;
    /**
     * 阿里云图片地址
     */
    private String            qrUrl;

}
