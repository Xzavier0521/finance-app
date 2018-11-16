package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceIdCardInfo.java, v0.1 2018/11/14 7:04 PM lili Exp $
 */
@Data
public class FinanceIdCardInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long              id;

    private Long              userId;

    private String            realName;

    private String            idNum;

    private Integer           authStatus;

    private Date              createTime;

    private Date              updateTime;

    private String            creater;

    private String            updater;

    private Long              version;

    private Integer           isDelete;

}