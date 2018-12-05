package finance.domainservice.repository.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import finance.core.dal.dao.NewsInfoDAO;
import finance.domain.news.NewsInfo;
import finance.domainservice.converter.NewsInfoConverter;
import finance.domainservice.repository.NewsInfoRepository;

/**
 * <p>资讯</p>
 *
 * @author lili
 * @version 1.0: NewsInfoRepositoryImpl.java, v0.1 2018/12/5 4:43 PM PM lili Exp $
 */
@Repository("newsInfoRepository")
public class NewsInfoRepositoryImpl implements NewsInfoRepository {

    @Resource
    private NewsInfoDAO newsInfoDAO;

    @Override
    public NewsInfo query(Long id) {
        return NewsInfoConverter.convert(newsInfoDAO.selectByPrimaryKey(id));
    }
}
