package finance.core.dal.dao;

import java.util.List;
import java.util.Map;

import finance.core.dal.dataobject.NewsReadRecordDO;

/**
 * <p>用户资讯阅读记录</p>
 * @author lili
 * @version 1.0: NewsReadRecordDAO.java, v0.1 2018/12/5 3:22 PM lili Exp $
 */
public interface NewsReadRecordDAO {

    int insertSelective(NewsReadRecordDO record);

    List<NewsReadRecordDO> query(Map parameters);

    int count(Map parameters);

    NewsReadRecordDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NewsReadRecordDO record);

}