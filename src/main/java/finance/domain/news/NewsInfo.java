package finance.domain.news;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>资讯</p>
 *
 * @author lili
 * @version $Id: NewsInfoDO.java, v0.1 2018/11/25 4:45 PM lili Exp $
 */
@Data
public class NewsInfo implements Serializable {
    private static final long serialVersionUID = -7417862791188070628L;
    private Long              id;

    private String            title;

    private String            tag1;

    private String            tag2;

    private String            bannerUrl;

    private String            redirectUrl;
    /**
     * 奖励金币数
     */
    private Integer           rewardCoinNum;

    private Integer           state;

    private Date              createTime;

    private Date              updateTime;

    private String            creater;

    private String            updater;

    private Long              version;

    private Integer           isDelete;

}