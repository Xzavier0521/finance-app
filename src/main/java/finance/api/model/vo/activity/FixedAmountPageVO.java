package finance.api.model.vo.activity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version 1.0: FixedAmountPageVO.java, v0.1 2018/11/26 7:07 PM lili Exp $
 */
@Data
public class FixedAmountPageVO implements Serializable {

    private static final long                 serialVersionUID    = -971431898334288286L;
    /**
     * 所有完成参与活动人数（同一人可重复）
     */
    private Long                              totalJoinNum;
    /**
     *    固定金额活动金额
     */

    private BigDecimal                        redPacketTotalAmount;
    /**
     * 活动发起人拆得金额
     */

    private BigDecimal                        redPacketSponsorAmount;
    /**
     * 固定金额活动剩余金额
     */

    private BigDecimal                        remainingAmount;
    /**
     *  帮拆红包人数
     */

    private int                               openedRedPacketNum;
    /**
     *  还剩人数
     */

    private int                               unopenedRedPacketNum;

    private String                            sponsorMobilNum;

    private List<FixedAmountInvitedInfoVO>    invitedInfoList     = new ArrayList<>();

    private List<FixedAmountOpenedPacketInfo> openedRedPacketList = new ArrayList<>();

    public Long getTotalJoinNum() {
        return totalJoinNum;
    }

    public void setTotalJoinNum(Long totalJoinNum) {
        this.totalJoinNum = totalJoinNum;
    }

    public BigDecimal getRedPacketTotalAmount() {
        return redPacketTotalAmount;
    }

    public void setRedPacketTotalAmount(BigDecimal redPacketTotalAmount) {
        this.redPacketTotalAmount = redPacketTotalAmount;
    }

    public BigDecimal getRedPacketSponsorAmount() {
        return redPacketSponsorAmount;
    }

    public void setRedPacketSponsorAmount(BigDecimal redPacketSponsorAmount) {
        this.redPacketSponsorAmount = redPacketSponsorAmount;
    }

    public BigDecimal getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(BigDecimal remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public int getOpenedRedPacketNum() {
        return openedRedPacketNum;
    }

    public void setOpenedRedPacketNum(int openedRedPacketNum) {
        this.openedRedPacketNum = openedRedPacketNum;
    }

    public int getUnopenedRedPacketNum() {
        return unopenedRedPacketNum;
    }

    public void setUnopenedRedPacketNum(int unopenedRedPacketNum) {
        this.unopenedRedPacketNum = unopenedRedPacketNum;
    }

    public String getSponsorMobilNum() {
        return sponsorMobilNum;
    }

    public void setSponsorMobilNum(String sponsorMobilNum) {
        this.sponsorMobilNum = sponsorMobilNum.substring(0, 3) + "****"
                               + sponsorMobilNum.substring(7, 11);
    }

    public List<FixedAmountInvitedInfoVO> getInvitedInfoList() {
        return invitedInfoList;
    }

    public void setInvitedInfoList(List<FixedAmountInvitedInfoVO> invitedInfoList) {
        this.invitedInfoList = invitedInfoList;
    }

    public List<FixedAmountOpenedPacketInfo> getOpenedRedPacketList() {
        return openedRedPacketList;
    }

    public void setOpenedRedPacketList(List<FixedAmountOpenedPacketInfo> openedRedPacketList) {
        this.openedRedPacketList = openedRedPacketList;
    }

}
