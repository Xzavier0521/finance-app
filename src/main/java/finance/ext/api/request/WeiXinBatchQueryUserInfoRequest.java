package finance.ext.api.request;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>批量获取用户基本信息</p>
 * @author lili
 * @version $Id: WeiXinBatchQueryUserInfoRequest.java, v0.1 2018/10/30 12:08 AM lili Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeiXinBatchQueryUserInfoRequest extends WeiXinBasicRequest {
    private static final long                serialVersionUID = -2769778524378718333L;

    private List<WeiXinUserInfoQueryRequest> user_list;
}
