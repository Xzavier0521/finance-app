package cn.zhishush.finance.ext.api.response;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: WeiXinQueryIndustryResponse.java, v0.1 2018/10/21 10:14 PM lili
 *          Exp $
 */
@Data
public class WeiXinQueryIndustryResponse implements Serializable {

	private static final long serialVersionUID = 1093758896798633411L;
	/**
	 * 接口调用凭证
	 */
	private String access_token;
	/**
	 * 帐号设置的主营行业
	 */
	private String primary_industry;
	/**
	 * 帐号设置的副营行业
	 */
	private String secondary_industry;

}
