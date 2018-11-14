package finance.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import finance.model.po.FinanceUserFirstLoginLog;
/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceUserFirstLoginLogDAO.java, v0.1 2018/11/14 1:00 PM lili Exp $
 */
@CacheConfig(cacheNames = "firstLoginLog")
public interface FinanceUserFirstLoginLogDAO extends BaseDAO<FinanceUserFirstLoginLog, Long> {
    
    /**
     * 查询首次登录信息.
     * @param userId
     * @param platformCode
     * @return
     * @author hewenbin
     * @version FinanceUserFirstLoginLogDAO.java, v1.0 2018年7月9日 下午9:03:56 hewenbin
     */
	@Cacheable(key="'firstLoginLog_'.concat(#a0).concat(#a1)", unless= "#result == null")
    FinanceUserFirstLoginLog selectByUserId(@Param("userId")Long userId,  @Param("platformCode")String platformCode);
}