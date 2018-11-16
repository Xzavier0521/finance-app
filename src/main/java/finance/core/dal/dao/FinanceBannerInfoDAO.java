package finance.core.dal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import finance.core.dal.dataobject.FinanceBannerInfo;

/**
 *  <p>注释</p>
 * @author  lili
 * @version :1.0  FinanceBannerInfoDAO.java.java, v 0.1 2018/9/29 上午10:13 lili Exp $
 */
public interface FinanceBannerInfoDAO extends BaseDAO<FinanceBannerInfo, Long> {

    /**
     *  查询banner 类型
     * @param pageCode pageCode
     * @param bannerType banner 类型
     * @return List<FinanceBannerInfo>
     */
    List<FinanceBannerInfo> queryBannerByCodeAndType(@Param("pageCode") Integer pageCode,
                                                     @Param("bannerType") Integer bannerType);

}