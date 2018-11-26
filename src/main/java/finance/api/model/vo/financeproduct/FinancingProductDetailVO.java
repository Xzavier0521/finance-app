package finance.api.model.vo.financeproduct;

import java.math.BigDecimal;

import finance.core.common.enums.AmountType;

/**
 * @program: finance-app
 *
 * @description: 理财产品详情VO
 *
 * @author: MORUIHAI
 *
 * @create: 2018-07-06 10:11
 **/
public class FinancingProductDetailVO {
	private Long id;
	private String productName; // 产品名称

	private String redirectUrl; // 跳转页

	private String terminalBonus; // 终端

	private String directBonus; // 直推

	private String indirectBonus; // 间推

	private String logoUrl; // LOGO

	private String cashbackDate; // 返现日期

	private String level; // 众合评级

	private String backgroundStrength; // 背景实力

	private String riskControl; // 平台风控

	private String operationCapability; // 运营能力

	private String startAmount; // 投资金额

	private String startPeriod; // 投资期限

	private String rebackName; // 金榕返名称

	private String rebackValue; // 金榕返值

	private String totalReturn; // 总回报
	private String aveRevenue; // 平均收益
	private String cashbackRule; // 返现规则

	private String productDesc; // 产品描述

	private String promotionUrl; // 推广页banner

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

	public String getCashbackRule() {
		return cashbackRule;
	}

	public void setCashbackRule(String cashbackRule) {
		this.cashbackRule = cashbackRule;
	}

	public String getAveRevenue() {
		return aveRevenue;
	}

	public void setAveRevenue(String aveRevenue) {
		this.aveRevenue = aveRevenue;
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

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
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

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getCashbackDate() {
		return cashbackDate;
	}

	public void setCashbackDate(String cashbackDate) {
		this.cashbackDate = cashbackDate;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getBackgroundStrength() {
		return backgroundStrength;
	}

	public void setBackgroundStrength(String backgroundStrength) {
		this.backgroundStrength = backgroundStrength;
	}

	public String getRiskControl() {
		return riskControl;
	}

	public void setRiskControl(String riskControl) {
		this.riskControl = riskControl;
	}

	public String getOperationCapability() {
		return operationCapability;
	}

	public void setOperationCapability(String operationCapability) {
		this.operationCapability = operationCapability;
	}

	public String getStartAmount() {
		return startAmount;
	}

	public void setStartAmount(String startAmount) {
		this.startAmount = startAmount;
	}

	public String getStartPeriod() {
		return startPeriod;
	}

	public void setStartPeriod(String startPeriod) {
		this.startPeriod = startPeriod;
	}

	public String getRebackName() {
		return rebackName;
	}

	public void setRebackName(String rebackName) {
		this.rebackName = rebackName;
	}

	public String getRebackValue() {
		return rebackValue;
	}

	public void setRebackValue(String rebackValue) {
		this.rebackValue = rebackValue;
	}

	public String getTotalReturn() {
		return totalReturn;
	}

	public void setTotalReturn(String totalReturn) {
		this.totalReturn = totalReturn;
	}

	@Override
	public String toString() {
		return "FinancingProductDetailVO{" + "id=" + id + ", productName='" + productName + '\'' + ", redirectUrl='"
				+ redirectUrl + '\'' + ", terminalBonus='" + terminalBonus + '\'' + ", directBonus='" + directBonus
				+ '\'' + ", indirectBonus='" + indirectBonus + '\'' + ", logoUrl='" + logoUrl + '\''
				+ ", cashbackDate='" + cashbackDate + '\'' + ", level='" + level + '\'' + ", backgroundStrength='"
				+ backgroundStrength + '\'' + ", riskControl='" + riskControl + '\'' + ", operationCapability='"
				+ operationCapability + '\'' + ", startAmount='" + startAmount + '\'' + ", startPeriod='" + startPeriod
				+ '\'' + ", rebackName='" + rebackName + '\'' + ", rebackValue='" + rebackValue + '\''
				+ ", totalReturn='" + totalReturn + '\'' + ", aveRevenue='" + aveRevenue + '\'' + ", cashbackRule='"
				+ cashbackRule + '\'' + ", productDesc='" + productDesc + '\'' + ", promotionUrl='" + promotionUrl
				+ '\'' + '}';
	}
}
