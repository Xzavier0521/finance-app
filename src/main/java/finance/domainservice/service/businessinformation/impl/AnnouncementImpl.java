package finance.domainservice.service.businessinformation.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import finance.core.dal.dao.UserInfoDAO;
import finance.core.dal.dao.UserInviteInfoDAO;
import finance.core.dal.dataobject.AnnouncementInfoDO;
import org.springframework.stereotype.Service;

import finance.api.model.vo.info.AnnouncementListVO;
import finance.domainservice.service.businessinformation.AnnouncementInfo;
import finance.domainservice.service.jwt.JwtService;
import finance.core.dal.dao.AnnouncementInfoDAO;
import finance.core.dal.dataobject.UserInfoDO;
import finance.core.dal.dataobject.UserInviteInfoDO;

/**
 * <p>
 * 后台-公告
 * </p>
 * 
 * @author lili
 * @version $Id: AnnouncementImpl.java, v0.1 2018/11/14 1:52 PM lili Exp $
 */
@Service
public class AnnouncementImpl implements AnnouncementInfo {
	@Resource
	private JwtService jwtService;
	@Resource
	private UserInviteInfoDAO financeUserInviteInfoMapper;
	@Resource
	private UserInfoDAO financeUserInfoMapper;
	@Resource
	private AnnouncementInfoDAO financeAnnouncementInfoMapper;

	/**
	 * 功能描述:查询公告
	 * 
	 * @author: moruihai
	 * @date: 2018/8/16 13:53
	 * @param: []
	 * @return: java.util.Map<java.lang.String,java.util.List<finance.model.vo.AnnouncementListVO>>
	 */

	@Override
	public Map<String, List<AnnouncementListVO>> getAnnouncementList() {

		// 1.获取用户今日最新的一条推广
		Map<String, List<AnnouncementListVO>> returnMap = new HashMap<>();
		List<AnnouncementListVO> announcementList = new ArrayList<>();
		Long userId = jwtService.getUserInfo().getId();
		String searchDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		UserInviteInfoDO financeUserInviteInfo = financeUserInviteInfoMapper.selectByParentUserIdAndDate(userId,
				searchDate);
		AnnouncementListVO announcementListVO = new AnnouncementListVO();
		if (financeUserInviteInfo != null) {
			Long newestUserId = financeUserInviteInfo.getUserId();
			UserInfoDO financeUserInfo = financeUserInfoMapper.selectByPrimaryKey(newestUserId);
			String newestUserMobile = financeUserInfo.getMobileNum();
			String message = new StringBuffer().append("您推广的客户").append(newestUserMobile).append("已完成注册!").toString();
			announcementListVO = new AnnouncementListVO();
			announcementListVO.setId(financeUserInfo.getId());
			announcementListVO.setAnnouncementType("message");
			announcementListVO.setAnnouncementTitle(message);
			announcementListVO.setAnnouncementContext(message);
			announcementList.add(announcementListVO);

		}

		// 2. 取公告信息默认显示最新1条
		AnnouncementInfoDO announcementInfoDO = financeAnnouncementInfoMapper.selectNewestAnnouncements();
		if (announcementInfoDO != null) {
			announcementListVO = new AnnouncementListVO();
			announcementListVO.setId(announcementInfoDO.getId());
			announcementListVO.setAnnouncementType("announcement");
			announcementListVO.setAnnouncementTitle(announcementInfoDO.getAnnouncementTitle());
			announcementListVO.setAnnouncementContext(announcementInfoDO.getAnnouncementContext());
			announcementList.add(announcementListVO);
		}
		returnMap.put("announcementList", announcementList);

		return returnMap;
	}
}
