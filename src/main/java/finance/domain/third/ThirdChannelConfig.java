package finance.domain.third;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>第三方渠道配置</p>
 * @author lili
 * @version 1.0: ThirdChannelConfig.java, v0.1 2018/12/5 6:17 PM lili Exp $
 */
@Data
public class ThirdChannelConfig implements Serializable {
    private static final long serialVersionUID = 8430926071985455795L;
    /**
     * 主键
     */
    private Long              id;

    /**
     * 用户id
     */
    private Long              userId;

    /**
     * 手机号码
     */
    private String            mobileNum;

    /**
     * 渠道编码
     */
    private String            channelCode;

    /**
     * 渠道名称
     */
    private String            channelName;

    /**
     * 创建时间
     */
    private Date              createDate;

    /**
     * 更新时间
     */
    private Date              updateTime;

    /**
     * 创建者
     */
    private String            creator;

    /**
     * 更新者
     */
    private String            updator;

    /**
     * 是否删除 0-否 1-是
     */
    private String            isDelete;

    /**
     * 版本号
     */
    private Long              version;

}