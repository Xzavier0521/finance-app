package finance.ext.api.request;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: WeiXinSetIndustryRequest.java, v0.1 2018/10/21 10:09 PM lili Exp $
 */
@Data
public class WeiXinSetIndustryRequest implements Serializable {
    private static final long serialVersionUID = 2815881210039273343L;

    /**
     * 公众号模板消息所属行业编号
     */
    private String            industry_id1;
    /**
     * 公众号模板消息所属行业编号
     */
    private String            industry_id2;
}
