package cn.zhishush.finance.ext.integration.kameng;

import cn.zhishush.finance.core.dal.dataobject.user.UserApplyCreditCardDetailDO;
import cn.zhishush.finance.ext.api.response.KaMengUserApplyCreditCardResponse;

/**
 * <p>
 * 注释
 * </p >
 *
 * @author liwei
 * @version $Id: UserApplyCreditCardDetailClient.java, v0.1 2018/11/19 下午6:27 PM
 *          user Exp $
 */
public interface UserApplyCreditCardDetailClient {

	KaMengUserApplyCreditCardResponse applyCreditCard(UserApplyCreditCardDetailDO userApplyCreditCardDetailDO);
}
