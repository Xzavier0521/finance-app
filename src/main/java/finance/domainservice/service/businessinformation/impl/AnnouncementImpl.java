package finance.domainservice.service.businessinformation.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import finance.api.model.vo.AnnouncementListVO;
import finance.domainservice.service.businessinformation.AnnouncementInfo;
import finance.domainservice.service.jwt.JwtService;
import finance.core.dal.dao.FinanceAnnouncementInfoDAO;
import finance.core.dal.dao.FinanceUserInfoDAO;
import finance.core.dal.dao.FinanceUserInviteInfoDAO;
import finance.core.dal.dataobject.FinanceAnnouncementInfo;
import finance.core.dal.dataobject.FinanceUserInfo;
import finance.core.dal.dataobject.FinanceUserInviteInfo;

/**
 * <p>后台-公告</p>
 * @author lili
 * @version $Id: AnnouncementImpl.java, v0.1 2018/11/14 1:52 PM lili Exp $
 */
@Service
public class AnnouncementImpl implements AnnouncementInfo {
    @Resource
    private JwtService                 jwtService;
    @Resource
    private FinanceUserInviteInfoDAO   financeUserInviteInfoMapper;
    @Resource
    private FinanceUserInfoDAO         financeUserInfoMapper;
    @Resource
    private FinanceAnnouncementInfoDAO financeAnnouncementInfoMapper;

    /**
      *功能描述:查询公告
      * @author: moruihai
      * @date: 2018/8/16 13:53
      * @param: []
      * @return: java.util.Map<java.lang.String,java.util.List<finance.model.vo.AnnouncementListVO>>
      */

    @Override
    public Map<String, List<AnnouncementListVO>> getAnnouncementList() {

        //1.获取用户今日最新的一条推广
        Map<String, List<AnnouncementListVO>> returnMap = new HashMap<>();
        List<AnnouncementListVO> announcementList = new ArrayList<>();
        Long userId = jwtService.getUserInfo().getId();
        String searchDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        FinanceUserInviteInfo financeUserInviteInfo = financeUserInviteInfoMapper
            .selectByParentUserIdAndDate(userId, searchDate);
        AnnouncementListVO announcementListVO = new AnnouncementListVO();
        if (financeUserInviteInfo != null) {
            Long newestUserId = financeUserInviteInfo.getUserId();
            FinanceUserInfo financeUserInfo = financeUserInfoMapper
                .selectByPrimaryKey(newestUserId);
            String newestUserMobile = financeUserInfo.getMobileNum();
            String message = new StringBuffer().append("您推广的客户").append(newestUserMobile)
                .append("已完成注册!").toString();
            announcementListVO = new AnnouncementListVO();
            announcementListVO.setId(financeUserInfo.getId());
            announcementListVO.setAnnouncementType("message");
            announcementListVO.setAnnouncementTitle(message);
            announcementListVO.setAnnouncementContext(message);
            announcementList.add(announcementListVO);

        }

        //2. 取公告信息默认显示最新1条
        FinanceAnnouncementInfo financeAnnouncementInfo = financeAnnouncementInfoMapper
            .selectNewestAnnouncements();
        if (financeAnnouncementInfo != null) {
            announcementListVO = new AnnouncementListVO();
            announcementListVO.setId(financeAnnouncementInfo.getId());
            announcementListVO.setAnnouncementType("announcement");
            announcementListVO.setAnnouncementTitle(financeAnnouncementInfo.getAnnouncementTitle());
            announcementListVO
                .setAnnouncementContext(financeAnnouncementInfo.getAnnouncementContext());
            announcementList.add(announcementListVO);
        }
        returnMap.put("announcementList", announcementList);

        return returnMap;
    }
}
