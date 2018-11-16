package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceUserGiftInfo.java, v0.1 2018/11/14 7:14 PM lili Exp $
 */
@Data
public class FinanceUserGiftInfo implements Serializable {
    private static final long serialVersionUID = -4800120915806313531L;
    private Long              id;

    private Long              userId;

    private Long              giftId;

    private Integer           giftStatus;

    private Date              giftProvideTime;

    private Date              createTime;

    private Date              updateTime;

    private Integer           isDelete;

    private String            creator;

    private String            updator;

    private Integer           version;

}