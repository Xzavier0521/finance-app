package cn.zhishush.finance.core.dal.dataobject.user;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version 1.0: UserTaskInfoDO.java, v0.1 2018/11/26 4:51 PM lili Exp $
 */
@Data
public class UserTaskInfoDO implements Serializable {
    private static final long serialVersionUID = -7824696028411787132L;
    private Long              id;

    private Long              userId;

    private Long              taskId;

    private Integer           finishStatus;

    private Integer           nextStageNum;

    private Integer           isDelete;

    private String            creator;

    private String            updator;

    private Integer           version;

    private Date              createTime;

    private Date              updateTime;

}