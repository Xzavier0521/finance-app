package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version $Id: OperationLogDO.java, v0.1 2018/11/25 4:45 PM lili Exp $
 */
@Data
public class OperationLogDO implements Serializable {

    private static final long serialVersionUID = -5833936804212878265L;
    private Long              id;

    private Long              logId;

    private Long              userId;

    private String            opCode;

    private String            opName;

    private String            creator;

    private String            updator;

    private Integer           version;

    private Date              createTime;

    private Date              updateTime;

}