package finance.api.model.vo.loan;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>贷款产品信息</p>
 *
 * @author lili
 * @version 1.0: LoanInfoVO.java, v0.1 2018/11/29 12:52 PM PM lili Exp $
 */
@Data
public class LoanInfoVO implements Serializable {
    private static final long serialVersionUID = -8004739172142407372L;

    /**
     * 产品代码
     */
    private String            productCode;
    /**
     * 产品名称
     */
    private String            productName;
    /**
     * 产品描述
     */
    private String            productDesc;

    /**
     * 产品logo
     */
    private String            productLogoUrl;

    /**
     * 产品特点 以竖线进行分割
     */
    private String            productFeatures;

    /**
     * 标签
     */
    private String            productTags;
    /**
     * 放款时长
     */
    private String            lendingTime;

    /**
     * 贷款额度
     */
    private String            loanAmountStr;
    /**
     * 贷款期限
     */
    private String            loanTermStr;

    /**
     * 利息
     */
    private String            loanInterestStr;

    /**
     * 热度
     */
    private String            hotPercent;

    /**
     * 头部标签
     */
    private String            productTopTag;

    /**
     * 顺序
     */
    private Integer              order;
}
