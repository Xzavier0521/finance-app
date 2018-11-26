package finance.core.dal.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: ActivityFixedAmountMainDO.java, v0.1 2018/11/14 6:52 PM lili Exp $
 */
@Data
public class ActivityFixedAmountMainDO implements Serializable {
    private static final long serialVersionUID = 1739108484406424788L;
    private Long              id;

    private Long              userId;

    private BigDecimal        totalAmount;

    private Long              state;

    private Integer           joinNum;

    private BigDecimal        dividedAmount;

    private Date              createTime;

    private Date              updateTime;

    private Integer           isDelete;

    private String            creater;

    private String            updater;

    private Integer           version;

}