package finance.ext.api.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import finance.ext.api.model.WeiXinUserInfo;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: UserInfoQueryResponse.java, v0.1 2018/10/22 11:35 AM lili Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserInfoQueryResponse extends WeiXinBasicResponse {

	private static final long serialVersionUID = 2909306383328448389L;
	/**
	 * 关注该公众账号的总用户数
	 */
	private int total;
	/**
	 * 拉取的OPENID个数，最大值为10000
	 */
	private int count;

	/**
	 * 拉取的OPENID个数，最大值为10000
	 */
	private WeiXinUserInfo data;
	/**
	 * 拉取列表的最后一个用户的OPENID
	 */
	private String next_openid;

}
