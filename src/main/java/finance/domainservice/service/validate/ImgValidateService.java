package finance.domainservice.service.validate;

import finance.api.model.vo.ImgValidateVo;

/**
 * 图片验证服务接口.
 * @author hewenbin
 * @version v1.0 2018年7月9日 下午5:35:06 hewenbin
 */
public interface ImgValidateService {

	/**
	 * 获取图片验证码.
	 * @return
	 * @author hewenbin
	 * @version ImgValidateService.java, v1.0 2018年7月9日 下午5:50:09 hewenbin
	 */
	ImgValidateVo getImgValidate();
	
	/**
	 * 校验图片验证码.
	 * @param imgCodeId not null
	 * @param imgCode not null
	 * @return
	 * @author hewenbin
	 * @version ImgValidateService.java, v1.0 2018年7月9日 下午7:38:35 hewenbin
	 */
	Boolean vidateImgCode(String imgCodeId, String imgCode);
	
}
