package finance.core.dal.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version $Id: ExcelDetailDO.java, v0.1 2018/11/25 4:42 PM lili Exp $
 */
@Data
public class ExcelDetailDO implements Serializable {
    private static final long serialVersionUID = -4024666359996456173L;
    private Long              id;

    private String            allId;

    private Long              prodId;

    private String            realName;

    private String            mobile;

    private BigDecimal        money;

    private Date              transactDate;

    private String            errorMessge;

    private String            status;

    private String            type;

    private Date              createTime;

    private Date              updateTime;

    private Integer           version;

    private Integer           isDelete;

    /**
     * 针对保险的产品ID，不是什么父级ID
     */
    private String            parentID;

}