package finance.domainservice.repository;

import finance.domain.news.NewsReadRecord;

/**
 * <p>资讯</p>
 *
 * @author lili
 * @version 1.0: NewsReadRecordRepository.java, v0.1 2018/12/5 3:27 PM PM lili Exp $
 */
public interface NewsReadRecordRepository {

    int save(NewsReadRecord newsReadRecord);

    NewsReadRecord query(Long newsId, Long userId);
}
