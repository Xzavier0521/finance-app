package cn.zhishush.finance.core.dal.dataobject.activity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: AgentConfigDO.java, v0.1 2018/10/11 10:43 AM lili Exp $
 */
@Data
public class AgentConfigDO implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 代理的用户id
	 */
	private Long agentId;
	/**
	 * 代理级别
	 */
	private Integer agentLevel;
	/**
	 * 推广活动代码
	 */
	private String activityCode;
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