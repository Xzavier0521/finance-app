package cn.zhishush.finance.domain.activity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import lombok.Data;

import com.google.common.collect.Sets;

import cn.zhishush.finance.core.common.util.EntityUtils;
import cn.zhishush.finance.core.common.enums.LeaderBoardTypeEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 排行榜
 * </p>
 * 
 * @author lili
 * @version $Id: LeaderBoard.java, v0.1 2018/10/19 8:33 PM lili Exp $
 */
@Slf4j
@Data
public class LeaderBoard implements Serializable {

	private static final long serialVersionUID = -1306186117253789418L;
	/**
	 * 排行
	 */
	private Long ranking;

	/**
	 * 排行榜类型
	 */
	private LeaderBoardTypeEnum leaderBoardType;

	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 手机号码
	 */
	private String mobilePhone;

	/**
	 * 邀请人数
	 */
	private Long inviteNumber;

	/**
	 * 用户真实姓名
	 */
	private String realName;

	/**
	 * 奖励金额
	 */
	private BigDecimal rewardAmount;

	public static Set<String> fieldSet() {
		return Sets.newHashSet("ranking", "leaderBoardType", "userId", "mobilePhone", "inviteNumber", "realName",
				"rewardAmount");
	}

	public static LeaderBoard mapToObject(Map<String, Object> fieldMap) {
		return EntityUtils.hashToObject(fieldMap, LeaderBoard.class);
	}

}
