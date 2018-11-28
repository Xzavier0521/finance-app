package finance.domainservice.repository;

import finance.api.model.base.Page;
import finance.domain.third.ThirdUnionLoginLog;

/**
 * <p>第三方联合登陆日志p>
 *
 * @author lili
 * @version 1.0: ThirdUnionLoginLogRepository.java, v0.1 2018/11/28 8:37 PM PM lili Exp $
 */
public interface ThirdUnionLoginLogRepository {

    int save(ThirdUnionLoginLog thirdUnionLoginLog);

    Page<ThirdUnionLoginLog> query(Long userId, int pageSize, int pageNum);

}
