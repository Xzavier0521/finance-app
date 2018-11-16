package finance.core.dal.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceWithdrawOrder.java, v0.1 2018/11/14 7:18 PM lili Exp $
 */
@Data
public class FinanceWithdrawOrder implements Serializable {
    private static final long serialVersionUID = 8491501102188661526L;
    private Long              id;

    private String            serialId;

    private Long              userId;

    private String            userName;

    private BigDecimal        money;

    private Long              bankCardId;

    private String            bankFullname;

    private String            accountNo;

    private String            phone;

    private String            bankType;

    private String            status;

    private Date              createTime;

    private Date              updateTime;

    private Integer           version;

    private String            isFlag;

    private String            errorCode;

    private String            errorMessage;

}