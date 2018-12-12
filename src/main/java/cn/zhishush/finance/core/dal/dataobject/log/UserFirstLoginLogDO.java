package cn.zhishush.finance.core.dal.dataobject.log;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version 1.0: UserFirstLoginLogDO.java, v0.1 2018/11/26 4:46 PM lili Exp $
 */
@Data
public class UserFirstLoginLogDO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long              id;

    private Long              userId;

    private String            platformCode;

    private String            platformDetail;

    private Integer           isDelete;

    private String            creator;

    private String            updator;

    private Integer           version;

    private Date              createTime;

    private Date              updateTime;

}