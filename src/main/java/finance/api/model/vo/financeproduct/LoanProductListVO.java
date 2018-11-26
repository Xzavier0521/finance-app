package finance.api.model.vo.financeproduct;

import java.math.BigDecimal;

import finance.core.common.enums.AmountType;

/**
 * @program: finance-app
 *
 * @description: 贷款产品列表VO
 *
 * @author: MORUIHAI
 *
 * @create: 2018-07-06 14:28
 **/
public class LoanProductListVO {
	private Long id;
	private String productName; // 产品名称
	private String logoUrl; // logo
	private String totalBonus; // 总奖金

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
		return "LoanProductListVO{" + "id=" + id + ", productName='" + productName + '\'' + ", logoUrl='" + logoUrl
				+ '\'' + ", totalBonus='" + totalBonus + '\'' + '}';
	}
}
