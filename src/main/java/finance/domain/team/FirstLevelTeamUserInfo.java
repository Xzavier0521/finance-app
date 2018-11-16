package finance.domain.team;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>一级用户</p>
 * @author lili
 * @version $Id: FirstLevelTeamUserInfo.java, v0.1 2018/10/6 12:40 AM lili Exp $
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FirstLevelTeamUserInfo {

    /**
     * 用户id
     */
    private Long                     userId;

    /**
     * 邀请人id
     */
    private Long                     parentUserId;
    /**
     * 手机号码
     */
    private String                   mobileNum;
    /**
     * 用户姓名
     */
    private String                   name;
    /**
     * 注册时间
     */
    private String                   registerDate;

    /**
     * 二级用户
     */
    private List<SecondLevelTeamUserInfo> inviteList;
}
