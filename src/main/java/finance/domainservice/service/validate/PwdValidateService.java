package finance.domainservice.service.validate;

import finance.core.common.enums.PwdType;

/**
 * 密码校验服务接口.
 * @author hewenbin
 * @version v1.0 2018年7月11日 上午11:33:08 hewenbin
 */
public interface PwdValidateService {

    /**
     * 生成加密的账户密码.
     * @param pwd 前端给后端的明文密码
     * @return 加密之后的密文
     * @author hewenbin
     * @version PwdValidateService.java, v1.0 2018年7月11日 上午11:38:33 hewenbin
     */
    String getPwd(PwdType pwdType, Long userId, String pwd);

    /**
     * 校验账户密码.
     * @param pwd 前端给后端的明文密码
     * @return
     * @author hewenbin
     * @version PwdValidateService.java, v1.0 2018年7月11日 上午11:40:42 hewenbin
     */
    Boolean validatePwd(PwdType pwdType, Long userId, String pwd);

}
