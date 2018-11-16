package finance.core.dal.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceCoinMoneyLog.java, v0.1 2018/11/14 6:58 PM lili Exp $
 */
@Data
public class FinanceCoinMoneyLog implements Serializable {
    private static final long serialVersionUID = -808047204408143261L;
    private Long              id;

    private Long              userId;

    private Integer           coinNum;

    private BigDecimal        money;

    private Long              orderId;

    private String            remark;

    private Integer           isDelete;

    private String            creator;

    private String            updator;

    private Integer           version;

    private Date              createTime;

    private Date              updateTime;

}