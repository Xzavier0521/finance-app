package cn.zhishush.finance.api.model.vo.activity;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version 1.0: FixedAmountInvitedInfoVO.java, v0.1 2018/11/26 7:06 PM lili Exp $
 */
@Data
public class FixedAmountInvitedInfoVO implements Serializable {
    private static final long serialVersionUID = -155041054690835430L;
    private String            mobileNum;

    private Integer           inviteNum;

    private BigDecimal        getAmount;

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum.substring(0, 3) + "****" + mobileNum.substring(7, 11);
    }

    public Integer getInviteNum() {
        return inviteNum;
    }

    public void setInviteNum(Integer inviteNum) {
        this.inviteNum = inviteNum;
    }

    public BigDecimal getGetAmount() {
        return getAmount;
    }

    public void setGetAmount(BigDecimal getAmount) {
        this.getAmount = getAmount;
    }

}
