package finance.domain.weixin;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>注释</p>
 * 
 * @author lili
 * @version $Id: NoticeMessage.java, v0.1 2018/10/22 4:36 PM lili Exp $
 */
@Data
public class NoticeMessage implements Serializable {
    private static final long serialVersionUID = -8371680524310887593L;
    /**
     * 公众号微信号
     */
    private String            ToUserName;
    /**
     * 接收模板消息的用户的openid
     */
    private String            FromUserName;
    /**
     * 创建时间
     */
    private String            CreateTime;
    /**
     * 消息类型是事件
     */
    private String            MsgType;
    /**
     * 消息类型是事件
     */
    private String            Event;
    /**
     * 消息id
     */
    private String            MsgID;
    /**
     * 状态
     */
    private String            Status;
}
