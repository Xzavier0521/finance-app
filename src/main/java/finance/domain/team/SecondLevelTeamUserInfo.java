package finance.domain.team;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 二级用户
 * </p>
 * 
 * @author lili
 * @version $Id: SecondLevelTeamUserInfo.java, v0.1 2018/10/5 8:07 PM lili Exp $
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SecondLevelTeamUserInfo {

	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 邀请人id
	 */
	private Long parentUserId;
	/**
	 * 手机号码
	 */
	private String mobileNum;
	/**
	 * 用户姓名
	 */
	private String name;
	/**
	 * 注册时间
	 */
	private String registerDate;
}
