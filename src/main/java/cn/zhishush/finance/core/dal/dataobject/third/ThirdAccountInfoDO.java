package cn.zhishush.finance.core.dal.dataobject.third;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: ThirdAccountInfoDO.java, v0.1 2018/11/21 3:39 PM lili Exp $
 */
@Data
public class ThirdAccountInfoDO implements Serializable {
	private static final long serialVersionUID = -5138910644340404056L;
	/**
	 * 主键ID
	 */
	private Long id;

	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	 * 第三方渠道类型（qq、wechat、wechat_public……）
	 */
	private String channel;

	/**
	 * 公众号名
	 */
	private String publicName;

	/**
	 * 第三方账户（唯一标识）
	 */
	private String openId;

	/**
	 * 状态（1：已绑定；非1：已解绑）
	 */
	private String status;

	/**
	 * 是否已删除(0:否；1:是)
	 */
	private Integer isDelete;

	/**
	 * 创建人
	 */
	private String creator;

	/**
	 * 更新人
	 */
	private String updator;

	/**
	 * 版本号
	 */
	private Integer version;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

}