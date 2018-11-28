package finance.core.dal.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * <p>注释</p>
 * 
 * @author lili
 * @version $Id: LeaderBoardDO.java, v0.1 2018/10/19 8:50 PM lili Exp $
 */
@Data
public class LeaderBoardDO implements Serializable {

    private static final long serialVersionUID = 9143007222695875748L;
    /**
     * 排行
     */
    private Long              ranking;

    /**
     * 排行榜类型
     */
    private String            leaderBoardType;

    /**
     * 用户id
     */
    private Long              userId;

    /**
     * 手机号码
     */
    private String            mobilePhone;

    /**
     * 邀请人数
     */
    private Long              inviteNumber;

    /**
     * 用户真实姓名
     */
    private String            realName;

    /**
     * 奖励金额
     */
    private BigDecimal        rewardAmount;
}
