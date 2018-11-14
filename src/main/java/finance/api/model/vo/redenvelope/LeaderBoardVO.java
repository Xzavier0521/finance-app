package finance.api.model.vo.redenvelope;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import finance.core.common.enums.LeaderBoardTypeEnum;
import lombok.NoArgsConstructor;

/**
 * <p>排行榜</p>
 * @author lili
 * @version $Id: LeaderBoardVO.java, v0.1 2018/10/19 5:18 PM lili Exp $
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeaderBoardVO implements Serializable {

    private static final long   serialVersionUID = 8027016448801408000L;
    /**
     * 排行
     */
    private String              ranking;

    /**
     * 排行榜类型
     */
    private LeaderBoardTypeEnum leaderBoardType;

    /**
     *  用户id
     */
    private Long                userId;

    /**
     * 手机号码
     */
    private String              mobilePhone;

    /**
     * 邀请人数
     */
    private Long                inviteNumber;

    /**
     * 用户真实姓名
     */
    private String              realName;

    /**
     * 奖励金额
     */
    private String              rewardAmount;

}
