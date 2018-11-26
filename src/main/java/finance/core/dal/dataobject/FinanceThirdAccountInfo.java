package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: FinanceThirdAccountInfo.java, v0.1 2018/11/14 7:12 PM lili Exp
 *          $
 */
@Data
public class FinanceThirdAccountInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long userId;

	private String channel;

	private String publicName;

	private String openId;

	private String status;

	private Integer isDelete;

	private String creator;

	private String updator;

	private Integer version;

	private Date createTime;

	private Date updateTime;

}