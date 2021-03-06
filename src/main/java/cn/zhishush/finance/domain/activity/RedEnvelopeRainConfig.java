package cn.zhishush.finance.domain.activity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>
 * 红包雨活动时间配置
 * </p>
 *
 * @author lili
 * @version 1.0: RedEnvelopeRainConfig.java, v0.1 2018/11/16 11:45 AM PM lili
 *          Exp $
 */
@Data
public class RedEnvelopeRainConfig implements Serializable {

	private static final long serialVersionUID = 5718554520092850963L;
	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 活动代码
	 */
	private String activityCode;

	/**
	 * 时间代码
	 */
	private String timeCode;

	/**
	 * 开始时间 hhmmss
	 */
	private String startTime;

	/**
	 * 结束时间
	 */
	private String endTime;

	/**
	 * 持续时间: 秒
	 */
	private Integer duration;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 是否删除 0-否 1-是
	 */
	private Integer isDelete;

	/**
	 * 创建者
	 */
	private String creator;

	/**
	 * 更新者
	 */
	private String updator;

	/**
	 * 版本号
	 */
	private Integer version;
}
