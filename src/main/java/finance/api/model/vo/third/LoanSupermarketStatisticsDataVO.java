package finance.api.model.vo.third;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>贷超数据</p>
 *
 * @author lili
 * @version 1.0: LoanSupermarketStatisticsDataVO.java, v0.1 2018/12/5 6:33 PM PM lili Exp $
 */
@Data
public class LoanSupermarketStatisticsDataVO implements Serializable {

    private static final long serialVersionUID = 7078179847917791920L;
    /**
     * 注册用户数
     */
    private int               registerNum;

    /**
     * 渠道代码
     */
    private String            channelCode;

    /**
     * 渠道名称
     */
    private String            channelName;

}
