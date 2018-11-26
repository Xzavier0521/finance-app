package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: ExcelAllDO.java, v0.1 2018/11/25 4:41 PM lili Exp $
 */
@Data
public class ExcelAllDO implements Serializable {
    private static final long serialVersionUID = -223083400687438690L;
    private Long              id;

    private String            batchNo;

    private String            creater;

    private String            updater;

    private Long              detailsNum;

    private Long              detailsValidNum;

    private Long              failNum;

    private String            status;

    private Date              createTime;

    private Date              updateTime;

    private Integer           version;

    private Integer           isDelete;

}