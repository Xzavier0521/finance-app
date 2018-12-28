package cn.zhishush.finance.api.model.vo.creditCard;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>信用卡申请信息</p>
 * @author lili
 * @version 1.0: CreditCardApplyInfoVO.java, v0.1 2018/11/29 5:19 PM lili Exp $
 */
@Data
public class CreditCardApplyInfoVO implements Serializable {
    private static final long serialVersionUID = 860554710339584575L;

    /**
     * 申请id
     */
    private Long              id;
    /**
     *银行code
     */
    private String            bankCode;
    /**
     * logo
     */
    private String            logoUrl;
    /**
     * 名字
     */
    private String            realName;
    /**
     * 电话
     */
    private String            mobileNum;
    /**
     * 用户id
     */
    private Long              userId;

    /**
     * 标题:招商银行信用卡审批记录
     */
    private String            title;
    /**
     * bank logo url
     */
    private String            bankLogoUrl;
    /**
     * 产品代码
     */
    private String            productCode;
    /**
     * 产品名称
     */
    private String            productName;

    /**
     * 标签：新用户核卡返现120元
     */
    private String            productTag;

    private  String           applyStatus;

    /**
     * 申请时间
     */
    private String              applyTime;
    /**
     * 申请状态:申请中
     */

    private String            progressStatus;

    /**
     *  身份证号码
     */
    private String            identificationNumber;
    /**
     * 查询类型
     */
    private String             queryType;


    private String             queryStatus;

    private String             content;

}