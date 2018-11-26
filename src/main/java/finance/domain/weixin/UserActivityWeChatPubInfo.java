package finance.domain.weixin;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: UserActivityWeChatPubInfo.java, v0.1 2018/11/7 4:34 PM lili Exp
 *          $
 */
@Data
public class UserActivityWeChatPubInfo implements Serializable {

	private static final long serialVersionUID = 4065512480140639878L;

	/**
	 * 活动代码
	 */
	private String activityCode;
	/**
	 * 历史关注人数
	 */
	private Long historySubscribeNum;

	/**
	 * 当前关注人数
	 */
	private Long subscribeNum;

	/**
	 * 微信公众号取消关注人数
	 */
	private Long unsubscribeNum;
}
