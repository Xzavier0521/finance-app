package cn.zhishush.finance.api.model.vo.teaminfo;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: UserInfoVo.java, v0.1 2018/10/6 12:07 AM lili Exp $
 */
@Data
public class UserInfoVo implements Serializable {

	/**
	 * 手机号码
	 */
	private String mobileNum;
	/**
	 * 用户姓名
	 */
	private String name;
	/**
	 * 注册时间
	 */
	private String registerDate;
}
