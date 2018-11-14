package finance.model.po;

import java.util.Date;

public class FinanceExcelAll {
    private Long id;

    private String batchNo;

    private String creater;

    private String updater;

    private Long detailsNum;

    private Long detailsValidNum;

    private Long failNum;

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

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo == null ? null : batchNo.trim();
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

    public Long getDetailsNum() {
        return detailsNum;
    }

    public void setDetailsNum(Long detailsNum) {
        this.detailsNum = detailsNum;
    }

    public Long getDetailsValidNum() {
        return detailsValidNum;
    }

    public void setDetailsValidNum(Long detailsValidNum) {
        this.detailsValidNum = detailsValidNum;
    }

    public Long getFailNum() {
        return failNum;
    }

    public void setFailNum(Long failNum) {
        this.failNum = failNum;
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