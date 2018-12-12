package cn.zhishush.finance.api.model.vo.gift;
/**
 * @program: finance-server
 *
 * @description: 兑换商品
 *
 * @author: MORUIHAI
 *
 * @create: 2018-08-15 15:34
 **/
public class ExchangeGoodsVO {

	private String goodsName;
	private Long goodsId;
	private String bannerUrl;
	private Integer needCoinCount;

	private String thumbnailUrl;

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getBannerUrl() {
		return bannerUrl;
	}

	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}

	public Integer getNeedCoinCount() {
		return needCoinCount;
	}

	public void setNeedCoinCount(Integer needCoinCount) {
		this.needCoinCount = needCoinCount;
	}

	@Override
	public String toString() {
		return "ExchangeGoodsVO{" + "goodsName='" + goodsName + '\'' + ", goodsId=" + goodsId + ", bannerUrl='"
				+ bannerUrl + '\'' + ", needCoinCount=" + needCoinCount + ", thumbnailUrl='" + thumbnailUrl + '\''
				+ '}';
	}
}
