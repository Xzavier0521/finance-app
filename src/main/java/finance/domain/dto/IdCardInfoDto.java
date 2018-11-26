package finance.domain.dto;

import org.springframework.util.StringUtils;

/**
 * 身份证信息接口参数接收类.
 *
 * @author panzhongkang
 * @date 2018/8/21 14:14
 */
public class IdCardInfoDto {
	private Long userId;
	private String realName;
	private String idNum;
	private String authStatus;

	public Boolean validateParam() {
		if (StringUtils.isEmpty(realName) || StringUtils.isEmpty(idNum) || idNum.length() != 18
				|| !this.validate(idNum)) {
			return false;
		}
		return true;
	}

	/**
	 * 校验18位身份证号
	 *
	 * @param idNum
	 * @return boolean
	 * @author panzhongkang
	 * @date 2018/8/21 14:35
	 */
	private boolean validate(String idNum) {
		// 对身份证号进行长度等简单判断
		if (idNum == null || idNum.length() != 18 || !idNum.matches("\\d{17}[0-9X]")) {
			return false;
		}
		// 1-17位相乘因子数组
		int[] factor = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
		// 18位随机码数组
		char[] random = "10X98765432".toCharArray();
		// 计算1-17位与相应因子乘积之和
		int total = 0;
		for (int i = 0; i < 17; i++) {
			total += Character.getNumericValue(idNum.charAt(i)) * factor[i];
		}
		// 判断随机码是否相等
		return random[total % 11] == idNum.charAt(17);
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}

	@Override
	public String toString() {
		return "IdCardInfoDto{" + "userId=" + userId + ", realName='" + realName + '\'' + ", idNum='" + idNum + '\''
				+ ", authStatus='" + authStatus + '\'' + '}';
	}
}
