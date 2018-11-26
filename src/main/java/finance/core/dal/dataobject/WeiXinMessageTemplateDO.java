package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>
 * 微信公众号消息模版
 * </p>
 *
 * @author lili
 * @version $Id: WeixinMessageTemplateDO.java, v0.1 2018/10/24 10:22 AM lili Exp
 *          $
 */
@Data
public class WeiXinMessageTemplateDO implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 模版代码
	 */
	private String templateCode;
	/**
	 * 模版描述
	 */
	private String templateDesc;
	/**
	 * 模版正文
	 */
	private String templateContent;
	/**
	 * 微信公众号模版id
	 */
	private String wxTemplateId;
	/**
	 * 跳转链接
	 */
	private String redirectUrl;
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
	 * 是否删除
	 */
	private String isDelete;
	/**
	 * 版本号
	 */
	private Integer version;

}