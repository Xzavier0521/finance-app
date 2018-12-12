package cn.zhishush.finance.ext.api.response;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 注释
 * </p >
 *
 * @author liwei
 * @version $Id: KaMengBasicResponse.java, v0.1 2018/11/19 下午6:05 PM user Exp $
 */
@Data
public class KaMengBasicResponse implements Serializable {
	/**
	 * 返回信息
	 */
	private String message;
	/**
	 * 返回码
	 */
	private String status;
}
