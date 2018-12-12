package cn.zhishush.finance.core.dal.dataobject.user;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: UserBankCardInfoDO.java, v0.1 2018/11/26 4:46 PM lili Exp $
 */
@Data
public class UserBankCardInfoDO implements Serializable {
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