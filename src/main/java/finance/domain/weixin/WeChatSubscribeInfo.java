package finance.domain.weixin;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: WeChatSubscribeInfo.java, v0.1 2018/11/7 3:18 PM lili Exp $
 */
@Data
public class WeChatSubscribeInfo implements Serializable {

    private static final long serialVersionUID = 5720616107047459494L;
    /**
     * 历史关注人数
     */
    private Long              historySubscribeNum;

    /**
     * 关注人数
     */
    private Long              subscribeNum;

    /**
     * 取消关注人数
     */
    private Long              unsubscribeNum;

    /**
     * 邀请码
     */
    private String            inviteCode;
}
