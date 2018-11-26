package finance.domain.activity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import finance.core.common.enums.RedEnvelopeRainTimeCodeEnum;
import finance.core.common.util.EntityUtils;

/**
 * <p>
 * 红包雨活动数据
 * </p>
 *
 * @author lili
 * @version 1.0: RedEnvelopeRainData.java, v0.1 2018/11/14 10:01 PM PM lili Exp
 *          $
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedEnvelopeRainData implements Serializable {

	private static final long serialVersionUID = 261344159214689225L;
	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 手机号码
	 */
	private String mobilePhone;
	/**
	 * 活动代码
	 */
	private String activityCode;
	/**
	 * 时间编码
	 */
	private RedEnvelopeRainTimeCodeEnum timeCode;

	/**
	 * 红包总个数
	 */
	private Long totalNum;

	/**
	 * 总金额/金币
	 */
	private BigDecimal totalAmount;

	/**
	 * 活动日期
	 */
	private Integer activityDay;

	/**
	 * 排名
	 */
	private Long ranking;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 创建者
	 */
	private String creator;

	/**
	 * 更新者
	 */
	private String updator;

	/**
	 * 是否删除 0-否，1-是
	 */
	private Integer isDelete;

	/**
	 * 版本号
	 */
	private Long version;

	public static Set<String> fieldSet() {
		return Sets.newHashSet("ranking", "activityCode", "userId", "mobilePhone", "totalNum", "totalAmount",
				"activityDay");
	}

	public static RedEnvelopeRainData mapToObject(Map<String, Object> fieldMap) {
		return EntityUtils.hashToObject(fieldMap, RedEnvelopeRainData.class);
	}
}
