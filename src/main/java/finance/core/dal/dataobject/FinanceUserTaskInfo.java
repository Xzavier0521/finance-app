package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceUserTaskInfo.java, v0.1 2018/11/14 7:17 PM lili Exp $
 */
@Data
public class FinanceUserTaskInfo implements Serializable {
    private static final long serialVersionUID = -7824696028411787132L;
    private Long              id;

    private Long              userId;

    private Long              taskId;

    private Integer           finishStatus;

    private Integer           nextStageNum;

    private Integer           isDelete;

    private String            creator;

    private String            updator;

    private Integer           version;

    private Date              createTime;

    private Date              updateTime;

}