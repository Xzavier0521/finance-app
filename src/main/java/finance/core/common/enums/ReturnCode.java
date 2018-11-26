package finance.core.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * 返回码
 * </p>
 * 
 * @author lili
 * @version $Id: ReturnCode.java, v0.1 2018/11/13 1:40 PM lili Exp $
 */
public enum ReturnCode {

	/*
	 * 系统默认code，不允许修改，可以添加，需要和团队成员沟通好
	 */
	SUCCESS("0000000", "成功"), PARAM_EMPTY("0000001", "参数为空"), PARAM_LACK("0000002", "参数不足"), DATA_EXIST("0000003",
			"数据已存在"), DATA_NOT_EXIST("0000004", "数据不存在"), DB_EXCEPTION("0000005", "数据库异常"), REQUEST_REPEAT("0000006",
					"重复的请求"), ILLEGAL_ARGUMENT("0000007", "参数不合法"), FAIL("0000010", "失败了"), PARAM_INVALID("0000011",
							"输入不合法"), PARAM_OVER_LIMIT("0000012", "参数超过限制"), PARAM_OVER_LEN_LIMIT("0000013",
									"参数长度不合法"), SYSTEM_ERROR("9999999", "网络返回错误"),
	// 用户登陆，注册错误码 1XXX
	USER_REGISTER_ERROR("1001", "用户注册失败"), LOGIN_PARAM_VALIDATE_INVALID("0101001",
			"登陆信息输入不合法"), LOGIN_IMG_VALIDATE_FAIL("0101002", "图片验证码不正确"), LOGIN_SMS_VALIDATE_FAIL("0101003",
					"短信验证码不正确或已超过3次"), GET_OPENID_FAIL("0101004", "获取第三方账号信息失败"), THIRD_LOGIN_VALIDATE_INVALID(
							"0101005",
							"输入不合法"), THIRD_UNBIND_VALIDATE_INVALID("0101006", "输入不合法"), THIRD_ACCOUNT_EXIST("0101007",
									"该授权账号已被绑定"), THIRD_HAS_BINDING("0101008", "已绑定，不允许重复绑定"), IMG_VALIDATE_INVALID(
											"0102001",
											"验证码输入不合法"), IMG_VALIDATE_FAIL("0102002", "图片验证码不正确"), SMS_VALIDATE_INVALID(
													"0103001", "验证码输入不合法"), SMS_IMG_VALIDATE_FAIL("0103002",
															"图片验证码不正确"), SMS_SEND_FAIL("0103003",
																	"验证码发送失败，请重试"), USER_NOT_EXISTS("0103004", "用户不存在"),

	BANK_CARD_PARAM_INVALID("0103001", "请填写正确的银行卡号"), ACCOUNT_PWD_PARAM_INVALID("0104001",
			"密码输入不合法"), ACCOUNT_PWD_SMS_VALIDATE_FAIL("0104002", "短信验证码不正确"), ACCOUNT_NOT_EXIST("0104003",
					"不支持的银行卡号"), ID_INFO_NOT_SAVE("0104004", "个人信息未完善"), ID_CARD_PARAM_INVALID("0104005",
							"您输入的姓名或身份证账号有误，请检查后重新输入"), ID_AUTH_EXIST("0104006", "身份证已验证"), BANK_CARD_AUTH_ERROR(
									"0104007", "银行卡验证异常，请联系客服"), BANK_CARD_AUTH_DIFFER("0104008",
											"您输入的信息银行校验未通过，请检查您的银行卡号和您的银行预留手机号是否有误"), ID_AUTH_BY_OTHERS_ERROR("0104009",
													"身份证已被其他用户验证，请联系客服"), BANK_CARD_HAS_VERIFY("0104010",
															"银行卡已验证，不可修改"),

	WITH_DRAW_PARAM_INVALID("0201000", "提现输入不合法"), SEND_MSG_ERROR("0201001", "短信发送失败"), BANK_CARD_NOT_EXIST("0201002",
			"用户不存在"), EXTEND_WITH_DRAW_MONEY("0201003", "超出可提现金额"), PWD_ERROR("0201004", "提现密码不对"), LOW_WITH_DRAW_MONEY(
					"0201005", "提现金额必须大于10元"), ID_CARD_NOT_EXIST("0201006", "个人身份证信息未完善"),
	// 产品
	FINANCIAL_PARAM_NULL("0301001", "理财查询参数错误"), CREDIT_CARD_PARAM_NULL("0302001", "输入不合法"), LOAN_PARAM_NULL("0303001",
			"输入不合法"), FINANCE_HOME_PARAM_INVALID("0305001", "输入不合法"), REGISTER_HOME_PARAM_INVALID("0306001",
					"输入不合法"), CREDIT_CARD_ID_NOT_EXIST("0302002", "信用卡产品不存在或者未配置"),
	// 金币
	GAME_PARAM_INVALID("0401001", "输入不合法"), COIN_NUM_NOT_ENOUGH("0401002", "金币数不足"), JOIN_TIME_INVALID("0401003",
			"非参加活动时间"), SIGN_TIME_INVALID("0401004", "非打卡时间"), JOIN_FAIL("0401005",
					"网络异常，请稍后重试"), TODAY_ALREADY_SIGN("0401005", "今日已签到"), GIFT_NOT_EXIST("0401006",
							"礼品不存在"), INVALID_OPERATION("0401007", "非法操作"), COIN_NUM_TOO_LARGE("0401008", "金币数不合法"),
	// 活动
	UNFINISHED_FIXED_AMOUNT_ACTIVITY("060101", "还存未完成的固定金额活动"), INVALID_ACTIVITY_OPERATION("060102",
			"非法操作"), ACTIVITY_FINISH("060103",
					"活动已结束"), ACTIVITY_OFFLINE("060104", "活动已下线"), ACTIVITY_HAS_JOIN("060105", "用户已经参加活动"),

    UNFINISHED_SECOND_REWARD_ACTIVITY("060106","未完成二级奖励活动"),
	// 业务CODE
	NEWS_PARAM_INVALID("0501001", "资讯信息输入不合法"), STEP_RED_ENVELOPE_END("0601001", "阶梯红包活动已结束"), STEP_RED_ENVELOPE_EXIST(
			"0601002", "已参加阶梯红包活动"), RAIN_RED_ENVELOPE_UN_START("0601003", "红包雨活动未开始"),
	// 第三方渠道
	KAMENG_CREDIT_CARD_ISNULL("0901001", "卡盟渠道返回信息为空"), SYS_BUSY("888888", "系统繁忙，请稍后重试"), SYS_ERROR("999999", "系统异常");

	private String code;
	private String desc;

	ReturnCode(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static ReturnCode getByCode(String code) {
		if (StringUtils.isBlank(code)) {
			return null;
		}
		for (ReturnCode temp : ReturnCode.values()) {
			if (temp.getCode().equals(code)) {
				return temp;
			}
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
