package cn.zhishush.finance.core.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * api返回错误码.
 * 
 * @author hewenbin
 * @version v1.0 2018年7月3日 下午3:43:56 hewenbin
 */
public enum CodeEnum {
	/*
	 * 系统默认code，不允许修改，可以添加，需要和团队成员沟通好 在默认 code 不够用的情况下，可以添加具体业务code，注意：不建议添加业务code
	 */
	succ("0000000", null), paramEmpty("0000001", "参数为空"), paramLack("0000002", "参数不足"), dataExist("0000003",
			"数据已存在"), dataNotExist("0000004", "数据不存在"), dbException("0000005", "数据库异常"), fail("0000010",
					"失败了"), paramInvalid("0000011", "输入不合法"), paramOverLimit("0000012",
							"参数超过限制"), paramOverLenLimit("0000013", "参数长度不合法"), systemError("9999999", "网络返回错误"),

	loginParamValidateInvalid("0101001", "登陆信息输入不合法"), loginImgValidateFail("0101002",
			"图片验证码不正确"), loginSmsValidateFail("0101003", "短信验证码不正确或已超过3次"), getOpenIdFail("0101004",
					"获取第三方账号信息失败"), thirdLoginValidateInvalid("0101005", "输入不合法"), thirdUnbindValidateInvalid("0101006",
							"输入不合法"), thirdAccountExist("0101007", "该授权账号已被绑定"), thirdBinded("0101008", "已绑定，不允许重复绑定"),

	imgValidateInvalid("0102001", "验证码输入不合法"), imgValidateFail("0102002", "图片验证码不正确"),

	smsValidateInvalid("0103001", "验证码输入不合法"), smsImgValidateFail("0103002", "图片验证码不正确"), smsSendFail("0103003",
			"验证码发送失败，请重试"),

	bankCardParamInvalid("0103001", "请填写正确的银行卡号"),

	accountPwdParamInvalid("0104001", "密码输入不合法"), accountPwdSmsValidateFail("0104002", "短信验证码不正确"), accountNotExist(
			"0104003", "不支持的银行卡号"), idInfoNotSave("0104004", "个人信息未完善"), idCardParamInvalid("0104005",
					"您输入的姓名或身份证账号有误，请检查后重新输入"), idAuthExist("0104006", "身份证已验证"), bankCardAuthError("0104007",
							"银行卡验证异常，请联系客服"), bankCardAuthDiffer("0104008",
									"您输入的信息银行校验未通过，请检查您的银行卡号和您的银行预留手机号是否有误"), idAuthByOthersError("0104009",
											"身份证已被其他用户验证，请联系客服"), bankCardAuthed("0104010", "银行卡已验证，不可修改"),

	withdrawParamInvalid("0201000", "提现输入不合法"), sendMsgError("0201001", "短信发送失败"), bankCardNotExist("0201002",
			"用户不存在"), extendWithDrawMoney("0201003", "超出可提现金额"), pwdError("0201004",
					"提现密码不对"), lowWithDrawMoney("0201005", "提现金额必须大于10元"), idCardNotExist("0201006", "个人身份证信息未完善"),

	operateValidateInvalid("0401001", "输入不合法"),
	// 产品
	financialParamNUll("0301001", "理财查询参数错误"), creditCardParamNUll("0302001", "输入不合法"), loanParamNUll("0303001",
			"输入不合法"), rebackRuleParamNUll("0304001", "输入不合法"),

	financeHomeParamInvalid("0305001", "输入不合法"), registerHomeParamInvalid("0306001", "输入不合法"),

	gameParamInvalid("0401001", "输入不合法"), coinNumNotEnough("0401002", "金币数不足"), joinTimeInvalid("0401003",
			"非参加活动时间"), signTimeInvalid("0401004", "非打卡时间"), joinFail("0401005", "网络异常，请稍后重试"), todayAlreadySign(
					"0401005", "今日已签到"), giftNotExist("0401006", "礼品不存在"), invalidOperation("0401007", "非法操作"),

	unfinishFixedAmountActivity("060101", "还存未完成的固定金额活动"), invalidActivityOperation("060102",
			"非法操作"), activityFinish("060103", "活动已结束"), activityDownLine("060104", "活动已下线"),

	// 业务code
	newsParamInvalid("0501001", "资讯信息输入不合法"),

	stepRedEnvelopeEnd("0601001", "阶梯红包活动已结束"), stepRedEnvelopeExist("0601002", "已参加阶梯红包活动"),

	example("", "");
	private String code;
	private String msg;

	CodeEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	// 业务code
	private static final Map<String, CodeEnum> param = new HashMap<String, CodeEnum>();

	static {
		for (CodeEnum codeEnum : CodeEnum.values()) {
			param.put(codeEnum.getCode(), codeEnum);
		}
	}
	public static Map<String, CodeEnum> getParam() {
		return param;
	}
	@Override
	public String toString() {
		return "CodeEnum{" + "code='" + code + '\'' + ", msg='" + msg + '\'' + '}';
	}
}
