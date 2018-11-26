package finance.core.dal.dao;

import java.util.List;

import finance.core.dal.dataobject.BannerInfoDO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version :1.0 BannerInfoDAO.java.java, v 0.1 2018/9/29 上午10:13 lili Exp $
 */
public interface BannerInfoDAO extends BaseDAO<BannerInfoDO, Long> {

	/**
	 * 查询banner 类型
	 * 
	 * @param pageCode
	 *            pageCode
	 * @param bannerType
	 *            banner 类型
	 * @return List<BannerInfoDO>
	 */
	List<BannerInfoDO> queryBannerByCodeAndType(@Param("pageCode") Integer pageCode,
			@Param("bannerType") Integer bannerType);

}