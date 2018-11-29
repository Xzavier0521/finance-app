package finance.domain.loan;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>贷款产品信息</p>
 * @author lili
 * @version 1.0: LoanInfo.java, v0.1 2018/11/29 1:37 PM lili Exp $
 */
@Data
public class LoanInfo implements Serializable {
    private static final long serialVersionUID = 8865501739899613355L;
    /**
     * 主键
     */
    private Long              id;

    /**
     * 产品代码
     */
    private String            productCode;

    /**
     * 产品代码
     */
    private String            productName;

    /**
     * 产品简述
     */
    private String            productDesc;

    /**
     * 产品logo
     */
    private String            productLogoUrl;

    /**
     * 产品特点 以竖线进行分割 额度高|放款快|利息低
     */
    private String            productFeatures;

    /**
     * 放款时长
    
     */
    private String            lendingTime;

    /**
     * 贷款额度
    
     */
    private String            loanAmount;

    /**
     * 利息
     */
    private String            loanInterest;

    /**
     * 贷款期限
    
     */
    private String            loanTerm;

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
    private Integer           order;

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