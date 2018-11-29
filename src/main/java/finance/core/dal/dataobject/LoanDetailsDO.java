package finance.core.dal.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * <p>贷款产品明细</p>
 * @author lili
 * @version 1.0: LoanDetailsDO.java, v0.1 2018/11/29 2:40 PM lili Exp $
 */
@Data
public class LoanDetailsDO implements Serializable {
    private static final long serialVersionUID = -3625023856938892416L;
    /**
     * 主键
     */
    private Long              id;

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
     * logo url
     */
    private String            productLogoUrl;

    /**
     * 放款时长
    放款时长
    
     */
    private String            lendingTime;

    /**
     * 参考利息
     */
    private String            referenceInterest;

    /**
     * 额度范围
     */
    private String            loanAmount;

    /**
     * 贷款期限
     */
    private String            loanTerm;

    /**
     * 申请人数
    
     */
    private Long              applyNum;

    /**
     * 均单时长
     */
    private String            avgOrderTime;

    /**
     * 均单金额
     */
    private BigDecimal        avgOrderAmount;

    /**
     * 贷款条件
     */
    private String            loanConditions;

    /**
     * 申请资料
     */
    private String            applyInfo;

    /**
     * 贷款流程url
     */
    private String            loanProcessUrl;

    /**
     * 费用说明
     */
    private String            feeDesc;

    /**
     * 还款说明
     */
    private String            repaymentDesc;

    /**
     * 分享图片地址
     */
    private String            shareImgUrl;

    /**
     * 返佣配置id
     */
    private String            cashbackConfigId;

    /**
     * 创建时间
     */
    private Date              createTime;

    /**
     * 更新时间
     */
    private Date              updateTime;

    /**
     * 创建者
     */
    private String            creator;

    /**
     * 更新者
     */
    private String            updator;

    /**
     * 是否删除 0-否 1-是 
     */
    private String            isDelete;

    /**
     * 版本号
     */
    private Long              version;

}