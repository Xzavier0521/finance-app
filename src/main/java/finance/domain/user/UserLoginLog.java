package finance.domain.user;

import lombok.Data;

import java.util.Date;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: UserLoginLog.java, v 0.1 2018/9/28 上午11:30 lili Exp $
 */
@Data
public class UserLoginLog {

    private Long id;
    private Long userId;
    private String type;
    private String loginName;
    private String loginStatus;
    private String loginDesc;
    private String ip;
    private String userAgent;
    private String platformCode;
    private String platformDetail;
    private Integer isDelete;
    private String creator;
    private String updator;
    private Integer version;
    private Date createTime;
    private Date updateTime;
}
