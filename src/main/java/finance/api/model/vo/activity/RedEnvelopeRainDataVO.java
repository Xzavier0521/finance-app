package finance.api.model.vo.activity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import finance.core.common.enums.RedEnvelopeRainTimeCodeEnum;

/**
 * <p>红包雨活动数据p>
 * @author lili
 * @version 1.0: RedEnvelopeRainDataVO.java, v0.1 2018/11/26 7:10 PM lili Exp $
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedEnvelopeRainDataVO implements Serializable {
    private static final long           serialVersionUID = 7739127872932927403L;
    /**
     * 主键
     */
    private Long                        id;

    /**
     * 用户id
     */
    private Long                        userId;

    /**
     * 手机号码
     */
    private String                      mobilePhone;
    /**
     * 时间编码
     */
    private RedEnvelopeRainTimeCodeEnum timeCode;

    /**
     * 红包总个数
     */
    private Long                        totalNum;

    /**
     * 总金额/金币
     */
    private Long                        totalAmount;

    /**
     * 活动日期
     */
    private Integer                     activityDay;

    /**
     * 排名
     */
    private Long                        ranking;

    /**
     * 创建时间
     */
    private Date                        createTime;

    /**
     * 更新时间
     */
    private Date                        updateTime;

    /**
     * 创建者
     */
    private String                      creator;

    /**
     * 更新者
     */
    private String                      updator;

    /**
     * 是否删除 0-否，1-是
     */
    private Integer                     isDelete;

    /**
     * 版本号
     */
    private Long                        version;
}
