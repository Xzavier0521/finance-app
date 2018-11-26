package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: CoinGameDO.java, v0.1 2018/11/25 1:39 PM lili Exp $
 */
@Data
public class CoinGameDO implements Serializable {
    private static final long serialVersionUID = 3383774222648441070L;
    private Long              id;

    private String            taskType;

    private String            taskName;

    private String            taskDesc;

    private String            effect;

    private Integer           num;

    private String            logoUrl;

    private String            redirectUrl;

    private Integer           status;

    private Date              planInvalidDate;

    private Integer           seqNo;

    private Integer           gameType;

    private Integer           isDelete;

    private String            creator;

    private String            updator;

    private Integer           version;

    private Date              createTime;

    private Date              updateTime;

}