package cn.zhishush.finance.core.dal.dataobject.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liwei
 * @date 2018/11/20
 * @description 用户申请信用卡数据
 */
@Data
public class UserApplyCreditCardDetailDO implements Serializable {
	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 用户姓名
	 */
	private String userName;

	/**
	 * 手机号
	 */
	private String mobileNum;

	/**
	 * 身份证号
	 */
	private String idNum;

	/**
	 * 产品id
	 */
	private Long productId;

	/**
	 * 产品类型(1:办卡;2:贷款)
	 */
	private Long productType;

	/**
	 * ip地址
	 */
	private String ip;

	/**
	 * 系统名称
	 */
	private String systemVersion;

	/**
	 * 浏览器版本
	 */
	private String softVersion;

	/**
	 * 渠道商编号
	 */
	private String channelId;

	/**
	 * 办理状态(0失败 1成功)
	 */
	private String status;

	/**
	 * 错误信息
	 */
	private String message;

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
	private String isDelete;

	/**
	 * 版本号
	 */
	private Integer version;

}