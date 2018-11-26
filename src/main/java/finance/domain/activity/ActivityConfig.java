package finance.domain.activity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: ActivityConfig.java, v0.1 2018/10/11 10:13 AM lili Exp $
 */
@Data
public class ActivityConfig implements Serializable {

	private static final long serialVersionUID = 2225960344629723983L;
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 活动代码
	 */
	private String activityCode;
	/**
	 * 活动名称
	 */
	private String activityName;
	/**
	 * 推广url
	 */
	private String spreadUrl;
	/**
	 * 代理的用户id
	 */
	private Long agentId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 是否已删除(0:否；1:是)
	 */
	private Integer isDelete;
	/**
	 * 创建人
	 */
	private String creater;
	/**
	 * 更新人
	 */
	private String updater;
	/**
	 * 版本号
	 */
	private Integer version;
}
