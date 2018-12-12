package cn.zhishush.finance.domainservice.service.finance.tansAccount;

import cn.zhishush.finance.core.dal.dataobject.account.UserAccountDO;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: TransAccBiz.java, v0.1 2018/11/14 1:54 PM lili Exp $
 */
public interface TransAccBiz {

	/**
	 * 根据useid查询账户
	 * 
	 * @param userId
	 * @return
	 */
	UserAccountDO getAccountByUserId(Long userId);
}
