package finance.domainservice.service.businessinformation.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import finance.api.model.base.Page;
import finance.api.model.vo.NewsDetailVO;
import finance.core.common.enums.NewsTag1;
import finance.core.common.enums.NewsTag2;
import finance.domainservice.service.businessinformation.NewsBiz;
import finance.mapper.FinanceNewsInfoDAO;
import finance.model.po.FinanceNewsInfo;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: NewsBizImpl.java, v0.1 2018/11/14 1:52 PM lili Exp $
 */
@Service
public class NewsBizImpl implements NewsBiz {

    @Resource
    private FinanceNewsInfoDAO newsInfoMapper;

    @Override
    public Map<String, List<NewsDetailVO>> queryNews(String newsType, Integer maxCount) {
        Page<FinanceNewsInfo> page = new Page<>(maxCount, 1L);
        List<FinanceNewsInfo> newsInfos = newsInfoMapper.queryNews(newsType, page);
        List<NewsDetailVO> detailVOList = new ArrayList<>();
        for (FinanceNewsInfo financeNewsInfo : newsInfos) {
            NewsDetailVO newsDetailVO = new NewsDetailVO();
            this.setNewsInfoMapper(financeNewsInfo);
            BeanUtils.copyProperties(financeNewsInfo, newsDetailVO);
            newsDetailVO.setCreateTime(
                new SimpleDateFormat("yyyy-MM-dd").format(financeNewsInfo.getCreateTime()));
            detailVOList.add(newsDetailVO);
        }
        Map<String, List<NewsDetailVO>> returnMap = new HashMap<>();
        returnMap.put("newsList", detailVOList);
        return returnMap;
    }

    private void setNewsInfoMapper(FinanceNewsInfo financeNewsInfo) {
        if (NewsTag2.information.getCode().equals(financeNewsInfo.getTag2())) {
            financeNewsInfo.setTag2(NewsTag2.information.getMsg());
        } else if (NewsTag2.knowledge.getCode().equals(financeNewsInfo.getTag2())) {
            financeNewsInfo.setTag2(NewsTag2.knowledge.getMsg());
        }
        String tag1 = financeNewsInfo.getTag1();
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
            financeNewsInfo.setTag1(String.join(",", tag1List));
        }
    }
}
