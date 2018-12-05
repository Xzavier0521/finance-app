package finance.domainservice.repository.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

import finance.core.dal.dao.NewsReadRecordDAO;
import finance.domain.news.NewsReadRecord;
import finance.domainservice.converter.NewsReadRecordConverter;
import finance.domainservice.repository.NewsReadRecordRepository;

/**
 * <p>资讯</p>
 *
 * @author lili
 * @version 1.0: NewsReadRecordRepositoryImpl.java, v0.1 2018/12/5 3:35 PM PM lili Exp $
 */
@Repository("newsReadRecordRepository")
public class NewsReadRecordRepositoryImpl implements NewsReadRecordRepository {

    @Resource
    private NewsReadRecordDAO newsReadRecordDAO;

    @Override
    public int save(NewsReadRecord newsReadRecord) {
        return newsReadRecordDAO.insertSelective(NewsReadRecordConverter.convert(newsReadRecord));
    }

    @Override
    public NewsReadRecord query(Long newsId, Long userId) {
        NewsReadRecord newsReadRecord = null;
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("userId", userId);
        parameters.put("newsId", newsId);
        List<NewsReadRecord> newsReadRecordList = NewsReadRecordConverter
            .convert2List(newsReadRecordDAO.query(parameters));
        if (CollectionUtils.isNotEmpty(newsReadRecordList)) {
            newsReadRecord = newsReadRecordList.get(0);
        }
        return newsReadRecord;
    }
}
