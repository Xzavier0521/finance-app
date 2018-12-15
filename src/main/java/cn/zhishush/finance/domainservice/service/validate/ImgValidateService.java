package cn.zhishush.finance.domainservice.service.validate;

import cn.zhishush.finance.api.model.vo.login.ImgValidateVo;

/**
 * 图片验证服务接口.
 *
 * @author hewenbin
 * @version v1.0 2018年7月9日 下午5:35:06 hewenbin
 */
public interface ImgValidateService {

    /**
     * 获取图片验证码.
     *
     * @return ImgValidateVo
     */
    ImgValidateVo getImgValidate();

    /**
     * 校验图片验证码.
     *
     * @param imgCodeId 验证码id
     * @param imgCode   验证码
     * @return Boolean
     */
    Boolean validateImgCode(String imgCodeId, String imgCode);

}
