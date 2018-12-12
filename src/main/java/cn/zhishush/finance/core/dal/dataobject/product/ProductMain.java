package cn.zhishush.finance.core.dal.dataobject.product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version 1.0: ProductMain.java, v0.1 2018/11/26 4:39 PM lili Exp $
 */
@Data
public class ProductMain implements Serializable {
    private static final long serialVersionUID = -5841560448486042562L;
    private Long              id;

    private String            productName;

    private Integer           type;

    private String            redirectUrl;

    private Integer           amountType;

    private BigDecimal        totalBonus;

    private BigDecimal        terminalBonus;

    private BigDecimal        directBonus;

    private BigDecimal        indirectBonus;

    private String            logoUrl;

    private Integer           seqNo;

    private String            cashbackDate;

    private Integer           isShow;

    private Date              createTime;

    private Date              updateTime;

    private Integer           isDelete;

    private String            creator;

    private String            updator;

    private Integer           version;

    private String            settleUnit;

    private Integer           settlePeriod;

    private String            settleDay;

    private BigDecimal        prePayRate;

    private String            productDesc;

    private String            promotionUrl;
}