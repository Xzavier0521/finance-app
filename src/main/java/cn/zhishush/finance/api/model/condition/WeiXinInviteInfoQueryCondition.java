package cn.zhishush.finance.api.model.condition;

import cn.zhishush.finance.core.common.enums.WeChatSendStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: WeiXinInviteInfoQueryCondition.java, v0.1 2018/12/4 3:35 PM PM lili Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeiXinInviteInfoQueryCondition extends QueryCondition4Batch {

    private static final long    serialVersionUID = 451986711339688003L;
    /**
     * 活动代码
     */
    private String               activityCode;
    /**
     * 发送状态
     */
    private WeChatSendStatusEnum isSend;
}
