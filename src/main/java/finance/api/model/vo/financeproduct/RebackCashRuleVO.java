package finance.api.model.vo.financeproduct;

import java.math.BigDecimal;

import finance.core.common.enums.AmountType;

/**
 * @program: finance-app
 *
 * @description: 返现规则VO
 *
 * @author: MORUIHAI
 *
 * @create: 2018-07-06 15:17
 **/
public class RebackCashRuleVO {
	private Long id;
	private String productName; // 产品名称
	private String terminalBonus; // 终端
	private String directBonus; // 直推
	private String indirectBonus; // 间推
	private String cashbackDate; // 返现日期

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

	@Override
	public String toString() {
		return "RebackCashRuleVO{" + "id=" + id + ", productName='" + productName + '\'' + ", terminalBonus='"
				+ terminalBonus + '\'' + ", directBonus='" + directBonus + '\'' + ", indirectBonus='" + indirectBonus
				+ '\'' + ", cashbackDate='" + cashbackDate + '\'' + '}';
	}
}
