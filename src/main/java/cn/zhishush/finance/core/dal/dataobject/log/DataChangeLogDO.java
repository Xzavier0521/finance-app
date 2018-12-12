package cn.zhishush.finance.core.dal.dataobject.log;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: DataChangeLogDO.java, v0.1 2018/11/25 4:40 PM lili Exp $
 */
@Data
public class DataChangeLogDO implements Serializable {
    private static final long serialVersionUID = -941823881069545883L;
    private Long              id;

    private String            tableName;

    private Long              tableId;

    private String            beforeData;

    private String            afterData;

    private String            reason;

    private String            remark;

    private Date              createTime;

    private Date              updateTime;

    private Long              creater;

    private Long              updater;

    private Boolean           isDelete;

    private Integer           version;

}