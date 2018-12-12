package cn.zhishush.finance.domainservice.repository.news.impl;

import javax.annotation.Resource;

import cn.zhishush.finance.core.dal.dao.news.NewsInfoDAO;
import cn.zhishush.finance.domain.news.NewsInfo;
import cn.zhishush.finance.domainservice.repository.news.NewsInfoRepository;
import org.springframework.stereotype.Repository;

import cn.zhishush.finance.domainservice.converter.news.NewsInfoConverter;

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
