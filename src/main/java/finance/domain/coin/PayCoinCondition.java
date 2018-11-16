package finance.domain.coin;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import finance.api.model.response.BasicResponse;
import finance.domain.basic.BasicParameter;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: PayCoinCondition.java, v0.1 2018/11/15 10:31 PM PM lili Exp $
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayCoinCondition implements Serializable {
    private static final long                               serialVersionUID = -8478842135099316404L;
    /**
     * 需要执行的函数列表
     */
    List<Function<BasicParameter, ? extends BasicResponse>> functions;
    /**
     * 参数
     */
    private BasicParameter                                  parameter;
    /**
     * 前缀
     */
    private String                                          keyPrefix;
    /**
     * 用户id
     */
    private Long                                            userId;
    /**
     * 支付的金币数目
     */
    private Integer                                         coinNum;
    /**
     * 支付原因
     */
    private String                                          payReason;
}
