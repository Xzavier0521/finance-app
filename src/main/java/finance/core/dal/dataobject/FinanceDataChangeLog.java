package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceDataChangeLog.java, v0.1 2018/11/14 7:00 PM lili Exp $
 */
@Data
public class FinanceDataChangeLog implements Serializable {
    private Long    id;

    private String  tableName;

    private Long    tableId;

    private String  beforeData;

    private String  afterData;

    private String  reason;

    private String  remark;

    private Date    createTime;

    private Date    updateTime;

    private Long    creater;

    private Long    updater;

    private Boolean isDelete;

    private Integer version;

}