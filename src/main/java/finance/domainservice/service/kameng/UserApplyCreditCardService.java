package finance.domainservice.service.kameng;

import finance.core.dal.dataobject.UserApplyCreditCardDetailDO;
import finance.domain.user.UserInfo;
import nl.bitwalker.useragentutils.UserAgent;

/**
 * <p>注释</p >
 *
 * @author liwei
 * @version $Id: UserApplyCreditCardService.java, v0.1 2018/11/20 上午9:47 PM user Exp $
 */
public interface UserApplyCreditCardService {
    /**
     * 获取用户实名信息
     *
     * @param userId
     * @return
     */
    UserApplyCreditCardDetailDO selectUserRealNameInfo(UserInfo userInfo, UserAgent userAgent,
                                                       String ip, Long productId);

    int insertData(UserApplyCreditCardDetailDO userApplyCreditCardDetailDO);
}
