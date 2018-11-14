package finance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import finance.api.model.base.Page;
import finance.model.po.FinanceNewsInfo;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceNewsInfoDAO.java, v0.1 2018/11/14 12:47 PM lili Exp $
 */
public interface FinanceNewsInfoDAO extends BaseDAO<FinanceNewsInfo, Long> {
    /**
     * 根据类型查询所有资讯
     * @param newsType
     * @param page
     * @return
     * @throws
     * @author panzhongkang
     * @date 2018/9/4 14:27
     */
    List<FinanceNewsInfo> queryNews(@Param("newsType") String newsType,
                                    @Param("page") Page<FinanceNewsInfo> page);
}