package finance.api.model.vo;

/**
 * 图片验证码.
 * @author hewenbin
 * @version v1.0 2018年7月9日 下午5:41:34 hewenbin
 */
public class ImgValidateVo {
	
	private String imgCodeId;
	private String imgCodeBase64;
	
	public String getImgCodeId() {
		return imgCodeId;
	}
	public void setImgCodeId(String imgCodeId) {
		this.imgCodeId = imgCodeId;
	}
	public String getImgCodeBase64() {
		return imgCodeBase64;
	}
	public void setImgCodeBase64(String imgCodeBase64) {
		this.imgCodeBase64 = imgCodeBase64;
	}

	@Override
	public String toString() {
		return "ImgValidateVo{" +
				"imgCodeId='" + imgCodeId + '\'' +
				", imgCodeBase64='" + imgCodeBase64 + '\'' +
				'}';
	}
}
