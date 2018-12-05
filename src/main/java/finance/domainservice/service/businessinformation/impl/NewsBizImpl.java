package finance.domainservice.service.businessinformation.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import finance.api.model.base.Page;
import finance.api.model.vo.info.NewsDetailVO;
import finance.core.common.enums.NewsTag1;
import finance.core.common.enums.NewsTag2;
import finance.core.common.util.ConvertBeanUtil;
import finance.core.dal.dao.NewsInfoDAO;
import finance.core.dal.dataobject.NewsInfoDO;
import finance.domain.news.NewsReadRecord;
import finance.domain.user.UserInfo;
import finance.domainservice.repository.NewsReadRecordRepository;
import finance.domainservice.service.businessinformation.NewsBiz;

/**
 * <p>资讯</p>
 * 
 * @author lili
 * @version $Id: NewsBizImpl.java, v0.1 2018/11/14 1:52 PM lili Exp $
 */
@Service("newsBiz")
public class NewsBizImpl implements NewsBiz {

    @Resource
    private NewsInfoDAO              newsInfoMapper;

    @Resource
    private NewsReadRecordRepository newsReadRecordRepository;

    @Override
    public Map<String, List<NewsDetailVO>> queryNews(String newsType, Integer maxCount,
                                                     UserInfo userInfo) {
        Page<NewsInfoDO> page = new Page<>(maxCount, 1L);
        List<NewsInfoDO> newsInfos = newsInfoMapper.queryNews(newsType, page);
        List<NewsDetailVO> detailVOList = new ArrayList<>();
        for (NewsInfoDO newsInfoDO : newsInfos) {
            NewsDetailVO newsDetailVO = new NewsDetailVO();
            this.setNewsInfoMapper(newsInfoDO);
            ConvertBeanUtil.copyBeanProperties(newsInfoDO, newsDetailVO);
            newsDetailVO.setCreateTime(
                new SimpleDateFormat("yyyy-MM-dd").format(newsInfoDO.getCreateTime()));
            NewsReadRecord newsReadRecord = newsReadRecordRepository.query(newsInfoDO.getId(),
                userInfo.getId());
            newsDetailVO.setUserId(userInfo.getId());
            if (Objects.nonNull(newsReadRecord)) {
                newsDetailVO.setRead(true);
            } else {
                newsDetailVO.setRead(false);
            }
            detailVOList.add(newsDetailVO);
        }
        Map<String, List<NewsDetailVO>> returnMap = new HashMap<>();
        returnMap.put("newsList", detailVOList);
        return returnMap;
    }

    private void setNewsInfoMapper(NewsInfoDO newsInfoDO) {
        if (NewsTag2.information.getCode().equals(newsInfoDO.getTag2())) {
            newsInfoDO.setTag2(NewsTag2.information.getMsg());
        } else if (NewsTag2.knowledge.getCode().equals(newsInfoDO.getTag2())) {
            newsInfoDO.setTag2(NewsTag2.knowledge.getMsg());
        }
        String tag1 = newsInfoDO.getTag1();
        if (!StringUtils.isEmpty(tag1)) {
            List<String> tag1List = new ArrayList<>();
            List<String> tag1Arr = Arrays.asList(tag1.split(","));
            if (tag1Arr.contains(NewsTag1.creditCard.getCode())) {
                tag1List.add(NewsTag1.creditCard.getMsg());
            }
            if (tag1Arr.contains(NewsTag1.financing.getCode())) {
                tag1List.add(NewsTag1.financing.getMsg());
            }
            if (tag1Arr.contains(NewsTag1.loan.getCode())) {
                tag1List.add(NewsTag1.loan.getMsg());
            }
            if (tag1Arr.contains(NewsTag1.insurance.getCode())) {
                tag1List.add(NewsTag1.insurance.getMsg());
            }
            newsInfoDO.setTag1(String.join(",", tag1List));
        }
    }
}
