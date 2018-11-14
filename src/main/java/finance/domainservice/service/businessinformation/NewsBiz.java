package finance.domainservice.service.businessinformation;

import finance.api.model.vo.NewsDetailVO;

import java.util.List;
import java.util.Map;

/**
 * <p>资讯</p>
 * @author lili
 * @version $Id: NewsBiz.java, v0.1 2018/11/14 1:53 PM lili Exp $
 */
public interface NewsBiz {

    Map<String,List<NewsDetailVO>> queryNews(String newsType, Integer maxCount);
}
