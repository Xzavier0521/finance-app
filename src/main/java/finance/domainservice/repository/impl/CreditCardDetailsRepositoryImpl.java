package finance.domainservice.repository.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

import finance.core.dal.dao.CreditCardDetailsDAO;
import finance.domain.creditcard.CreditCardDetails;
import finance.domainservice.converter.CreditCardDetailsConverter;
import finance.domainservice.repository.CreditCardDetailsRepository;

/**
 * <p>信用卡明细</p>
 *
 * @author lili
 * @version 1.0: CreditCardDetailsRepositoryImpl.java, v0.1 2018/11/29 1:34 AM PM lili Exp $
 */
@Repository("creditCardDetailsRepository")
public class CreditCardDetailsRepositoryImpl implements CreditCardDetailsRepository {

    @Resource
    private CreditCardDetailsDAO creditCardDetailsDAO;

    @Override
    public CreditCardDetails query(String cardCode) {
        CreditCardDetails creditCardDetails = null;
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("cardCode", cardCode);
        List<CreditCardDetails> creditCardDetailsList = CreditCardDetailsConverter
            .convert2List(creditCardDetailsDAO.query(parameters));
        if (CollectionUtils.isNotEmpty(creditCardDetailsList)) {
            creditCardDetails = creditCardDetailsList.get(0);
        }
        return creditCardDetails;
    }
}
