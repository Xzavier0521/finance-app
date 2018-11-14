package finance.api.model.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: BankCardIDSearchVo.java, v0.1 2018/11/14 3:28 PM lili Exp $
 */
@Data
public class BankCardIDSearchVo implements Serializable {

    private static final long serialVersionUID = 2385966073683971157L;
    private String            bankName;
    private String            bankCode;
    private String            accountNo;

    public BankCardIDSearchVo() {
        this.bankName = "";
        this.bankCode = "";
        this.accountNo = "";
    }

}
