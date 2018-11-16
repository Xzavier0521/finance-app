package finance.domain.user;

import lombok.Data;

import java.util.Date;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: UserInfo.java, v 0.1 2018/9/29 上午9:13 lili Exp $
 */
@Data
public class UserInfo {

    private Long    id;

    private String  mobileNum;

    /**
     * 注册时间
     */
    private String  registerDate;

    private  String registerTime;

    /**
     * 是否支付金币
     */
    private boolean isPayCoin;
    /**
     * 邀请码
     */
    private String  inviteCode;

    private String  status;

    private Integer isDelete;

    private String  creator;

    private String  updator;

    private Integer version;

    private Date    createTime;

    private Date    updateTime;
}
