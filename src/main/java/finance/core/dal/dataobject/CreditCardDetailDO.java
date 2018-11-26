package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: CreditCardDetailDO.java, v0.1 2018/11/25 4:40 PM lili Exp $
 */
@Data
public class CreditCardDetailDO implements Serializable {
    private static final long serialVersionUID = -3864410855824681672L;
    private Long              id;

    private Long              productId;

    private String            passRate;

    private String            rebackCashDesc;

    private String            detailPageUrl;

    private Date              createTime;

    private Date              updateTime;

    private Integer           isDelete;

    private String            creator;

    private String            updator;

    private Integer           version;

    private String            maxAmount;

    private String            auditLength;
    private Long              otherChannelProductId;

}