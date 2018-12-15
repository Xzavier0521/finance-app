package cn.zhishush.finance.domainservice.repository.third.impl.product.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import cn.zhishush.finance.core.dal.dao.product.CreditCardDetailsDAO;
import cn.zhishush.finance.domain.creditcard.CreditCardDetails;
import cn.zhishush.finance.domainservice.converter.product.CreditCardDetailsConverter;
import cn.zhishush.finance.domainservice.repository.third.impl.product.CreditCardDetailsRepository;

import com.google.common.collect.Maps;

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

    @Override
    public List<CreditCardDetails> query() {
        Map<String, Object> parameters = Maps.newHashMap();
        return CreditCardDetailsConverter.convert2List(creditCardDetailsDAO.query(parameters));
    }
}
