package finance.api.model.vo.loan;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import finance.api.model.vo.cashback.CashBackConfigTableVO;
import finance.api.model.vo.common.BodyVO;

/**
 * <p>贷款产品明细</p>
 *
 * @author lili
 * @version 1.0: LoanDetailsVO.java, v0.1 2018/11/29 2:51 PM PM lili Exp $
 */
@Data
public class LoanDetailsVO implements Serializable {
    private static final long     serialVersionUID = -8886563362998110407L;

    /**
     * 产品代码
     */
    private String                productCode;

    /**
     * 产品名称
     */
    private String                productName;

    /**
     * 产品简述
     */
    private String                productDesc;

    /**
     * logo url
     */
    private String                productLogoUrl;

    /**
     * 放款时长
     */
    private String                lendingTime;

    /**
     * 参考利息
     */
    private String                referenceInterestStr;

    /**
     * 额度范围
     */
    private String                loanAmountStr;

    /**
     * 贷款期限
     */
    private String                loanTermStr;

    /**
     * 申请人数
     */
    private Long                applyNum;

    /**
     * 均单时长
     */
    private String                avgOrderTime;

    /**
     * 均单金额
     */
    private String                avgOrderAmount;

    /**
     * 返佣配置
     */
    private CashBackConfigTableVO cashBackConfigTable;

    /**
     * body
     */
    private List<BodyVO>          bodyList;
}
