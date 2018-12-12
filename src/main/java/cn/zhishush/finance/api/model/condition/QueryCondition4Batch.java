package cn.zhishush.finance.api.model.condition;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>批量查询条件</p>
 * @author lili
 * @version 1.0: QueryCondition4Batch.java, v0.1 2018/11/26 7:00 PM lili Exp $
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
