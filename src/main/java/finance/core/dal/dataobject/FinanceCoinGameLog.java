package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceCoinGameLog.java, v0.1 2018/11/14 6:57 PM lili Exp $
 */
@Data
public class FinanceCoinGameLog implements Serializable {
    private static final long serialVersionUID = 1412693001897380615L;
    private Long              id;

    private Long              userId;

    private Integer           outNum;

    private Integer           inNum;

    private Date              joinDate;

    private Date              joinTime;

    private Date              signTime;

    private Integer           status;

    private Integer           clockCount;

    private Integer           isDelete;

    private String            creator;

    private String            updator;

    private Integer           version;

    private Date              createTime;

    private Date              updateTime;

}