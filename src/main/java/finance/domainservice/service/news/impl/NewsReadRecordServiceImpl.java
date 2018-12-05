package finance.domainservice.service.news.impl;

import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import finance.api.model.response.BasicResponse;
import finance.core.common.enums.ReturnCode;
import finance.core.common.util.PreconditionUtils;
import finance.core.common.util.ResponseUtils;
import finance.domain.news.NewsInfo;
import finance.domain.news.NewsReadRecord;
import finance.domain.user.UserInfo;
import finance.domainservice.repository.CoinLogRepository;
import finance.domainservice.repository.NewsInfoRepository;
import finance.domainservice.repository.NewsReadRecordRepository;
import finance.domainservice.service.news.NewsReadRecordService;

/**
 * <p>资讯</p>
 *
 * @author lili
 * @version 1.0: NewsReadRecordServiceImpl.java, v0.1 2018/12/5 4:08 PM PM lili Exp $
 */
@Service("newsReadRecordService")
public class NewsReadRecordServiceImpl implements NewsReadRecordService {

    @Resource
    private NewsReadRecordRepository newsReadRecordRepository;

    @Resource
    private NewsInfoRepository       newsInfoRepository;

    @Resource
    private CoinLogRepository        coinLogRepository;

    @Resource
    private TransactionTemplate      transactionTemplate;

    @Override
    public BasicResponse localData(Long newsId, UserInfo userInfo) {

        NewsInfo newsInfo = newsInfoRepository.query(newsId);
        PreconditionUtils.checkArgument(Objects.nonNull(newsInfo), ReturnCode.NEWS_NOT_EXISTS);
        return transactionTemplate.execute(status -> {
            // 保存阅读记录
            NewsReadRecord newsReadRecord = new NewsReadRecord();
            newsReadRecord.setUserId(userInfo.getId());
            newsReadRecord.setNewsId(newsId);
            newsReadRecord.setRewardCoinNum(newsInfo.getRewardCoinNum());
            newsReadRecordRepository.save(newsReadRecord);
            // 金币奖励
            coinLogRepository.save(userInfo.getId(), newsInfo.getRewardCoinNum(), "阅读资讯文章奖励");
            return ResponseUtils.buildResp(ReturnCode.SUCCESS);
        });

    }
}
