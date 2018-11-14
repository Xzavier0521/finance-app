package finance.domainservice.service.validate.impl;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import finance.core.common.enums.PwdType;
import finance.domainservice.service.validate.PwdValidateService;
import finance.mapper.FinanceUserPwdInfoDAO;
import finance.model.po.FinanceUserPwdInfo;

/**
 * @author hewenbin
 * @version v1.0 2018年7月11日 上午11:42:29 hewenbin
 */
@Service
public class PwdValidateServiceImpl implements PwdValidateService {
    @Resource
    private FinanceUserPwdInfoDAO userPwdMapper;

    @Override
    public String getPwd(PwdType pwdType, Long userId, String pwd) {
        String realPwd = this.getPwd(userId, pwd, pwdType.name());
        return realPwd;
    }

    @Override
    public Boolean validatePwd(PwdType pwdType, Long userId, String pwd) {
        String sourcePwd = this.getPwd(pwdType, userId, pwd);
        FinanceUserPwdInfo pwdInfo = userPwdMapper.selectByUserId(userId, PwdType.withdraw.name());
        if (pwdInfo != null && pwdInfo.getPwd().equals(sourcePwd)) {
            return true;
        }
        return false;
    }

    /**
     * 生成密码.
     * <pre> 切记：上线之后不允许修改 </pre>
     * @param userId
     * @param pwd 
     * @param pwdType 密码类型（withdraw、login）
     * @return
     * @author hewenbin
     * @version PwdValidateServiceImpl.java, v1.0 2018年7月11日 下午2:38:55 hewenbin
     */
    private String getPwd(Long userId, String pwd, String pwdType) {
        String salt = userId + pwdType + userId % 1000;
        String realPwd = DigestUtils.md5Hex(salt + pwd);
        return realPwd;
    }

}
