package finance.ext.integration.weixin;

import finance.ext.api.model.WeiXinUserInfoDetail;
import finance.ext.api.request.WeiXinBatchQueryUserInfoRequest;
import finance.ext.api.response.UserInfoQueryResponse;
import finance.ext.api.response.WeiXinBatchQueryUserInfoResponse;

/**
 * <p>
 * 微信公众号用户信息
 * </p>
 * 
 * @author lili
 * @version $Id: WeiXinUserInfoQueryClient.java, v0.1 2018/10/23 3:06 PM lili
 *          Exp $
 */
public interface WeiXinUserInfoQueryClient {

	/**
	 * 获取用户列表
	 * 
	 * @param accessToken
	 *            调用接口凭证
	 * @param nextOpenid
	 *            第一个拉取的OPENID，不填默认从头开始拉取
	 * @return UserInfoQueryResponse
	 */
	UserInfoQueryResponse queryUserList(String accessToken, String nextOpenid);

	/**
	 * 获取用户基本信息(UnionID机制)
	 * 
	 * @param accessToken
	 *            调用接口凭证
	 * @param openId
	 *            普通用户的标识，对当前公众号唯一
	 * @return WeiXinUserInfoDetail
	 */
	WeiXinUserInfoDetail queryUserInfo(String accessToken, String openId);

	/**
	 * 批量获取用户基本信息
	 * 
	 * @param request
	 *            请求参数
	 * @return List<WeiXinUserInfoDetail>
	 */
	WeiXinBatchQueryUserInfoResponse batchQueryUserInfo(WeiXinBatchQueryUserInfoRequest request);

}
