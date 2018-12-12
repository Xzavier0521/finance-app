package cn.zhishush.finance.api.model.vo.weixin;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: TemplateMessageNoticeVO.java, v0.1 2018/10/22 2:13 PM lili Exp
 *          $
 */
@XmlRootElement(name = "xml")
public class TemplateMessageNoticeRequest implements Serializable {

	private static final long serialVersionUID = 5716582266618276876L;
	/**
	 * 公众号微信号
	 */
	private String ToUserName;
	/**
	 * 接收模板消息的用户的openid
	 */
	private String FromUserName;
	/**
	 * 创建时间
	 */
	private String CreateTime;
	/**
	 * 消息类型是事件
	 */
	private String MsgType;
	/**
	 * 消息类型是事件
	 */
	private String Event;
	/**
	 * 消息id
	 */
	private String MsgID;
	/**
	 * 状态
	 */
	private String Status;

	@XmlElement
	public void setStatus(String status) {
		Status = status;
	}

	@XmlElement
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	@XmlElement
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	@XmlElement
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	@XmlElement
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	@XmlElement
	public void setEvent(String event) {
		Event = event;
	}

	@XmlElement
	public void setMsgID(String msgID) {
		MsgID = msgID;
	}

}
