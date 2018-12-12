package cn.zhishush.finance.core.dal.dataobject.user;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version 1.0: UserPwdInfoDO.java, v0.1 2018/11/26 4:49 PM lili Exp $
 */
@Data
public class UserPwdInfoDO implements Serializable {
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