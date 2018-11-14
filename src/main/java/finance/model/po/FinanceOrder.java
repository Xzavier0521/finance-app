package finance.model.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceOrder.java, v0.1 2018/11/14 10:44 AM lili Exp $
 */
@Data
public class FinanceOrder implements Serializable {
    private static final long serialVersionUID = -8066185680813384043L;
    private Long              id;

    private String            serialId;

    private Long              profitId;

    private String            title;

    private Long              userId;

    private String            userName;

    private String            transType;

    private BigDecimal        money;

    private Long              bankCardId;

    private String            bankFullname;

    private String            accountNo;

    private String            phone;

    private String            bankType;

    private String            auditName;

    private Long              auditId;

    private Integer           settlePatch;

    private String            status;

    private Date              createTime;

    private Date              updateTime;

    private Integer           version;

    private String            isFlag;

    private String            errorCode;

    private String            errorMessage;

    private Date              settleDate;

}