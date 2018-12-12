package cn.zhishush.finance.ext.api.model;

/**
 * <p>
 * 微信小程序
 * </p>
 * 
 * @author lili
 * @version $Id: MiniProgram.java, v0.1 2018/10/21 7:39 PM lili Exp $
 */
public class MiniProgram {

	/**
	 * 所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系，暂不支持小游戏）
	 */
	private String appid;
	/**
	 * 所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar），暂不支持小游戏
	 */
	private String pagepath;
}
