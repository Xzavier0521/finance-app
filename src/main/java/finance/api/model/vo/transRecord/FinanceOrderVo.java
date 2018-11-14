package finance.api.model.vo.transRecord;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceOrderVo.java, v0.1 2018/11/14 3:25 PM lili Exp $
 */
@Data
public class FinanceOrderVo implements Serializable {

    private static final long serialVersionUID = -6104685226279869556L;
    private BigDecimal        money;

    private String            bankCode;

    private String            bankName;

    private String            status;

    private String            createTime;

}