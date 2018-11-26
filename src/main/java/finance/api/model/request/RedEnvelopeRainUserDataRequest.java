package finance.api.model.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import finance.core.common.enums.RedEnvelopeRainTimeCodeEnum;

/**
 * <p>红包雨用户数据</p>
 * @author lili
 * @version 1.0: RedEnvelopeRainUserDataRequest.java, v0.1 2018/11/26 7:02 PM lili Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RedEnvelopeRainUserDataRequest extends BasicRequest {

	private static final long serialVersionUID = -7740988454249948488L;

	/**
	 * 活动代码
	 */
	private String activityCode;
	/**
	 * 红包总个数
	 */
	@NotNull(message = "红包总个数不能为空")
	private Long totalNum;
	/**
	 * 红包总金额
	 */
	@NotNull(message = "红包总金额不能为空")
	private BigDecimal totalAmount;

	/**
	 * 时间编码 当日第几次红包雨数据
	 */
	private RedEnvelopeRainTimeCodeEnum timeCode;

}
