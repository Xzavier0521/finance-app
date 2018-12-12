package cn.zhishush.finance.api.model.vo.financeproduct;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cn.zhishush.finance.core.common.enums.AmountType;

/**
 * @program: finance-app
 * @description: 理财产品主页列表VO
 * @author: MORUIHAI
 * @create: 2018-07-06 09:33
 **/
public class FinancingProductListVO {
	private Long id;
	private String productName; // 产品名称
	private String logoUrl; // logo
	private List mark = new ArrayList(); // 标签

	private String aveRevenue; // 平均收益
	private String productBackground; // 背景
	private String totalBonus; // 总返现

	public List getMark() {
		return mark;
	}

	public void setMark(List mark) {
		this.mark = mark;
	}

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

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getAveRevenue() {
		return aveRevenue;
	}

	public void setAveRevenue(String aveRevenue) {
		this.aveRevenue = aveRevenue;
	}

	public String getProductBackground() {
		return productBackground;
	}

	public void setProductBackground(String productBackground) {
		this.productBackground = productBackground;
	}

	public String getTotalBonus() {
		return totalBonus;
	}

	public void setTotalBonus(BigDecimal totalBonus, Integer amountType) {
		if (AmountType.amount.getCode().equals(amountType)) {
			this.totalBonus = totalBonus == null ? "" : totalBonus.stripTrailingZeros().toPlainString();
		} else if (AmountType.percenta.getCode().equals(amountType)) {
			this.totalBonus = totalBonus.multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString() + "%";
		}
	}

	@Override
	public String toString() {
		return "FinancingProductListVO{" + "id=" + id + ", productName='" + productName + '\'' + ", logoUrl='" + logoUrl
				+ '\'' + ", mark=" + mark + ", aveRevenue='" + aveRevenue + '\'' + ", productBackground='"
				+ productBackground + '\'' + ", totalBonus='" + totalBonus + '\'' + '}';
	}
}
