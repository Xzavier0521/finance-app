package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 *  <p>注释</p>
 * @author  lili
 * @version :1.0  FinanceOperationLog.java.java, v 0.1 2018/9/28 下午8:42 lili Exp $
 */
@Data
public class FinanceOperationLog implements Serializable {

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