package finance.api.model.vo.creditCard;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import finance.api.model.vo.cashback.CashBackConfigTableVO;
import finance.api.model.vo.common.BodyVO;

/**
 * <p>信用卡明细</p>
 *
 * @author lili
 * @version 1.0: CreditCardDetailVO.java, v0.1 2018/11/27 11:16 AM PM lili Exp $
 */
@Data
public class CreditCardDetailVO implements Serializable {

    private static final long     serialVersionUID = 5998416785645147584L;
    /**
     * 银行代码
     */
    private String                bankCode;

    /**
     * 信用卡卡号
     */
    private String                cardCode;

    /**
     * 信用卡名称
     */
    private String                cardName;

    /**
     * banner url
     */
    private String                cardBannerUrl;

    /**
     * 渠道类型
     */
    private String                channelType;
    /**
     * 跳转第三方url
     */
    private String                redirectUrl;

    /**
     * 分享图片地址	
     */
    private String                shareImgUrl;

    /**
     * 信用卡简述
     */
    private String                cardDetailDesc;

    /**
     * 标签
     */
    private String                cardDetailTag;

    /**
     * 返佣配置
     */
    private CashBackConfigTableVO cashBackConfigTable;

    /**
     * body
     */
    private List<BodyVO>          bodyList;
}
