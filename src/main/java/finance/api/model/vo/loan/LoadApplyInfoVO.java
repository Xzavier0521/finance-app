package finance.api.model.vo.loan;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>贷款申请记录</p>
 *
 * @author lili
 * @version 1.0: LoadApplyInfo.java, v0.1 2018/11/29 2:41 AM PM lili Exp $
 */
@Data
public class LoadApplyInfoVO implements Serializable {

    private static final long serialVersionUID = 1557925035529358260L;
    /**
     * 主键
     */
    private Long              id;

    /**
     * 用户id
     */
    private Long              userId;

    /**
     * 身份证号码
     */
    private String            mobileNum;

    /**
     * 姓名
     */
    private String            realName;

    /**
     * 手机号码
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
     * 月收入
     */
    private String            monthlySalary;

    /**
     * 工作年限
     */
    private String            workingYears;

    /**
     * 车辆情况
     */
    private String            vehicleSituation;

    /**
     * 职业类别
     */
    private String            careerCategory;

    /**
     * 预估额度
     */
    private String            estimatedAmount;

    /**
     * 创建时间
     */
    private Date createTime;

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
