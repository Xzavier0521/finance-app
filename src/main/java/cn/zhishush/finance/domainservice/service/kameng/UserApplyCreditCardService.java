package cn.zhishush.finance.domainservice.service.kameng;

import cn.zhishush.finance.core.dal.dataobject.user.UserApplyCreditCardDetailDO;
import cn.zhishush.finance.domain.user.UserInfo;

/**
 * <p>
 * 注释
 * </p >
 *
 * @author liwei
 * @version $Id: UserApplyCreditCardService.java, v0.1 2018/11/20 上午9:47 PM user
 *          Exp $
 */
public interface UserApplyCreditCardService {
	/**
	 * 获取用户实名信息
	 * 
	 * @param userInfo
	 * @param header
	 * @param ip
	 * @param productId
	 * @return
	 */
	UserApplyCreditCardDetailDO selectUserRealNameInfo(UserInfo userInfo, String header, String ip, Long productId);

	int insertData(UserApplyCreditCardDetailDO userApplyCreditCardDetailDO);
}
