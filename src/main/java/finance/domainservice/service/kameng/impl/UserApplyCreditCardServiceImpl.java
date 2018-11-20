package finance.domainservice.service.kameng.impl;

import finance.core.dal.dao.FinanceCreditCardDetailDAO;
import finance.core.dal.dao.FinanceIdCardInfoDAO;
import finance.core.dal.dao.UserApplyCreditCardDetailDAO;
import finance.core.dal.dataobject.FinanceCreditCardDetail;
import finance.core.dal.dataobject.FinanceIdCardInfo;
import finance.core.dal.dataobject.UserApplyCreditCardDetailDO;
import finance.domain.user.UserInfo;
import finance.domainservice.service.kameng.UserApplyCreditCardService;
import lombok.extern.slf4j.Slf4j;
//import nl.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>申请办理信用卡-卡盟</p >
 *
 * @author liwei
 * @version $Id: UserApplyCreditCardServiceImpl.java, v0.1 2018/11/20 上午10:35 PM user Exp $
 */
@Slf4j
@Service("userApplyCreditCardService")
public class UserApplyCreditCardServiceImpl implements UserApplyCreditCardService {
    @Resource
    private FinanceIdCardInfoDAO         idCardInfoDAO;
    @Resource
    private FinanceCreditCardDetailDAO   creditCardDetailDAO;
    @Resource
    private UserApplyCreditCardDetailDAO userApplyCreditCardDetailDAO;
    @Value("${kameng.channel.id}")
    private String                       kaMengChannelId;

    @Override
    public UserApplyCreditCardDetailDO selectUserRealNameInfo(UserInfo userInfo,
                                                              UserAgent userAgent, String ip,
                                                              Long productId) {
        FinanceIdCardInfo idCardInfo = idCardInfoDAO.selectByUserId(userInfo.getId());
        FinanceCreditCardDetail creditCardDetail = creditCardDetailDAO
            .selectProductDetailByProductId(productId);
        log.info("[用户申请办理信用卡请求头信息:{},实名信息:{},信用卡产品信息:{}]", userAgent, idCardInfo, creditCardDetail);
        UserApplyCreditCardDetailDO userApplyCreditCardDetailDO = new UserApplyCreditCardDetailDO();
        if (null != idCardInfo && null != creditCardDetail) {
            userApplyCreditCardDetailDO.setUserName(idCardInfo.getRealName());
            userApplyCreditCardDetailDO.setIdNum(idCardInfo.getIdNum());
            userApplyCreditCardDetailDO.setChannelId(kaMengChannelId);
            userApplyCreditCardDetailDO.setProductId(creditCardDetail.getProductId());
            userApplyCreditCardDetailDO.setProductType((long) 1);
            userApplyCreditCardDetailDO.setIp(ip);
            userApplyCreditCardDetailDO.setSystemVersion(userAgent.getOperatingSystem().getName());
            userApplyCreditCardDetailDO.setSoftVersion(userAgent.getBrowser().getName());
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
