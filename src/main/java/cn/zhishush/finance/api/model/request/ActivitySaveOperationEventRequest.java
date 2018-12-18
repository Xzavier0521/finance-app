package cn.zhishush.finance.api.model.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * <p>用户操作事件保存</p>
 *
 * @author lili
 * @version 1.0: ActivitySaveOperationEventRequest.java, v0.1 2018/12/18 4:30 PM PM lili Exp $
 */
@Data
public class ActivitySaveOperationEventRequest implements Serializable {
    private static final long serialVersionUID = 6408238467774146553L;

    /**
     * 活动代码
     */
    @NotBlank(message = "活动代码不能为空")
    private String            activityCode;

    /**
     * 事件类型 join-参与活动,done-完成活动 older_join-成为推广人员
     */
    @NotBlank(message = "事件类型不能为空")
    private String            eventType;
}
