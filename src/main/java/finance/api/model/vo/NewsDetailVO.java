package finance.api.model.vo;

/**
 * @program: finance-server
 *
 * @description: news资讯VO
 *
 * @author: MORUIHAI
 *
 * @create: 2018-08-15 15:03
 **/
public class NewsDetailVO {
    private String title;
    private String tag1;
    private String tag2;
    private String bannerUrl;
    private String redirectUrl;
    private String createTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "NewsDetailVO{" +
                "title='" + title + '\'' +
                ", tag1='" + tag1 + '\'' +
                ", tag2='" + tag2 + '\'' +
                ", bannerUrl='" + bannerUrl + '\'' +
                ", redirectUrl='" + redirectUrl + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
