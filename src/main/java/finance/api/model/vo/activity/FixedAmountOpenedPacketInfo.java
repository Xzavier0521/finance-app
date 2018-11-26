package finance.api.model.vo.activity;

import java.math.BigDecimal;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version 1.0: FixedAmountOpenedPacketInfo.java, v0.1 2018/11/26 7:07 PM lili Exp $
 */
@Data
public class FixedAmountOpenedPacketInfo {
    private String     mobileNum;

    private BigDecimal openedAmount;

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum.substring(0, 3) + "****" + mobileNum.substring(7, 11);
    }

    public BigDecimal getOpenedAmount() {
        return openedAmount;
    }

    public void setOpenedAmount(BigDecimal openedAmount) {
        this.openedAmount = openedAmount;
    }

}
