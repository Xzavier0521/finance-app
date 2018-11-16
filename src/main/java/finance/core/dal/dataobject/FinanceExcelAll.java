package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceExcelAll.java, v0.1 2018/11/14 7:01 PM lili Exp $
 */
@Data
public class FinanceExcelAll implements Serializable {
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