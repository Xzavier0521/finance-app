package finance.core.dal.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceProfit.java, v0.1 2018/11/14 7:08 PM lili Exp $
 */
@Data
public class FinanceProfit implements Serializable {
    private static final long serialVersionUID = -2268558759989709329L;
    private Long              id;

    private Long              detailId;

    private Long              prodId;

    private String            prodName;

    private String            terminalName;

    private Long              terminalId;

    private String            terminalPhone;

    private BigDecimal        terminalMoney;

    private Long              parentId;

    private String            parentPhone;

    private String            parentName;

    private BigDecimal        parentMoney;

    private Long              grandParentId;

    private String            grandParentPhone;

    private String            grandParentName;

    private BigDecimal        grandParentMoney;

    private String            status;

    private Date              createTime;

    private Date              updateTime;

    private Integer           version;

    private Integer           isDelete;

}