package cn.zhishush.finance.domainservice.service.businessinformation;

import java.util.List;
import java.util.Map;

import cn.zhishush.finance.api.model.vo.info.NewsDetailVO;
import cn.zhishush.finance.domain.user.UserInfo;

/**
 * <p>资讯</p>
 * 
 * @author lili
 * @version $Id: NewsBiz.java, v0.1 2018/11/14 1:53 PM lili Exp $
 */
public interface NewsBiz {

    Map<String, List<NewsDetailVO>> queryNews(String newsType, Integer maxCount, UserInfo userInfo);
}
