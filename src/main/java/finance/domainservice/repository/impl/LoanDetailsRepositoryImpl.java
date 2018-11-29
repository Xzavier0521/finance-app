package finance.domainservice.repository.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

import finance.core.dal.dao.LoanDetailsDAO;
import finance.domain.loan.LoanDetails;
import finance.domainservice.converter.LoanDetailsConverter;
import finance.domainservice.repository.LoanDetailsRepository;

/**
 * <p>贷款产品明细</p>
 *
 * @author lili
 * @version 1.0: LoanDetailsRepositoryImpl.java, v0.1 2018/11/29 3:08 PM PM lili Exp $
 */
@Repository("loanDetailsRepository")
public class LoanDetailsRepositoryImpl implements LoanDetailsRepository {

    @Resource
    private LoanDetailsDAO loanDetailsDAO;

    @Override
    public LoanDetails query(String productCode) {
        LoanDetails loanDetails = null;
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("productCode", productCode);
        List<LoanDetails> loanDetailsList = LoanDetailsConverter
            .convert2List(loanDetailsDAO.query(parameters));
        if (CollectionUtils.isNotEmpty(loanDetailsList)) {
            loanDetails = loanDetailsList.get(0);
        }
        return loanDetails;
    }
}
