package cn.zhishush.finance.api.model.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页对象.
 * 
 * @author hewenbin
 * @version $Id: Page.java, v 0.1 2017年10月30日 下午7:39:49 hewenbin Exp $
 */
public class Page<T> {

    public final static Integer PAGE_SIZE = 10;
    public final static Long    PAGE_NUM  = 1L;
    /** 每页记录数 */
    private Integer             pageSize  = PAGE_SIZE;
    /** 页码 */
    private Long                pageNum   = PAGE_NUM;
    /** 记录总数 */
    private Long                totalCount;
    /** 总页数 */
    private Long                totalPage;
    /** 结果集 */
    private List<T>             dataList;

    /**
     * @param pageSize
     *            每页记录数
     * @param pageNum
     *            第几页
     */
    public Page(Integer pageSize, Long pageNum) {
        if (pageSize != null) {
            this.pageSize = pageSize;
        }
        if (pageNum != null) {
            this.pageNum = pageNum;
        }
        this.totalCount = 0L;
        this.totalPage = 0L;
        this.dataList = new ArrayList();
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Long getPageNum() {
        return pageNum;
    }

    public Long getBeginNum() {
        return (pageNum - 1) * pageSize;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
        this.totalPage = (totalCount + pageSize - 1) / pageSize;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "Page:{\"pageSize\":\"" + pageSize + "\",\"pageNum\":\"" + pageNum
               + "\",\"totleCount\":\"" + totalCount + "\",\"totalPage\":\"" + totalPage
               + "\",\"dataList\":\"" + dataList + "\"}";
    }

}
