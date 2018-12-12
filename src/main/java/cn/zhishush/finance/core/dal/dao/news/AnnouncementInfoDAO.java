package cn.zhishush.finance.core.dal.dao.news;

import cn.zhishush.finance.core.dal.dao.base.BaseDAO;
import cn.zhishush.finance.core.dal.dataobject.news.AnnouncementInfoDO;

/**
 * <p>公告</p>
 * 
 * @author lili
 * @version $Id: AnnouncementInfoDAO.java, v0.1 2018/11/14 12:24 PM lili Exp $
 */
public interface AnnouncementInfoDAO extends BaseDAO<AnnouncementInfoDO, Long> {
	/**
	 * 功能描述:获取最新的公告
	 * 
	 * @return AnnouncementInfoDO
	 */
	AnnouncementInfoDO selectNewestAnnouncements();

}