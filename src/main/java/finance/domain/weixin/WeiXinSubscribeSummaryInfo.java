package finance.domain.weixin;

import java.util.List;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: WeiXinSubscribeSummaryInfo.java, v0.1 2018/12/3 8:27 PM PM lili Exp $
 */
public class WeiXinSubscribeSummaryInfo {

    /**
     * 关注该公众账号的总用户数
     */
    private int          total;
    /**
     * 拉取的OPENID个数，最大值为10000
     */
    private int          count;
    /**
     * 拉取的OPENID个数，最大值为10000
     */
    private List<String> openIds;
    /**
     * 拉取列表的最后一个用户的OPENID
     */
    private String       nexOpenId;

}
