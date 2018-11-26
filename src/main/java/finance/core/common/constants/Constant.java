package finance.core.common.constants;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;

/**
 * @author yaolei
 * @Title: Constant
 * @ProjectName finance-app
 * @Description: 常亮类
 * @date 2018/7/10上午9:55
 */
public class Constant {
	// 一般活动
	public static final String COMMON_ACTIVITY = "commonActivity";
	// 成长任务
	public static final String GROWTASK = "growTask";
	// 新手任务
	public static final String NEWREGISTERTASK = "newRegisterTask";
	// 早起打卡
	public static final String EARLY_SIGN = "earlySign";
	// 每日签到
	public static final String EVERYDAY_CLOCK = "everydayClock";
	// 参加早起打卡
	public static final String JOIN = "join";
	// 金币退还
	public static final String GOLD_REFUND = "goldRefund";
	// 分发邀请人奖励
	public static final String GOLD_REWORD = "goldReword";
	// wechat
	public static final String THIRD_PARD_WECHAT = "wechat";
	// 微信公众号 wechatPub
	public static final String THIRD_PARD_WECHATPUB = "wechatPub";
	// QQ
	public static final String THIRD_PARD_QQ = "qq";
	/**
	 * 申请提现title
	 **/
	public static final String transfer_title = "用户申请提现";

	/**
	 * DE代表借记卡，CR代表信用卡，其他值为非法
	 **/
	public static final String card_type_de = "DE";

	/**
	 * DE代表借记卡，CR代表信用卡，其他值为非法
	 **/
	public static final String card_type_cr = "CR";

	/**
	 * 支持转账的银行列表
	 */
	public static final Map<String, String> bank_map;

	/**
	 * TODO 平台列表，定义枚举
	 */
	public static final Map<String, String> platform_code;

	/**
	 * 短信抬头
	 */
	public static final Map<String, String> sms_header;
	/**
	 * 团队查询类型
	 */
	public static final int type_all = 0;
	public static final int type_first = 1;
	public static final int type_second = 2;
	public static final String authSucc = "10000";
	/**
	 * 阶梯奖励列表
	 */
	public static final LinkedMap step_reward_map;
	/**
	 * 图片验证码长度
	 */
	public static int imgcode_length = 4;
	/**
	 * 短信验证码长度
	 */
	public static int smscode_length = 4;

	static {
		bank_map = new HashMap<>();
		bank_map.put("EVERCNBJ", "光大银行");
		bank_map.put("FJIBCNBA", "兴业银行");
		bank_map.put("ABOCCNBJ", "农业银行");
		bank_map.put("GDBKCN22", "广发银行");
		bank_map.put("SPDBCNSH", "浦发银行");
		bank_map.put("PCBCCNBJ", "建设银行");
		bank_map.put("HZCBCN2H", "杭州银行");
		bank_map.put("CQCBCN22", "重庆银行");
		bank_map.put("BKNBCN2N", "宁波银行");
		bank_map.put("SZDBCNBS", "平安银行");
		bank_map.put("ICBKCNBJ", "工商银行");
		bank_map.put("COMMCNSH", "交通银行");
		bank_map.put("CMBCCNBS", "招商银行");
		bank_map.put("BKCHCNBJ", "中国银行");
		bank_map.put("LWCBCNBJ", "莱商银行");
		bank_map.put("BOSHCNSH", "上海银行");
		bank_map.put("BOJSCNBN", "江苏银行");
		bank_map.put("HXBKCNBJ", "华頁银行");
		bank_map.put("CIBKCNBJ", "中信银行");
		bank_map.put("MSBCCNBJ", "民生银行");
		bank_map.put("PSBCCNBJ", "邮政储蓄银行");
		bank_map.put("GZCBCN22", "广州银行");
		bank_map.put("BJCNCNBJ", "北京银行");
		bank_map.put("LYCBCNBL", "临商银行");

		platform_code = new HashMap<>();
		platform_code.put("register_home", "注册宝");
		platform_code.put("finance_home", "金榕家");
		platform_code.put("finance_home_list", "金榕家集合页");

		sms_header = new HashMap<>();
		sms_header.put("register_home", "【注册宝】");
		sms_header.put("finance_home", "【金榕家】");
		sms_header.put("finance_home_list", "【金榕家】");

		step_reward_map = new LinkedMap();
		step_reward_map.put(10, new BigDecimal(8));
		step_reward_map.put(20, new BigDecimal(23));
		step_reward_map.put(40, new BigDecimal(50));
		step_reward_map.put(60, new BigDecimal(72));
		step_reward_map.put(80, new BigDecimal(88));

		step_reward_map.put(100, new BigDecimal(120));
		step_reward_map.put(200, new BigDecimal(252));
		step_reward_map.put(300, new BigDecimal(357));
		step_reward_map.put(400, new BigDecimal(456));
		step_reward_map.put(500, new BigDecimal(555));

		step_reward_map.put(600, new BigDecimal(658));
		step_reward_map.put(700, new BigDecimal(759));
		step_reward_map.put(800, new BigDecimal(850));
		step_reward_map.put(900, new BigDecimal(950));

		step_reward_map.put(1000, new BigDecimal(1200));
		step_reward_map.put(2000, new BigDecimal(1600));
		step_reward_map.put(3000, new BigDecimal(2400));
		step_reward_map.put(4000, new BigDecimal(3200));
		step_reward_map.put(5000, new BigDecimal(4000));
	}

}
