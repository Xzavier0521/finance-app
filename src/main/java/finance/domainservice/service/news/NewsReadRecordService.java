package finance.domainservice.service.news;

import finance.api.model.response.BasicResponse;
import finance.domain.user.UserInfo;

/**
 * <p>资讯阅读记录p>
 *
 * @author lili
 * @version 1.0: NewsReadRecordService.java, v0.1 2018/12/5 3:53 PM PM lili Exp $
 */
public interface NewsReadRecordService {

    BasicResponse localData(Long newsId, UserInfo userInfo);
}
