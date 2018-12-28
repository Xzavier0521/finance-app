package cn.zhishush.finance.core.dal.dataobject.product;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>信用卡申请信息</p>
 * @author lili
 * @version 1.0: CreditCardApplyInfoDO.java, v0.1 2018/11/29 5:19 PM lili Exp $
 */
@Data
public class CreditCardApplyInfoDO implements Serializable {
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
     * 银行code
     */
    private String            bankCode;
    /**
     *审核状态
     */
    private String            progressStatus;
    /**
     * 进件日期
     */
    private Date              incomingTime;
    /**
     * 名字
     */
    private String            realName;
//    /**
//     * 申请时间
//     */
//    private Date              applyTime;


}