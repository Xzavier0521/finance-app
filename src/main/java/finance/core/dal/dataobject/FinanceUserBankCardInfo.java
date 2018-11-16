package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceUserBankCardInfo.java, v0.1 2018/11/14 7:13 PM lili Exp $
 */
@Data
public class FinanceUserBankCardInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long              id;

    private Long              userId;

    private String            bankName;

    private String            accountName;

    private String            accountNo;

    private String            accountMobile;

    private Integer           isDefault;

    private Integer           isDelete;

    private String            creator;

    private String            updator;

    private Integer           version;

    private Date              createTime;

    private Date              updateTime;

}