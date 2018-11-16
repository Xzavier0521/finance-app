package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version $Id: RedEnvelopeRainDataDO.java, v0.1 2018/11/16 10:41 AM lili Exp $
 */
@Data
public class RedEnvelopeRainDataDO implements Serializable {
    private static final long serialVersionUID = 884713642633448821L;
    /**
     * 主键
     */
    private Long              id;

    /**
     * 活动代码
     */
    private String            activityCode;

    /**
     * 用户id
     */
    private Long              userId;

    /**
     * 手机号码
     */
    private String            mobilePhone;

    /**
     * 时间编码
     */
    private String            timeCode;

    /**
     * 红包总个数
     */
    private Long              totalNum;

    /**
     * 总金额/金币
     */
    private Long              totalAmount;

    /**
     * 活动日期
     */
    private Integer           activityDay;

    /**
     * 排名
     */
    private Long              ranking;

    /**
     * 创建时间
     */
    private Date              createTime;

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
     * 是否删除 0-否，1-是
     */
    private Integer           isDelete;

    /**
     * 版本号
     */
    private Long              version;

}