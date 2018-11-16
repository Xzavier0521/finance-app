package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceUserLoginLog.java, v0.1 2018/11/14 7:15 PM lili Exp $
 */
@Data
public class FinanceUserLoginLog implements Serializable {
    private static final long serialVersionUID = 6215816644082068342L;
    private Long              id;

    private Long              userId;

    private String            type;

    private String            loginName;

    private String            loginStatus;

    private String            loginDesc;

    private String            ip;

    private String            userAgent;

    private String            platformCode;

    private String            platformDetail;

    private Integer           isDelete;

    private String            creator;

    private String            updator;

    private Integer           version;

    private Date              createTime;

    private Date              updateTime;

}