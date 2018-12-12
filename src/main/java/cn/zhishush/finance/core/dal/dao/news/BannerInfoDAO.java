package cn.zhishush.finance.core.dal.dao.news;

import java.util.List;

import cn.zhishush.finance.core.dal.dao.base.BaseDAO;
import org.apache.ibatis.annotations.Param;

import cn.zhishush.finance.core.dal.dataobject.news.BannerInfoDO;

/**
 * <p>注释</p>
 * 
 * @author lili
 * @version :1.0 BannerInfoDAO.java.java, v 0.1 2018/9/29 上午10:13 lili Exp $
 */
public interface BannerInfoDAO extends BaseDAO<BannerInfoDO, Long> {

    /**
     * 查询banner 类型
     * @param pageCode pageCode
     * @param bannerType  banner 类型
     * @return List<BannerInfoDO>
     */
    List<BannerInfoDO> queryBannerByCodeAndType(@Param("pageCode") Integer pageCode,
                                                @Param("bannerType") Integer bannerType);

}