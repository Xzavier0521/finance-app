package finance.domainservice.service.finance.tansAccount.impl;

import javax.annotation.Resource;

import finance.core.dal.dao.UserAccountDAO;
import finance.core.dal.dataobject.UserAccountDO;
import org.springframework.stereotype.Component;

import finance.domainservice.service.finance.tansAccount.TransAccBiz;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: TransAccBizImpl.java, v0.1 2018/11/14 1:54 PM lili Exp $
 */
@Component
public class TransAccBizImpl implements TransAccBiz {

	// private static final Logger logger =
	// LoggerFactory.getLogger(TransAccBizImpl.class);

	@Resource
	private UserAccountDAO accountMapper;

	@Override
	public UserAccountDO getAccountByUserId(Long userId) {
		return accountMapper.getAccountByUserId(userId);
	}

}
