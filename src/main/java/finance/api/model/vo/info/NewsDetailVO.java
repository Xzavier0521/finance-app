package finance.api.model.vo.info;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>news资讯VO</p>
 * @author lili
 * @version 1.0: NewsDetailVO.java, v0.1 2018/11/26 5:23 PM lili Exp $
 */
@Data
public class NewsDetailVO implements Serializable {
    private static final long serialVersionUID = 5513634366327024492L;
    /**
     * 标题
     */
    private String            title;
    /**
     * 标签
     */
    private String            tag1;
    /**
     * 标签
     */
    private String            tag2;
    /**
     * banner url
     */
    private String            bannerUrl;
    /**
     * 跳转URL
     */
    private String            redirectUrl;
    /**
     * 奖励金币数
     */
    private Integer           rewardCoinNum;
    /**
     * 创建时间
     */
    private String            createTime;

}
