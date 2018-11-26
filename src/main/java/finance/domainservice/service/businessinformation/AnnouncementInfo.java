package finance.domainservice.service.businessinformation;

import java.util.List;
import java.util.Map;

import finance.api.model.vo.info.AnnouncementListVO;
/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: AnnouncementInfo.java, v0.1 2018/11/14 1:52 PM lili Exp $
 */
public interface AnnouncementInfo {
	Map<String, List<AnnouncementListVO>> getAnnouncementList();
}
