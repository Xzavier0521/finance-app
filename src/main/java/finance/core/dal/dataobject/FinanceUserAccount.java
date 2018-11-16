package finance.core.dal.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceUserAccount.java, v0.1 2018/11/14 7:13 PM lili Exp $
 */
@Data
public class FinanceUserAccount implements Serializable {
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