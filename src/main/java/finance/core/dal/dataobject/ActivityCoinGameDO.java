package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: ActivityCoinGameDO.java, v0.1 2018/11/25 1:29 PM lili Exp $
 */
@Data
public class ActivityCoinGameDO implements Serializable {
    private static final long serialVersionUID = 6383627104980763312L;
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