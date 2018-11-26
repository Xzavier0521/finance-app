package finance.ext.api.response;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import finance.ext.api.model.WeiXinTemplate;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: WeiXinPrivateTemplateResponse.java, v0.1 2018/10/21 10:26 PM
 *          lili Exp $
 */
@Data
public class WeiXinPrivateTemplateResponse implements Serializable {
	private List<WeiXinTemplate> template_list;
}
