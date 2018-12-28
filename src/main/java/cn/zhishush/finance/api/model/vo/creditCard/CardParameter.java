package cn.zhishush.finance.api.model.vo.creditCard;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @program: finance-app
 * @description: 信用卡进度查询参数
 * @author: Mr.Zhang
 * @create: 2018-12-27 15:25
 **/
@Data
public class CardParameter {
    /**
     * 银行代码
     */
    @NotEmpty(message = "银行代码必填")
    private String          bankCode;
    /**
     * 名字
     */
    @NotEmpty(message = "名字必填")
    private String          realName;
    /**
     * 身份证
     */
    /**
     * 身份证必填
     */
    private String          idCard;
    /**
     * 手机号码
     */
    @NotEmpty(message = "手机号码必填")
    private String          mobileNum;
    /**
     * 短信验证码
     */

    private String          smsCode;


    private int             pageSize;
    private Long            pageNum;

}
