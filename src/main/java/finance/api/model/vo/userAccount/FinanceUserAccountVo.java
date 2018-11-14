package finance.api.model.vo.userAccount;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceUserAccountVo.java, v0.1 2018/11/14 3:25 PM lili Exp $
 */
@Data
public class FinanceUserAccountVo implements Serializable {

    private static final long serialVersionUID = -1952995942404471111L;
    private BigDecimal        canWithdrawMoney;

    private BigDecimal        withdrawedMoney;

    private BigDecimal        incomeMoney;

}