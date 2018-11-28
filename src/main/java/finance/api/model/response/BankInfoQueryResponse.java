package finance.api.model.response;

import finance.api.model.base.Page;
import finance.api.model.vo.creditCard.BankInfo;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: BankInfoQueryResponse.java, v0.1 2018/11/27 10:05 AM PM lili Exp $
 */
public class BankInfoQueryResponse extends Page<BankInfo> {

    /**
     * @param pageSize 每页记录数
     * @param pageNum
     */
    public BankInfoQueryResponse(Integer pageSize, Long pageNum) {
        super(pageSize, pageNum);
    }
}
