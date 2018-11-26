package finance.domainservice.service.kameng.impl;

import finance.core.dal.dao.CreditCardDetailDAO;
import finance.core.dal.dao.IdCardInfoDAO;
import finance.core.dal.dao.UserApplyCreditCardDetailDAO;
import finance.core.dal.dataobject.CreditCardDetailDO;
import finance.core.dal.dataobject.IdCardInfoDO;
import finance.core.dal.dataobject.UserApplyCreditCardDetailDO;
import finance.domain.user.UserInfo;
import finance.domainservice.service.kameng.UserApplyCreditCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 申请办理信用卡-卡盟
 * </p >
 *
 * @author liwei
 * @version $Id: UserApplyCreditCardServiceImpl.java, v0.1 2018/11/20 上午10:35 PM
 *          user Exp $
 */
@Slf4j
@Service("userApplyCreditCardService")
public class UserApplyCreditCardServiceImpl implements UserApplyCreditCardService {
    @Resource
    private IdCardInfoDAO                idCardInfoDAO;
    @Resource
    private CreditCardDetailDAO          creditCardDetailDAO;
    @Resource
    private UserApplyCreditCardDetailDAO userApplyCreditCardDetailDAO;
    @Value("${kameng.channel.id}")
    private String                       kaMengChannelId;

    @Override
    public UserApplyCreditCardDetailDO selectUserRealNameInfo(UserInfo userInfo, String header,
                                                              String ip, Long productId) {
        IdCardInfoDO idCardInfo = idCardInfoDAO.selectByUserId(userInfo.getId());
        CreditCardDetailDO creditCardDetail = creditCardDetailDAO
            .selectProductDetailByProductId(productId);
        log.info("[用户申请办理信用卡请求头信息:{},实名信息:{},信用卡产品信息:{}]", header, idCardInfo, creditCardDetail);
        UserApplyCreditCardDetailDO userApplyCreditCardDetailDO = new UserApplyCreditCardDetailDO();
        if (null != idCardInfo && null != creditCardDetail) {
            userApplyCreditCardDetailDO.setUserName(idCardInfo.getRealName());
            userApplyCreditCardDetailDO.setIdNum(idCardInfo.getIdNum());
            userApplyCreditCardDetailDO.setChannelId(kaMengChannelId);
            userApplyCreditCardDetailDO.setProductId(creditCardDetail.getOtherChannelProductId());
            userApplyCreditCardDetailDO.setProductType((long) 1);
            userApplyCreditCardDetailDO.setIp(ip);
            userApplyCreditCardDetailDO.setSystemVersion(header);
            userApplyCreditCardDetailDO.setSoftVersion("WeChat");
        }
        userApplyCreditCardDetailDO.setUserId(userInfo.getId());
        userApplyCreditCardDetailDO.setMobileNum(userInfo.getMobileNum());
        log.info("[用户申请办理信用卡请求参数封装:{}]", userApplyCreditCardDetailDO);
        return userApplyCreditCardDetailDO;
    }

    @Override
    public int insertData(UserApplyCreditCardDetailDO userApplyCreditCardDetailDO) {
        int id = userApplyCreditCardDetailDAO.insertSelective(userApplyCreditCardDetailDO);
        return id;
    }
}
