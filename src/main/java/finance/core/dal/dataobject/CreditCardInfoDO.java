package finance.core.dal.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * <p>信用卡信息</p>
 * @author lili
 * @version 1.0: CreditCardInfoDO.java, v0.1 2018/11/29 12:56 AM lili Exp $
 */
@Data
public class CreditCardInfoDO implements Serializable {
    private static final long serialVersionUID = -4498897526476080639L;
    /**
     * 主键
     */
    private Long              id;

    /**
     * 银行代码
     */
    private String            bankCode;

    /**
     * 信用卡代码
     */
    private String            cardCode;

    /**
     * 信用卡名称
     */
    private String            cardName;

    /**
     * logo url
     */
    private String            cardLogoUrl;
    /**
     * 标签
     */
    private String            cardTag;
    /**
     * 起始额度
     */
    private BigDecimal        cardLimitMin;

    /**
     * 结束额度
     */
    private BigDecimal        cardLimitMax;

    /**
     * 预计通过率
     */
    private String            predictPassingRate;

    /**
     * 顺序
     */
    private Long              order;

    /**
     * 卡类型:1-热门,2-白金卡,3-双币卡
     */
    private String            cardTypes;

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
     * 是否删除
     */
    private String            isDelete;

    /**
     * 版本号
     */
    private Long              version;

}