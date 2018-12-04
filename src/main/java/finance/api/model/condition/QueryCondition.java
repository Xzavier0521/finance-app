package finance.api.model.condition;

import java.io.Serializable;

import lombok.Data;
import finance.core.common.constants.QueryConstants;

/**
 * <p>查询条件</p>
 * @author lili
 * @version 1.0: QueryCondition.java, v0.1 2018/11/26 7:00 PM lili Exp $
 */
@Data
public class QueryCondition implements QueryConstants, Serializable {
    private static final long serialVersionUID = -1631598695528368695L;

    /**
     * 超时时间
     */
    private Integer           httpTimeout;
    /**
     * 版本号
     */
    private String            version          = DEFAULT_VERSION;
    /**
     * 客户端标识
     */
    private String            clientId;
    /**
     * 主键
     */
    private String            primaryKey;

    /**
     * 默认构造
     */
    public QueryCondition() {
    }

}
