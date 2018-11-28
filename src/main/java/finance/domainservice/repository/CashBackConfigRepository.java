package finance.domainservice.repository;

import finance.domain.cashbak.CashBackConfig;

/**
 * <p>返佣配置</p>
 *
 * @author lili
 * @version 1.0: CashBackConfigRepository.java, v0.1 2018/11/29 2:12 AM PM lili Exp $
 */
public interface CashBackConfigRepository {
    CashBackConfig query(Long configId);
}
