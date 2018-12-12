package cn.zhishush.finance.domainservice.repository.third.impl.product.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.zhishush.finance.domain.loan.LoanDetails;
import cn.zhishush.finance.domainservice.converter.product.LoanDetailsConverter;
import cn.zhishush.finance.domainservice.repository.third.impl.product.LoanDetailsRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

import cn.zhishush.finance.core.dal.dao.product.LoanDetailsDAO;

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
