package cn.zhishush.finance.api.model.vo.financeproduct;

import java.math.BigDecimal;

import cn.zhishush.finance.core.common.enums.AmountType;

/**
 * @program: finance-app
 * @description: 信用卡产品列表集合
 * @author: MORUIHAI
 * @create: 2018-07-06 13:51
 **/
public class CreditCardProductListVO {
	private Long id;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * logo
	 */
	private String logoUrl;
	/**
	 * 总奖金
	 */
	private String totalBonus;
	/**
	 * 通过率
	 */
	private String passRate;
	/**
	 * 返现说明
	 */
	private String rebackCashDesc;
	/**
	 * 直接推广奖金
	 */
	private BigDecimal directBonus;
	/**
	 * 间接推广奖金
	 */
	private BigDecimal indirectBonus;

	public BigDecimal getDirectBonus() {
		return directBonus;
	}

	public void setDirectBonus(BigDecimal directBonus) {
		this.directBonus = directBonus;
	}

	public BigDecimal getIndirectBonus() {
		return indirectBonus;
	}

	public void setIndirectBonus(BigDecimal indirectBonus) {
		this.indirectBonus = indirectBonus;
	}

	public String getRebackCashDesc() {
		return rebackCashDesc;
	}

	public void setRebackCashDesc(String rebackCashDesc) {
		this.rebackCashDesc = rebackCashDesc;
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

	public String getPassRate() {
		return passRate;
	}

	public void setPassRate(String passRate) {
		this.passRate = passRate;
	}

	@Override
	public String toString() {
		return "CreditCardProductListVO{" + "id=" + id + ", productName='" + productName + '\'' + ", logoUrl='"
				+ logoUrl + '\'' + ", totalBonus=" + totalBonus + ", passRate='" + passRate + '\''
				+ ", rebackCashDesc='" + rebackCashDesc + '\'' + ", directBonus=" + directBonus + ", indirectBonus="
				+ indirectBonus + '}';
	}
}
