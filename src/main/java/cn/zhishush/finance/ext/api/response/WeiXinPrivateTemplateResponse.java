package cn.zhishush.finance.ext.api.response;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import cn.zhishush.finance.ext.api.model.WeiXinTemplate;

/**
 * <p>注释</p>
 * 
 * @author lili
 * @version $Id: WeiXinPrivateTemplateResponse.java, v0.1 2018/10/21 10:26 PM lili Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeiXinPrivateTemplateResponse extends WeiXinBasicResponse {
    private static final long    serialVersionUID = 8856481910713411560L;
    private List<WeiXinTemplate> template_list;
}
