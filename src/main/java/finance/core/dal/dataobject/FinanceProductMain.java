package finance.core.dal.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceProductMain.java, v0.1 2018/11/14 7:08 PM lili Exp $
 */
@Data
public class FinanceProductMain implements Serializable {
    private static final long serialVersionUID = -5841560448486042562L;
    private Long              id;

    private String            productName;

    private Integer           type;

    private String            redirectUrl;

    private Integer           amountType;

    private BigDecimal        totalBonus;

    private BigDecimal        terminalBonus;

    private BigDecimal        directBonus;

    private BigDecimal        indirectBonus;

    private String            logoUrl;

    private Integer           seqNo;

    private String            cashbackDate;

    private Integer           isShow;

    private Date              createTime;

    private Date              updateTime;

    private Integer           isDelete;

    private String            creator;

    private String            updator;

    private Integer           version;

    private String            settleUnit;

    private Integer           settlePeriod;

    private String            settleDay;

    private BigDecimal        prePayRate;

    private String            productDesc;

    private String            promotionUrl;
}