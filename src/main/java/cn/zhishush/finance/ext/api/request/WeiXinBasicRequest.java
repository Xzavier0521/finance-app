package cn.zhishush.finance.ext.api.request;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: WeiXinBasicRequest.java, v0.1 2018/10/21 7:50 PM lili Exp $
 */
@Data
public class WeiXinBasicRequest implements Serializable {

	private static final long serialVersionUID = 8198031999971719750L;
	/**
	 * 接口调用凭证
	 */
	private String accessToken;
}
