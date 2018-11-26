package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version 1.0: UserLoginLogDO.java, v0.1 2018/11/26 4:49 PM lili Exp $
 */
@Data
public class UserLoginLogDO implements Serializable {
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