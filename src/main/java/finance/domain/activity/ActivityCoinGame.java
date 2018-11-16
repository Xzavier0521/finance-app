package finance.domain.activity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>活动-金币游戏</p>
 *
 * @author lili
 * @version 1.0: ActivityCoinGame.java, v0.1 2018/11/15 11:31 AM PM lili Exp $
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityCoinGame implements Serializable {

    private static final long serialVersionUID = -5737318953516631844L;
    /**
     * 主键
     */
    private Long              id;

    /**
     *  活动代码
     */
    private String            activityCode;

    /**
     * 用户id
     */
    private Long              userId;

    /**
     * 游戏代码
     */
    private String            gameCode;

    /**
     * 游戏名称
     */
    private String            gameName;

    /**
     * 支付的金币数目
     */
    private Long              coinNum;

    /**
     * 是否删除 0-否 1-是
     */
    private Integer           isDelete;

    /**
     * 创建时间
     */
    private Date              createTime;

    /**
     * 更新时间
     */
    private Date              updateTime;

    /**
     * 创建者
     */
    private String            creator;

    /**
     * 更新者
     */
    private String            updator;

    /**
     * 版本号
     */
    private Integer           version;
}
