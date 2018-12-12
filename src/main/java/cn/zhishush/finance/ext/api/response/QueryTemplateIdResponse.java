package cn.zhishush.finance.ext.api.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>注释</p>
 * 
 * @author lili
 * @version $Id: QueryTemplateIdResponse.java, v0.1 2018/10/21 8:01 PM lili Exp
 *          $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryTemplateIdResponse extends WeiXinBasicResponse {

    private static final long serialVersionUID = -5998588343250384164L;
    /**
     * 模版id
     */
    private String            template_id;

}
