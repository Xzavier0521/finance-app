package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version 1.0: SmsSendLogDO.java, v0.1 2018/11/26 4:42 PM lili Exp $
 */
@Data
public class SmsSendLogDO implements Serializable {
    private static final long serialVersionUID = -8251689449626860568L;
    private Long              id;

    private String            smsType;

    private String            mobileNum;

    private String            header;

    private String            body;

    private Integer           sendSuccess;

    private Integer           isDelete;

    private String            creator;

    private String            updator;

    private Integer           version;

    private Date              createTime;

    private Date              updateTime;

}