package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceFinancialProductDetail.java, v0.1 2018/11/14 7:02 PM lili Exp $
 */
@Data
public class FinanceFinancialProductDetail implements Serializable {
    private static final long serialVersionUID = -6969196466171233616L;
    private Long              id;

    private Long              productId;

    private String            mark;

    private String            aveRevenue;

    private String            productBackground;

    private String            grade;

    private String            backgroundStrength;

    private String            riskControl;

    private String            operationCapability;

    private String            startAmount;

    private String            startPeriod;

    private String            rebackName;

    private String            rebackValue;

    private String            totalReturn;

    private Date              createTime;

    private Date              updateTime;

    private Integer           isDelete;

    private String            creater;

    private String            updater;

    private Integer           version;

    private String            cashbackRule;

}