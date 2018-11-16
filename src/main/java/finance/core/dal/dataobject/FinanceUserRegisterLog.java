package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceUserRegisterLog.java, v0.1 2018/11/14 7:17 PM lili Exp $
 */
@Data
public class FinanceUserRegisterLog implements Serializable {
    private static final long serialVersionUID = -2453397255580750770L;
    private Long              id;

    private Long              userId;

    private String            mobileNum;

    private String            ip;

    private String            userAgent;

    private Integer           isDelete;

    private String            creator;

    private String            updator;

    private Integer           version;

    private Date              createTime;

    private Date              updateTime;

}