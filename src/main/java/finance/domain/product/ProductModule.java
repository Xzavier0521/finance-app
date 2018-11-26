package finance.domain.product;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import finance.core.common.enums.ProductModuleTypeEnum;

/**
 * <p>
 * 产品模块
 * </p>
 *
 * @author lili
 * @version 1.0: ProductModule.java, v0.1 2018/11/8 1:54 PM PM lili Exp $
 */
@Data
public class ProductModule implements Serializable {

	private static final long serialVersionUID = -46992396241392947L;
	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 产品模块代码
	 */
	private ProductModuleTypeEnum moduleCode;

	/**
	 * 产品模块名称
	 */
	private String moduleName;

	/**
	 * 产品模块banner url
	 */
	private String moduleBannerUrl;

	/**
	 * 模块类型
	 */
	private String moduleType;
	/**
	 * 布局类型
	 */
	private String layoutType;

	/**
	 * 模块的顺序
	 */
	private Integer moduleOrder;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 创建时间
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
