package finance.api.model.response;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: UserActivityWeChatPubInfoQueryResponse.java, v0.1 2018/11/26 7:04 PM lili Exp $
 */
@Data
public class UserActivityWeChatPubInfoQueryResponse implements Serializable {

    private static final long serialVersionUID = 859491862027822160L;
    /**
     * 用户id
     */
    private Long              userId;

    /**
     * 手机号码
     */
    private String            mobilePhone;

    /**
     * 活动代码
     */
    private String            activityCode;

    /**
     * 历史关注人数
     */
    private Long              historySubscribeNum;

    /**
     * 当前关注人数
     */
    private Long              subscribeNum;

    /**
     * 微信公众号取消关注人数
     */
    private Long              unsubscribeNum;
}
