package cn.zhishush.finance.domain.creditcard;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>信用卡申请信息</p>
 * @author lili
 * @version 1.0: CreditCardApplyInfo.java, v0.1 2018/11/29 5:19 PM lili Exp $
 */
@Data
public class CreditCardApplyInfo implements Serializable {
    private static final long serialVersionUID = 860554710339584575L;
    /**
     * 主键
     */
    private Long              id;

    /**
     * 用户id
     */
    private Long              userId;

    /**
     * 产品代码
     */
    private String            productCode;
    /**
     *  身份证号码
     */
    private String            identificationNumber;

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
    /**
     * 名字
     */
    private String            realName;
    /**
     * 核卡状态 not_queried-未查询
     */
    private String            progressStatus;




}