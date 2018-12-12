package cn.zhishush.finance.core.dal.dataobject.coin;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: CoinLogDO.java, v0.1 2018/11/25 1:41 PM lili Exp $
 */
@Data
public class CoinLogDO implements Serializable {
    private static final long serialVersionUID = 7460247824430976037L;
    private Long              id;

    private Long              userId;

    private Long              taskId;

    private String            taskName;

    private Integer           num;

    private Integer           isDelete;

    private String            creator;

    private String            updator;

    private Integer           version;

    private Date              createTime;

    private Date              updateTime;

}