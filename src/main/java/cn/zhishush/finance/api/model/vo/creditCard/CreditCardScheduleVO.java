package cn.zhishush.finance.api.model.vo.creditCard;

import lombok.Data;

/**
 * @program: finance-app
 * @description: 信用卡进度
 * @author: Mr.Zhang
 * @create: 2018-12-27 14:27
 **/
@Data
public class CreditCardScheduleVO {
    /**
     * 信用卡名字
     */
    private String              cardName;
    /**
     * 进件日期
     */
    private String              IncomingDate;
    /**
     *  申请状态
     */
    private String              applyStatus;
}
