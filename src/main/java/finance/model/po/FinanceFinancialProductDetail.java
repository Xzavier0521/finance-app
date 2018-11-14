package finance.model.po;

import java.util.Date;

public class FinanceFinancialProductDetail {
    private Long id;

    private Long productId;

    private String mark;

    private String aveRevenue;

    private String productBackground;

    private String grade;

    private String backgroundStrength;

    private String riskControl;

    private String operationCapability;

    private String startAmount;

    private String startPeriod;

    private String rebackName;

    private String rebackValue;

    private String totalReturn;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;

    private String creater;

    private String updater;

    private Integer version;

    private String cashbackRule;

    public String getCashbackRule() {
        return cashbackRule;
    }

    public void setCashbackRule(String cashbackRule) {
        this.cashbackRule = cashbackRule;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }

    public String getAveRevenue() {
        return aveRevenue;
    }

    public void setAveRevenue(String aveRevenue) {
        this.aveRevenue = aveRevenue == null ? null : aveRevenue.trim();
    }

    public String getProductBackground() {
        return productBackground;
    }

    public void setProductBackground(String productBackground) {
        this.productBackground = productBackground == null ? null : productBackground.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getBackgroundStrength() {
        return backgroundStrength;
    }

    public void setBackgroundStrength(String backgroundStrength) {
        this.backgroundStrength = backgroundStrength == null ? null : backgroundStrength.trim();
    }

    public String getRiskControl() {
        return riskControl;
    }

    public void setRiskControl(String riskControl) {
        this.riskControl = riskControl == null ? null : riskControl.trim();
    }

    public String getOperationCapability() {
        return operationCapability;
    }

    public void setOperationCapability(String operationCapability) {
        this.operationCapability = operationCapability == null ? null : operationCapability.trim();
    }

    public String getStartAmount() {
        return startAmount;
    }

    public void setStartAmount(String startAmount) {
        this.startAmount = startAmount == null ? null : startAmount.trim();
    }

    public String getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(String startPeriod) {
        this.startPeriod = startPeriod == null ? null : startPeriod.trim();
    }

    public String getRebackName() {
        return rebackName;
    }

    public void setRebackName(String rebackName) {
        this.rebackName = rebackName == null ? null : rebackName.trim();
    }

    public String getRebackValue() {
        return rebackValue;
    }

    public void setRebackValue(String rebackValue) {
        this.rebackValue = rebackValue == null ? null : rebackValue.trim();
    }

    public String getTotalReturn() {
        return totalReturn;
    }

    public void setTotalReturn(String totalReturn) {
        this.totalReturn = totalReturn == null ? null : totalReturn.trim();
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "FinanceFinancialProductDetail{" +
                "id=" + id +
                ", productId=" + productId +
                ", mark='" + mark + '\'' +
                ", aveRevenue='" + aveRevenue + '\'' +
                ", productBackground='" + productBackground + '\'' +
                ", grade='" + grade + '\'' +
                ", backgroundStrength='" + backgroundStrength + '\'' +
                ", riskControl='" + riskControl + '\'' +
                ", operationCapability='" + operationCapability + '\'' +
                ", startAmount='" + startAmount + '\'' +
                ", startPeriod='" + startPeriod + '\'' +
                ", rebackName='" + rebackName + '\'' +
                ", rebackValue='" + rebackValue + '\'' +
                ", totalReturn='" + totalReturn + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDelete=" + isDelete +
                ", creater='" + creater + '\'' +
                ", updater='" + updater + '\'' +
                ", version=" + version +
                ", cashbackRule='" + cashbackRule + '\'' +
                '}';
    }
}