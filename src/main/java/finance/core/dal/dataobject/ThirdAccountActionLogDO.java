package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version 1.0: ThirdAccountActionLogDO.java, v0.1 2018/11/26 4:43 PM lili Exp $
 */
@Data
public class ThirdAccountActionLogDO implements Serializable {
    private static final long serialVersionUID = -1023273519848059890L;
    private Long              id;

    private Long              userId;

    private String            channel;

    private String            publicName;

    private String            openId;

    private String            actionType;

    private Integer           isDelete;

    private String            creator;

    private String            updator;

    private Integer           version;

    private Date              createTime;

    private Date              updateTime;

}