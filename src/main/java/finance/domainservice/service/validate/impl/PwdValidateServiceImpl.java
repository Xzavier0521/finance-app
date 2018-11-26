package finance.domainservice.service.validate.impl;

import javax.annotation.Resource;

import finance.core.dal.dao.UserPwdInfoDAO;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import finance.core.common.enums.PwdType;
import finance.core.dal.dataobject.UserPwdInfoDO;
import finance.domainservice.service.validate.PwdValidateService;

/**
 * @author hewenbin
 * @version v1.0 2018年7月11日 上午11:42:29 hewenbin
 */
@Service("pwdValidateService")
public class PwdValidateServiceImpl implements PwdValidateService {
	@Resource
	private UserPwdInfoDAO userPwdInfoDAO;

	@Override
	public String getPwd(PwdType pwdType, Long userId, String pwd) {
		return this.getPwd(userId, pwd, pwdType.name());
	}

	@Override
	public Boolean validatePwd(PwdType pwdType, Long userId, String pwd) {
		String sourcePwd = this.getPwd(pwdType, userId, pwd);
		UserPwdInfoDO pwdInfo = userPwdInfoDAO.selectByUserId(userId, PwdType.withdraw.name());
		return pwdInfo != null && pwdInfo.getPwd().equals(sourcePwd);
	}

	/**
	 * 生成密码.
	 * 
	 * <pre>
	 *  切记：上线之后不允许修改
	 * </pre>
	 * 
	 * @param userId
	 * @param pwd
	 * @param pwdType
	 *            密码类型（withdraw、login）
	 * @return
	 * @author hewenbin
	 * @version PwdValidateServiceImpl.java, v1.0 2018年7月11日 下午2:38:55 hewenbin
	 */
	private String getPwd(Long userId, String pwd, String pwdType) {
		String salt = userId + pwdType + userId % 1000;
		return DigestUtils.md5Hex(salt + pwd);
	}

}
