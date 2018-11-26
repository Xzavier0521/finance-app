package finance.core.dal.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: StepRewardsAmountDO.java, v0.1 2018/11/26 4:43 PM lili Exp $
 */
@Data
public class StepRewardsAmountDO implements Serializable {
    private static final long serialVersionUID = -3628329170522504891L;
    private Long              id;

    private BigDecimal        currentAmount;

    private Date              createTime;

    private Date              updateTime;

    private String            creater;

    private String            updater;

    private Long              versionNum;

    private Integer           isDelete;

}