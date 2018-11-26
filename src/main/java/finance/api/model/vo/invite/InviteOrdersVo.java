package finance.api.model.vo.invite;

/**
 * 邀请人排行榜展示类.
 * 
 * @author hewenbin
 * @version v1.0 2018年8月19日 上午10:05:27 hewenbin
 */
public class InviteOrdersVo {

    private Long   userId;
    /** 邀请的人数（一级和二级） */
    private Long   inviteNum;
    private String realName;
    /** 排行、名次 */
    private int    orderNum;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getInviteNum() {
        return inviteNum;
    }

    public void setInviteNum(Long inviteNum) {
        this.inviteNum = inviteNum;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "InviteOrdersVo{" + "userId=" + userId + ", inviteNum=" + inviteNum + ", realName='"
               + realName + '\'' + ", orderNum=" + orderNum + '}';
    }

}
