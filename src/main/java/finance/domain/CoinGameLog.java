package finance.domain;

import lombok.Data;

import java.util.Date;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: CoinGameLog.java, v 0.1 2018/9/28 上午10:35 lili Exp $
 */
@Data
public class CoinGameLog {
    private Long id;
    private Long userId;
    private Integer outNum;
    private Integer inNum;
    private Date joinDate;
    private Date joinTime;
    private Date signTime;
    private Integer status;
    private Integer clockCount;
    private Integer isDelete;
    private String creator;
    private String updator;
    private Integer version;
    private Date createTime;
    private Date updateTime;
}
