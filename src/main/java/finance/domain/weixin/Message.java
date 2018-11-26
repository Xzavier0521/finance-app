package finance.domain.weixin;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 微信公众号回复消息
 * </p>
 * 
 * @author lili
 * @version $Id: Message.java, v0.1 2018/10/28 1:50 PM lili Exp $
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message implements Serializable {

	private static final long serialVersionUID = 6337246454612744119L;
	private String ToUserName;

	private String FromUserName;

	private Long CreateTime;

	private String MsgType;

	private String MsgId;
}