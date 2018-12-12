package cn.zhishush.finance.core.dal.dataobject.product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version 1.0: CashBackConfigDO.java, v0.1 2018/11/29 2:09 AM lili Exp $
 */
@Data
public class CashBackConfigDO implements Serializable {
    private static final long serialVersionUID = 6230885558125401670L;
    /**
     * 主键
     */
    private Long              id;

    /**
     * 配置id
     */
    private String              configId;

    /**
     * 产品id
     */
    private Long              productId;

    /**
     * 产品代码
     */
    private String            productCode;

    /**
     * 产品类型 信用卡-credit_card 贷款-load
     */
    private String            productType;

    /**
     * 有效开始日期
     */
    private Date              startEffectiveDate;

    /**
     * 有效结束时间
     */
    private Date              endEffectiveDate;

    /**
     * amount-金额, percentage-百分比
     */
    private String            cashbackType;

    /**
     * 总奖金
     */
    private BigDecimal        totalBonus;

    /**
     * 终端奖金
     */
    private BigDecimal        terminalBonus;

    /**
     * 直推奖金
     */
    private BigDecimal        directBonus;

    /**
     * 间推奖金
     */
    private BigDecimal        indirectBonus;
}