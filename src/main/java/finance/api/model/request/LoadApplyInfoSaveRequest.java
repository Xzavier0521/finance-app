package finance.api.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>贷款申请</p>
 *
 * @author lili
 * @version 1.0: LoadApplyInfoSaveRequest.java, v0.1 2018/11/29 2:41 AM PM lili Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoadApplyInfoSaveRequest extends BasicRequest {

    private static final long serialVersionUID = -257238297642612997L;
    /**
     * 姓名
     */
    private String            realName;
    /**
     * 身份证号码
     */
    private String            idCard;
    /**
     * 教育程度
     */
    private String            educationLevel;
    /**
     * 是否缴纳社保 Y-是 N-否
     */
    private String            isPaySocialSecurity;
    /**
     * 月可接受最高还款额度
     */
    private String            maxMonthlyRepaymentLimit;
    /**
     * 	车辆情况
     */
    private String            vehicleSituation;

    /**
     * 月收入
     */
    private String            monthlySalary;

    /**
     * 职业类别
     */
    private String            careerCategory;
    /**
     * 工作年限
     */
    private String            workingYears;
    /**
     * 预估额度
     */
    private String            estimatedAmount;

    /**
     * 产品代码
     */
    private String            productCode;
}
