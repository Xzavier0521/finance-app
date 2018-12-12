package cn.zhishush.finance.domainservice.repository.news;

import cn.zhishush.finance.domain.news.NewsInfo;

/**
 * <p>资讯</p>
 *
 * @author lili
 * @version 1.0: NewsInfoRepository.java, v0.1 2018/12/5 4:40 PM PM lili Exp $
 */
public interface NewsInfoRepository {

    NewsInfo query(Long id);
}
