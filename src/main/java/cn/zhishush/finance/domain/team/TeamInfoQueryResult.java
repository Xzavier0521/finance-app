package cn.zhishush.finance.domain.team;

import java.util.List;

import lombok.Data;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: TeamInfoQueryResult.java, v0.1 2018/10/5 8:08 PM lili Exp $
 */
@Data
public class TeamInfoQueryResult {

	/**
	 * 所有用户个数
	 */
	private int allLevelCount;
	/**
	 * 一级用户个数
	 */
	private int firstLevelCount;
	/**
	 * 二级用户个数
	 */
	private int secondLevelCount;

	/**
	 * 所有用户列表
	 */
	private List<FirstLevelTeamUserInfo> allLevelUserList;

	/**
	 * 一级用户列表
	 */
	private List<SecondLevelTeamUserInfo> firstLevelUserList;

	/**
	 * 二级用户列表
	 */
	private List<SecondLevelTeamUserInfo> secondLevelUserList;
}
