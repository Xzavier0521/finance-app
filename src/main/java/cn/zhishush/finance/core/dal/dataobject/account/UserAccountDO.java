package cn.zhishush.finance.core.dal.dataobject.account;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version 1.0: UserAccountDO.java, v0.1 2018/11/26 4:45 PM lili Exp $
 */
@Data
public class UserAccountDO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long              id;

    private Long              userId;

    private BigDecimal        canWithdrawMoney;

    private BigDecimal        withdrawedMoney;

    private BigDecimal        incomeMoney;

    private String            userName;

    private String            status;

    private Date              createTime;

    private Date              updateTime;

    private Integer           version;

    private String            isFlag;

    private BigDecimal        sumChargeMoney;

}