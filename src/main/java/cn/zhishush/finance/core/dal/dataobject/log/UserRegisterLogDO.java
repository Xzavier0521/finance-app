package cn.zhishush.finance.core.dal.dataobject.log;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>用户注册日志</p>
 * @author lili
 * @version 1.0: UserRegisterLogDO.java, v0.1 2018/11/26 4:50 PM lili Exp $
 */
@Data
public class UserRegisterLogDO implements Serializable {
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