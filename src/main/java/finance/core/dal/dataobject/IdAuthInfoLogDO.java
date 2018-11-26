package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: IdAuthInfoLogDO.java, v0.1 2018/11/25 4:44 PM lili Exp $
 */
@Data
public class IdAuthInfoLogDO implements Serializable {
    private static final long serialVersionUID = 5683020211857010167L;
    private Long              id;

    private Long              userId;

    private String            realName;

    private String            idNum;

    private String            accountNo;

    private String            code;

    private String            message;

    private String            dataState;

    private String            seqNo;

    private Date              createTime;

    private Date              updateTime;

    private String            creater;

    private String            updater;

    private Long              version;

    private Integer           isDelete;

}