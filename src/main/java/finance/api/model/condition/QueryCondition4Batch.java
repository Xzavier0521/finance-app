package finance.api.model.condition;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>批量查询条件</p>
 * @author lili
 * @version $Id: QueryCondition4Batch.java, v0.1 2018/10/11 11:45 AM lili Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryCondition4Batch extends QueryCondition {
    private static final long serialVersionUID = -1739173523246055200L;

    /**
     * 分页大小
     */
    private int               pageSize         = DEFAULT_PAGE_SIZE;
    /**
     * 当前页码
     */
    private int               currentPage      = 1;
    /**
     * 开始时间
     */
    private Date              beginTime;
    /**
     * 结束时间
     */
    private Date              endTime;

}
