package cn.zhishush.finance.api.model.vo.financeproduct;

import java.math.BigDecimal;

import cn.zhishush.finance.core.common.enums.AmountType;

/**
 * @program: finance-app
 *
 * @description: 信用卡产品明细VO
 *
 * @author: MORUIHAI
 *
 * @create: 2018-07-06 13:52
 **/
public class CreditCardProductDetailVO {
	private Long id;
	private String productName; // 产品名称
	private String detailPageUrl; // 详情页url
	private String terminalBonus; // 终端
	private String directBonus; // 直推
	private String indirectBonus; // 间推
	private String cashbackDate; // 返现日期
	private String redirectUrl; // 跳转页
	private String productDesc;
	private String promotionUrl; // 推广页banner

	private String maxAmount;
	private String auditLength;
	private String passRate; // 通过率
	private String amountType; // 金额类型，1:金额值，2:百分比

	@Override
	public String toString() {
		return "CreditCardProductDetailVO{" + "id=" + id + ", productName='" + productName + '\'' + ", detailPageUrl='"
				+ detailPageUrl + '\'' + ", terminalBonus='" + terminalBonus + '\'' + ", directBonus='" + directBonus
				+ '\'' + ", indirectBonus='" + indirectBonus + '\'' + ", cashbackDate='" + cashbackDate + '\''
				+ ", redirectUrl='" + redirectUrl + '\'' + ", productDesc='" + productDesc + '\'' + ", promotionUrl='"
				+ promotionUrl + '\'' + ", maxAmount='" + maxAmount + '\'' + ", auditLength='" + auditLength + '\''
				+ ", passRate='" + passRate + '\'' + ", amountType='" + amountType + '\'' + '}';
	}

	public String getPassRate() {
		return passRate;
	}

	public void setPassRate(String passRate) {
		this.passRate = passRate;
	}

	public String getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(String maxAmount) {
		this.maxAmount = maxAmount;
	}

	public String getAuditLength() {
		return auditLength;
	}

	public void setAuditLength(String auditLength) {
		this.auditLength = auditLength;
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

	public String getDetailPageUrl() {
		return detailPageUrl;
	}

	public void setDetailPageUrl(String detailPageUrl) {
		this.detailPageUrl = detailPageUrl;
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

	public String getAmountType() {
		return amountType;
	}

	public void setAmountType(String amountType) {
		this.amountType = amountType;
	}
}
