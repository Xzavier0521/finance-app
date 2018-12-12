package cn.zhishush.finance.core.common.constants;

import java.util.Arrays;
import java.util.List;

/**
 * <p>统一常量基础定义</p>
 * 
 * @author lili
 * @version $Id: QueryConstants.java, v0.1 2018/10/11 11:43 AM lili Exp $
 */
public interface QueryConstants {
    /**
     * HTTP请求路径分隔符
     */
    String       HTTP_SPLIT             = "/";
    /**
     * 主键查询后缀
     */
    String       PRIMARY_KEY_SUFFIX     = "/{primaryKey}";
    /**
     * 默认版本号
     */
    String       DEFAULT_VERSION        = "1.0";
    /**
     * 默认分页大小
     */
    int          DEFAULT_PAGE_SIZE      = 20;

    /**
     * 默认批量更新大小
     */
    int          DEFAULT_MAX_PAGE_SIZE  = 500;

    /**
     * 版本号
     */
    String       VERSION                = "version";
    /**
     * 主键查询后缀
     */
    String       PRIMARY_KEY            = "primaryKey";
    /**
     * 每页大小
     */
    String       PAGE_SIZE              = "pageSize";
    /**
     * 当前页
     */
    String       CURRENT_PAGE           = "currentPage";
    /**
     * 查询汇总金额
     */
    String       QUERY_TOTAL_AMOUNT     = "queryTotalAmount";
    /**
     * 起始行
     */
    String       START_ROW              = "startRow";
    /**
     * 结束行
     */
    String       END_ROW                = "endRow";

    /**
     * 默认剔除属性
     */
    List<String> DEFAULT_EXCLUDE_FIELDS = Arrays.asList(VERSION, PAGE_SIZE, CURRENT_PAGE,
        QUERY_TOTAL_AMOUNT);

    /**
     * 默认的用户id，保留
     */
    Long         DEFAULT_USER_ID        = 0L;
}
