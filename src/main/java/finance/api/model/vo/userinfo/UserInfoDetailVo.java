package finance.api.model.vo.userinfo;

import java.io.Serializable;

import lombok.Data;

/**
 * 用户详细信息，根据需求可扩展.
 * 
 * @author hewenbin
 * @version v1.0 2018年7月10日 下午3:39:54 hewenbin
 */

@Data
public class UserInfoDetailVo implements Serializable {

	private static final long serialVersionUID = 5116483402866383312L;
	/** 个人邀请码 */
	private String inviteCode;
	/** 个人手机号 */
	private String mobileNum;
	/** 是否已设置账户密码 */
	private Boolean hasAccountPwd;
	/** 真实姓名 */
	private String realName;
	/** 二代身份证号 */
	private String idNum;
	/** 认证状态 */
	private Integer authStatus;
	/** 银行名称 */
	private String bankName;
	/** 银行账号 */
	private String accountNo;
	/** 是否绑定QQ */
	private Boolean isBindQq;
	/** 是否绑定微信 */
	private Boolean isBindWechat;
	/**
	 * 是否关注微信公众号
	 */
	private Boolean isFlowWeChatPub;

	/**
	 * 微信公众号 open_id
	 */
	private String weChatOpenId;

}
