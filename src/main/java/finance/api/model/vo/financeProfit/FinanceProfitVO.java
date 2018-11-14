package finance.api.model.vo.financeProfit;

import finance.core.common.util.DateUtil;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

public class FinanceProfitVO implements Comparable<FinanceProfitVO> {

    private String     prodName;

    private String     terminalName;

    private String     terminalPhone;

    private BigDecimal terminalMoney;

    private String     createTime;

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getTerminalName() {
        return terminalName;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = (StringUtils.isEmpty(terminalName) || "null".equals(terminalName))
            ? "神秘大侠"
            : terminalName;
    }

    public String getTerminalPhone() {
        return terminalPhone;
    }

    public void setTerminalPhone(String terminalPhone) {
        this.terminalPhone = terminalPhone;
    }

    public BigDecimal getTerminalMoney() {
        return terminalMoney;
    }

    public void setTerminalMoney(BigDecimal terminalMoney) {
        this.terminalMoney = terminalMoney;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public int compareTo(FinanceProfitVO o) {
        return DateUtil.stringToDate(this.getCreateTime(), DateUtil.fm_yyyy_MM_dd_HHmmss).before(
            DateUtil.stringToDate(o.getCreateTime(), DateUtil.fm_yyyy_MM_dd_HHmmss)) ? 1 : 0;
    }

    @Override
    public String toString() {
        return "FinanceProfitVO{" + "prodName='" + prodName + '\'' + ", terminalName='"
               + terminalName + '\'' + ", terminalPhone='" + terminalPhone + '\''
               + ", terminalMoney=" + terminalMoney + ", createTime='" + createTime + '\'' + '}';
    }
}