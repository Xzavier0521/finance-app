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
    private String            title;
    private String            tag1;
    private String            tag2;
    private String            bannerUrl;
    private String            redirectUrl;
    private String            createTime;

}
