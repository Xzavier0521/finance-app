package finance.domain.news;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>资讯p>
 * @author lili
 * @version 1.0: NewsReadRecord.java, v0.1 2018/12/5 3:26 PM lili Exp $
 */
@Data
public class NewsReadRecord implements Serializable {
    private static final long serialVersionUID = -268197988490682662L;
    /**
     * 主键
     */
    private Long              id;

    /**
     * 用户id
     */
    private Long              userId;

    /**
     * 资讯文章id
     */
    private Long              newsId;

    /**
     * 奖励金币数
     */
    private Integer           rewardCoinNum;

    /**
     * 创建时间
     */
    private Date              createDate;

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
     * 是否删除 0-否 1-是
     */
    private String            isDelete;

    /**
     * 版本号
     */
    private Long              version;

}