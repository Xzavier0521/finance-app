package cn.zhishush.finance.api.model.vo.cashback;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>返现规则</p>
 *
 * @author lili
 * @version 1.0: CashBackConfigRuleVO.java, v0.1 2018/12/14 5:26 PM PM lili Exp $
 */
@Data
public class CashBackConfigRuleVO implements Serializable {

    private static final long serialVersionUID = -652585646142980008L;
    /**
     * 产品名称
     */
    private String            productName;

    /**
     * 产品代码
     */
    private String            productCode;

    /**
     * 总奖金
     */
    private String        totalBonus;

    /**
     * 终端奖金
     */
    private String        terminalBonus;

    /**
     * 直推奖金
     */
    private String        directBonus;

    /**
     * 间推奖金
     */
    private String        indirectBonus;

}
