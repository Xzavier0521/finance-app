package cn.zhishush.finance.ext.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>注释</p>
 * 
 * @author lili
 * @version $Id: WeiXinLongUrlToShortResponse.java, v0.1 2018/10/29 10:38 PM lili Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeiXinLongUrlToShortResponse extends WeiXinBasicResponse {
    private static final long serialVersionUID = 8954319527559683305L;

    /**
     * 短链接
     */
    private String            short_url;
}
