package finance.api.model.vo;

/**
 * @program: finance-server
 *
 * @description: 成长任务VO
 *
 * @author: MORUIHAI
 *
 * @create: 2018-08-28 13:56
 **/
public class GrowTaskVO {
    private Long taskId;
    private String pic;
    private String title;
    private Integer coinNum;
    private Integer currentNum;
    private Integer nextNum;
    private Integer status;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCoinNum() {
        return coinNum;
    }

    public void setCoinNum(Integer coinNum) {
        this.coinNum = coinNum;
    }

    public Integer getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(Integer currentNum) {
        this.currentNum = currentNum;
    }

    public Integer getNextNum() {
        return nextNum;
    }

    public void setNextNum(Integer nextNum) {
        this.nextNum = nextNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "GrowTaskVO{" +
                "taskId=" + taskId +
                ", pic='" + pic + '\'' +
                ", title='" + title + '\'' +
                ", coinNum=" + coinNum +
                ", currentNum=" + currentNum +
                ", nextNum=" + nextNum +
                ", status=" + status +
                '}';
    }
}
