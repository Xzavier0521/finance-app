package cn.zhishush.finance.api.model.vo.invite;

import lombok.Data;

/**
 * <p>邀请人排行榜展示类</p>
 * @author lili
 * @version 1.0: InviteOrdersVo.java, v0.1 2018/12/11 10:55 AM lili Exp $
 */
@Data
public class InviteOrdersVo {

    /**
     * 用户id
     */
    private Long   userId;
    /**
     * 邀请的人数（一级和二级）
     */
    private Long   inviteNum;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 排行、名次
     */
    private int    orderNum;

}
