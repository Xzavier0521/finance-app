package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceLoanDetail.java, v0.1 2018/11/14 7:04 PM lili Exp $
 */
@Data
public class FinanceLoanDetail implements Serializable {
    private static final long serialVersionUID = -8683117661330501287L;
    private Long              id;

    private Long              productId;

    private String            mark1;

    private String            mark2;

    private String            amountScope;

    private String            dateScope;

    private Date              createTime;

    private Date              updateTime;

    private Integer           isDelete;

    private String            creator;

    private String            updator;

    private Integer           version;
}