package cn.zhishush.finance.domain.user;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>用户信息</p>
 * @author lili
 * @version 1.0: UserInfo.java, v0.1 2018/11/26 2:45 PM lili Exp $
 */
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 4595586211491791364L;
    private Long              id;

    private String            mobileNum;

    /**
     * 注册时间
     */
    private String            registerDate;

    private String            registerTime;

    /**
     * 是否支付金币
     */
    private boolean           isPayCoin;
    /**
     * 邀请码
     */
    private String            inviteCode;

    private String            status;

    private Integer           isDelete;

    private String            creator;

    private String            updator;

    private Integer           version;

    private Date              createTime;

    private Date              updateTime;
}
