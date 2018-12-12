package cn.zhishush.finance.api.model.request;

import java.io.Serializable;

import cn.zhishush.finance.core.common.enums.ActivityRewardTypeEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: ActivityRecordRequest.java, v0.1 2018/11/26 11:25 AM PM lili Exp $
 */
@Data
public class ActivityRecordRequest implements Serializable {

    private static final long      serialVersionUID = 379523400892116372L;
    /**
     * 活动代码
     */
    @NotBlank(message = "活动代码不能为空")
    private String                 activityCode;
    /**
     * 活动奖励类型
     */
    @NotNull(message = "活动奖励类型不能为空")
    private ActivityRewardTypeEnum activityRewardType;
}
