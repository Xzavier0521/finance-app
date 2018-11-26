package finance.domain.activity;

import java.io.Serializable;

import lombok.Data;
import finance.core.common.enums.RedEnvelopeRainTimeCodeEnum;

/**
 * <p>
 * 注释
 * </p>
 *
 * @author lili
 * @version 1.0: UserRedEnvelopeRainInfo.java, v0.1 2018/11/16 11:48 PM PM lili
 *          Exp $
 */
@Data
public class UserRedEnvelopeRainInfo implements Serializable {
	private static final long serialVersionUID = 646768440887994793L;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 是否已经参加红包雨活动
	 */
	private Boolean isJoin;
	/**
	 * 活动代码
	 */
	private String activityCode;

	/**
	 * 活动日期
	 */
	private Integer activityDay;
	/**
	 * 时间编码
	 */
	private RedEnvelopeRainTimeCodeEnum timeCode;
}
