package finance.domain.coin;

import lombok.Data;

import java.util.Date;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: CoinLog.java, v 0.1 2018/9/28 上午9:25 lili Exp $
 */
@Data
public class CoinLog {

    /**
     * 主键
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 任务id
     */
    private Long taskId;
    /**
     *
     */
    private String taskName;
    /**
     *
     */
    private Integer num;
    /**
     *
     */
    private Integer isDelete;
    /**
     *  创建者
     */
    private String creator;
    /**
     *  更新者
     */
    private String updator;
    /**
     *  版本号
     */
    private Integer version;
    /**
     *  创建时间
     */
    private Date createTime;
    /**
     *  更新时间
     */
    private Date updateTime;
}
