package finance.model.po;

import java.util.Date;

public class FinanceLoanDetail {
    private Long id;

    private Long productId;

    private String mark1;

    private String mark2;

    private String amountScope;

    private String dateScope;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;

    private String creator;

    private String updator;

    private Integer version;

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

    public String getMark1() {
        return mark1;
    }

    public void setMark1(String mark1) {
        this.mark1 = mark1 == null ? null : mark1.trim();
    }

    public String getMark2() {
        return mark2;
    }

    public void setMark2(String mark2) {
        this.mark2 = mark2 == null ? null : mark2.trim();
    }

    public String getAmountScope() {
        return amountScope;
    }

    public void setAmountScope(String amountScope) {
        this.amountScope = amountScope == null ? null : amountScope.trim();
    }

    public String getDateScope() {
        return dateScope;
    }

    public void setDateScope(String dateScope) {
        this.dateScope = dateScope == null ? null : dateScope.trim();
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator == null ? null : updator.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "FinanceLoanDetail{" +
                "id=" + id +
                ", productId=" + productId +
                ", mark1='" + mark1 + '\'' +
                ", mark2='" + mark2 + '\'' +
                ", amountScope='" + amountScope + '\'' +
                ", dateScope='" + dateScope + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDelete=" + isDelete +
                ", creator='" + creator + '\'' +
                ", updator='" + updator + '\'' +
                ", version=" + version +
                '}';
    }
}