package cn.zhishush.finance.ext.api.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>注释</p>
 * @author lili
 * @version 1.0: KaMengUserApplyCreditCardResponse.java, v0.1 2018/12/7 1:15 AM lili Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class KaMengUserApplyCreditCardResponse extends KaMengBasicResponse {
    private static final long serialVersionUID = 4425029803301490479L;
    /**
     * 结果
     */
    private String            results;
}
