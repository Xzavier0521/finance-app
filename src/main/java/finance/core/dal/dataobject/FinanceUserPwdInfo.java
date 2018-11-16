package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class FinanceUserPwdInfo implements Serializable {
    private static final long serialVersionUID = -5668491116047580513L;
    private Long              id;

    private Long              userId;

    private String            pwdType;

    private String            pwd;

    private Integer           isDelete;

    private String            creator;

    private String            updator;

    private Integer           version;

    private Date              createTime;

    private Date              updateTime;

}