package finance.mapper;

import finance.model.po.FinanceAnnouncementInfo;

/**
 * <p>公告</p>
 * @author lili
 * @version $Id: FinanceAnnouncementInfoDAO.java, v0.1 2018/11/14 12:24 PM lili Exp $
 */
public interface FinanceAnnouncementInfoDAO extends BaseDAO<FinanceAnnouncementInfo, Long> {
    /**
     * 功能描述:获取最新的公告
     * @return FinanceAnnouncementInfo
     */
    FinanceAnnouncementInfo selectNewestAnnouncements();

}