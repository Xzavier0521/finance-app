package finance.core.dal.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: ActivityFixedAmountDetailDO.java, v0.1 2018/11/14 6:51 PM lili Exp $
 */
@Data
public class ActivityFixedAmountDetailDO implements Serializable {
    private static final long serialVersionUID = 6074865750250924858L;
    private Long              id;

    private Long              userId;

    private Long              activityId;

    private BigDecimal        dividedAmount;

    private Date              createTime;

    private Date              updateTime;

    private Integer           isDelete;

    private String            creater;

    private String            updater;

    private Integer           version;

}