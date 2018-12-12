package cn.zhishush.finance.domain.activity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import cn.zhishush.finance.domain.basic.BasicParameter;
import cn.zhishush.finance.domain.user.UserInfo;

/**
 * <p>
 * 注释
 * </p>
 *
 * @author lili
 * @version 1.0: ActivityCoinGameParameter.java, v0.1 2018/11/15 11:07 PM PM
 *          lili Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ActivityCoinGameParameter extends BasicParameter {
	private static final long serialVersionUID = -7976786016003138496L;
	/**
	 * 活动代码
	 */
	private String activityCode;
	/**
	 * 游戏代码
	 */
	private String gameCode;
	/**
	 * 游戏名称
	 */
	private String gameName;
	/**
	 * 金币数
	 */
	private Long coinNum;
	/**
	 * 用户信息
	 */
	private UserInfo userInfo;
}
