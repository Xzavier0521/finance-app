package finance.api.model.vo.financeproduct;

import java.math.BigDecimal;

import finance.core.common.enums.AmountType;

/**
 * @program: finance-app
 *
 * @description: 贷款产品详情VO
 *
 * @author: MORUIHAI
 *
 * @create: 2018-07-06 14:30
 **/
public class LoanProductDetailVO {
	private Long id;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 标签1
	 */
	private String mark1;
	/**
	 * 标签2
	 */
	private String mark2;
	/**
	 * 额度范围
	 */
	private String amountScope;
	/**
	 * 期限范围
	 */
	private String dateScope;
	/**
	 * logo
	 */
	private String logoUrl;
	/**
	 * 终端
	 */
	private String terminalBonus;
	/**
	 * 直推
	 */
	private String directBonus;
	/**
	 * 间推
	 */
	private String indirectBonus;
	/**
	 * 返现日期
	 */
	private String cashbackDate;
	/**
	 * 跳转页
	 */
	private String redirectUrl;
	/**
	 * 金额类型，1-金额值，2:-百分比
	 */
	private String amountType;
	private String productDesc;
	/**
	 * 推广页banner
	 */
	private String promotionUrl; //

	public String getAmountType() {
		return amountType;
	}

	public void setAmountType(String amountType) {
		this.amountType = amountType;
	}

	public String getPromotionUrl() {
		return promotionUrl;
	}

	public void setPromotionUrl(String promotionUrl) {
		this.promotionUrl = promotionUrl;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
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

	public String getMark1() {
		return mark1;
	}

	public void setMark1(String mark1) {
		this.mark1 = mark1;
	}

	public String getMark2() {
		return mark2;
	}

	public void setMark2(String mark2) {
		this.mark2 = mark2;
	}

	public String getAmountScope() {
		return amountScope;
	}

	public void setAmountScope(String amountScope) {
		this.amountScope = amountScope;
	}

	public String getDateScope() {
		return dateScope;
	}

	public void setDateScope(String dateScope) {
		this.dateScope = dateScope;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getTerminalBonus() {
		return terminalBonus;
	}

	public void setTerminalBonus(BigDecimal terminalBonus, Integer amountType) {
		if (AmountType.amount.getCode().equals(amountType)) {
			this.terminalBonus = terminalBonus == null ? "" : terminalBonus.stripTrailingZeros().toPlainString();
		} else if (AmountType.percenta.getCode().equals(amountType)) {
			this.terminalBonus = terminalBonus.multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString() + "%";
		}

	}

	public String getDirectBonus() {
		return directBonus;
	}

	public void setDirectBonus(BigDecimal directBonus, Integer amountType) {
		if (AmountType.amount.getCode().equals(amountType)) {
			this.directBonus = directBonus == null ? "" : directBonus.stripTrailingZeros().toPlainString();
		} else if (AmountType.percenta.getCode().equals(amountType)) {
			this.directBonus = directBonus.multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString() + "%";
		}

	}

	public String getIndirectBonus() {
		return indirectBonus;
	}

	public void setIndirectBonus(BigDecimal indirectBonus, Integer amountType) {
		if (AmountType.amount.getCode().equals(amountType)) {
			this.indirectBonus = indirectBonus == null ? "" : indirectBonus.stripTrailingZeros().toPlainString();
		} else if (AmountType.percenta.getCode().equals(amountType)) {
			this.indirectBonus = indirectBonus.multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString() + "%";
		}
	}

	public String getCashbackDate() {
		return cashbackDate;
	}

	public void setCashbackDate(String cashbackDate) {
		this.cashbackDate = cashbackDate;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	@Override
	public String toString() {
		return "LoanProductDetailVO{" + "id=" + id + ", productName='" + productName + '\'' + ", mark1='" + mark1 + '\''
				+ ", mark2='" + mark2 + '\'' + ", amountScope='" + amountScope + '\'' + ", dateScope='" + dateScope
				+ '\'' + ", logoUrl='" + logoUrl + '\'' + ", terminalBonus='" + terminalBonus + '\'' + ", directBonus='"
				+ directBonus + '\'' + ", indirectBonus='" + indirectBonus + '\'' + ", cashbackDate='" + cashbackDate
				+ '\'' + ", redirectUrl='" + redirectUrl + '\'' + ", amountType='" + amountType + '\''
				+ ", productDesc='" + productDesc + '\'' + ", promotionUrl='" + promotionUrl + '\'' + '}';
	}
}
