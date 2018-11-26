package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: ProductMainPageDO.java, v0.1 2018/11/26 4:40 PM lili Exp $
 */
@Data
public class ProductMainPageDO implements Serializable {
    private static final long serialVersionUID = -7029287819683031012L;
    private Long              id;

    private String            productName;

    private Integer           category;

    private String            redirectUrl;

    private String            productDes;

    private Integer           productType;

    private String            maxAmount;

    private String            feeRate;

    private String            logoUrl;

    private Integer           seqNo;

    private Integer           isShow;

    private Date              createTime;

    private Date              updateTime;

    private Integer           isDelete;

    private String            creator;

    private String            updator;

    private Integer           version;

}