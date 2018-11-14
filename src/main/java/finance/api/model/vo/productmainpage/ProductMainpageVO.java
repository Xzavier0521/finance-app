package finance.api.model.vo.productmainpage;

/**
 * @program: finance-app
 *
 * @description: 金榕家集合页和注册宝主页VO
 *
 * @author: MORUIHAI
 *
 * @create: 2018-07-06 15:54
 **/
public class ProductMainpageVO {
    private Long id;

    private String productName;

    private String redirectUrl;

    private String productDes;

    private String maxAmount;

    private String feeRate;

    private String logoUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getProductDes() {
        return productDes;
    }

    public void setProductDes(String productDes) {
        this.productDes = productDes;
    }

    public String getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(String maxAmount) {
        this.maxAmount = maxAmount;
    }

    public String getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(String feeRate) {
        this.feeRate = feeRate;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    @Override
    public String toString() {
        return "ProductMainpageVO{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", redirectUrl='" + redirectUrl + '\'' +
                ", productDes='" + productDes + '\'' +
                ", maxAmount='" + maxAmount + '\'' +
                ", feeRate='" + feeRate + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                '}';
    }
}
