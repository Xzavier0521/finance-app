package cn.zhishush.finance.core.dal.dao.news;

import java.util.List;

import cn.zhishush.finance.core.dal.dao.base.BaseDAO;
import cn.zhishush.finance.core.dal.dataobject.news.NewsInfoDO;
import org.apache.ibatis.annotations.Param;

import cn.zhishush.finance.api.model.base.Page;

/**
 * <p>资讯</p>
 * 
 * @author lili
 * @version $Id: NewsInfoDAO.java, v0.1 2018/11/14 12:47 PM lili Exp $
 */
public interface NewsInfoDAO extends BaseDAO<NewsInfoDO, Long> {
    /**
     * 根据类型查询所有资讯
     * @param newsType 类型
     * @param page 分页
     * @return List<NewsInfoDO>
     */
    List<NewsInfoDO> queryNews(@Param("newsType") String newsType,
                               @Param("page") Page<NewsInfoDO> page);
}