package finance.ext.api.response;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import finance.ext.api.model.WeiXinUserInfoDetail;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: WeiXinBatchQueryUserInfoResponse.java, v0.1 2018/10/30 12:18 AM
 *          lili Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeiXinBatchQueryUserInfoResponse extends WeiXinBasicResponse {

	private static final long serialVersionUID = 5822802960232469141L;
	private List<WeiXinUserInfoDetail> user_info_list;
}
