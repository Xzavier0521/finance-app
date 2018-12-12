package cn.zhishush.finance.domain.creditcard;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * <p>银行信息</p>
 * @author lili
 * @version 1.0: BankInfo.java, v0.1 2018/11/28 11:28 PM lili Exp $
 */
@Data
public class BankInfo implements Serializable {
    private static final long serialVersionUID = -950751326081433111L;
    /**
     * 主键
     */
    private Long              id;

    /**
     * 银行代码
     */
    private String            bankCode;

    /**
     * 银行名称
     */
    private String            bankName;

    /**
     * logo url
     */
    private String            bankLogoUrl;

    /**
     * 银行简介
     */
    private String            bankIntroduction;

    /**
     * 标签
     */
    private String            bankTag;

    /**
     * 起始额度
     */
    private BigDecimal        bankLimitMin;

    /**
     * 结束额度
     */
    private BigDecimal        bankLimitMax;

    /**
     * 预计通过率
     */
    private String            predictPassingRate;

    /**
     * 顺序
     */
    private Long              order;

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