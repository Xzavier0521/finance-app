package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceStepRewardsActivity.java, v0.1 2018/11/14 7:10 PM lili Exp $
 */
@Data
public class FinanceStepRewardsActivity implements Serializable {
    private static final long serialVersionUID = -6766765364483980739L;
    private Long              id;

    private Long              userId;

    private Integer           inviteNum;

    private Date              createTime;

    private Date              updateTime;

    private String            creater;

    private String            updater;

    private Long              versionNum;

    private Integer           isDelete;

}