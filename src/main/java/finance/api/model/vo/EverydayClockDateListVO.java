package finance.api.model.vo;

/**
 * @program: finance-server
 *
 * @description: 每日签到日期列表VO
 *
 * @author: MORUIHAI
 *
 * @create: 2018-08-26 16:35
 **/
public class EverydayClockDateListVO {
    private Integer date;
    private String status;

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EverydayClockDateListVO{" +
                "date=" + date +
                ", status='" + status + '\'' +
                '}';
    }
}
