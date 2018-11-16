package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceCoinLog.java, v0.1 2018/11/14 6:57 PM lili Exp $
 */
@Data
public class FinanceCoinLog implements Serializable {
    private static final long serialVersionUID = 7460247824430976037L;
    private Long              id;

    private Long              userId;

    private Long              taskId;

    private String            taskName;

    private Integer           num;

    private Integer           isDelete;

    private String            creator;

    private String            updator;

    private Integer           version;

    private Date              createTime;

    private Date              updateTime;

}