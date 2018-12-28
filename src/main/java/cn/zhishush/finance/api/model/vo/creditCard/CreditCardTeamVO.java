package cn.zhishush.finance.api.model.vo.creditCard;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: finance-app
 * @description: 信用卡团队
 * @author: Mr.Zhang
 * @create: 2018-12-27 12:04
 **/
@Data
public class CreditCardTeamVO implements Serializable {

    /**
     * 申请id
     */
    private Long              id;
    /**
     *银行code
     */
    private String            bankCode;
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
     * bank logo url
     */
    private String            logoUrl;

    /**
     * 产品名称
     */
    private String            productName;


    /**
     * 申请时间
     */
    private String              applyTime;
    /**
     * 申请状态:申请中
     */

    private String            progressStatus;
    /**
     * 查询类型
     */
    private String             queryType;
    /**
     * 返现文本
     */
    private String             content;
}
