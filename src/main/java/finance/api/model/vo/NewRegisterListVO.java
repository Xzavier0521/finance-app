package finance.api.model.vo;

/**
 * @program: finance-server
 *
 * @description: 新手任务列表VO
 *
 * @author: MORUIHAI
 *
 * @create: 2018-08-27 15:15
 **/
public class NewRegisterListVO {
    private Integer coinNum;
    private String pic;
    private String title;
    private Long taskId;
    private Integer status;

    public Integer getCoinNum() {
        return coinNum;
    }

    public void setCoinNum(Integer coinNum) {
        this.coinNum = coinNum;
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

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "NewRegisterListVO{" +
                "coinNum=" + coinNum +
                ", pic='" + pic + '\'' +
                ", title='" + title + '\'' +
                ", taskId=" + taskId +
                ", status=" + status +
                '}';
    }
}
