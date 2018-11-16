package finance.api.model.request;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import finance.core.common.enums.RedEnvelopeRainTimeCodeEnum;

/**
 * <p>红包雨活动数据查询请求</p>
 *
 * @author lili
 * @version 1.0: RedEnvelopeRainDataQueryRequest.java, v0.1 2018/11/15 12:18 AM PM lili Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RedEnvelopeRainDataQueryRequest extends BasicRequest {

    private static final long           serialVersionUID = 5182128817766565159L;
    /**
     * 每页记录数
     */
    @NotNull(message = "pageSize不能为空")
    private Integer                     pageSize;
    /**
     * 活动代码
     */
    private String                      activityCode;
    /**
     * 第几页
     */
    @NotNull(message = "pageNum不能为空")
    private Long                        pageNum;
    /**
     * 活动日期
     */
    private Integer                     activityDay;
    /**
     * 时间代码
     */
    // @NotNull(message = "timeCode不能为空")
    private RedEnvelopeRainTimeCodeEnum timeCode;
}
