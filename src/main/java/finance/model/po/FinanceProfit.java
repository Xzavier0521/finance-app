package finance.model.po;

import java.math.BigDecimal;
import java.util.Date;

public class FinanceProfit {
    private Long id;

    private Long detailId;

    private Long prodId;

    private String prodName;

    private String terminalName;

    private Long terminalId;

    private String terminalPhone;

    private BigDecimal terminalMoney;

    private Long parentId;

    private String parentPhone;

    private String parentName;

    private BigDecimal parentMoney;

    private Long grandParentId;

    private String grandParentPhone;

    private String grandParentName;

    private BigDecimal grandParentMoney;

    private String status;

    private Date createTime;

    private Date updateTime;

    private Integer version;

    private Integer isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    public String getTerminalName() {
        return terminalName;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName == null ? null : terminalName.trim();
    }

    public Long getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(Long terminalId) {
        this.terminalId = terminalId;
    }

    public String getTerminalPhone() {
        return terminalPhone;
    }

    public void setTerminalPhone(String terminalPhone) {
        this.terminalPhone = terminalPhone == null ? null : terminalPhone.trim();
    }

    public BigDecimal getTerminalMoney() {
        return terminalMoney;
    }

    public void setTerminalMoney(BigDecimal terminalMoney) {
        this.terminalMoney = terminalMoney;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone == null ? null : parentPhone.trim();
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName == null ? null : parentName.trim();
    }

    public BigDecimal getParentMoney() {
        return parentMoney;
    }

    public void setParentMoney(BigDecimal parentMoney) {
        this.parentMoney = parentMoney;
    }

    public Long getGrandParentId() {
        return grandParentId;
    }

    public void setGrandParentId(Long grandParentId) {
        this.grandParentId = grandParentId;
    }

    public String getGrandParentPhone() {
        return grandParentPhone;
    }

    public void setGrandParentPhone(String grandParentPhone) {
        this.grandParentPhone = grandParentPhone == null ? null : grandParentPhone.trim();
    }

    public String getGrandParentName() {
        return grandParentName;
    }

    public void setGrandParentName(String grandParentName) {
        this.grandParentName = grandParentName == null ? null : grandParentName.trim();
    }

    public BigDecimal getGrandParentMoney() {
        return grandParentMoney;
    }

    public void setGrandParentMoney(BigDecimal grandParentMoney) {
        this.grandParentMoney = grandParentMoney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}