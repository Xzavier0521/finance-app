package finance.domainservice.repository.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

import finance.api.model.base.Page;
import finance.core.dal.dao.CreditCardInfoDAO;
import finance.domain.creditcard.CreditCardInfo;
import finance.domainservice.converter.CreditCardInfoConverter;
import finance.domainservice.repository.CreditCardInfoRepository;

/**
 * <p>信用卡信息</p>
 *
 * @author lili
 * @version 1.0: CreditCardInfoRepositoryImpl.java, v0.1 2018/11/29 12:59 AM PM lili Exp $
 */
@Repository("creditCardInfoRepository")
public class CreditCardInfoRepositoryImpl implements CreditCardInfoRepository {

    @Resource
    private CreditCardInfoDAO creditCardInfoDAO;

    @Override
    public Page<CreditCardInfo> query(int pageSize, int pageNum) {
        Page<CreditCardInfo> page = new Page<>(pageSize, (long) pageNum);
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("page", page);
        int count = creditCardInfoDAO.count(parameters);
        page.setTotalCount((long) count);
        if (count > 0) {
            page.setDataList(
                CreditCardInfoConverter.convert2List(creditCardInfoDAO.query(parameters)));
        }
        return page;
    }

    @Override
    public CreditCardInfo query(String cardCode) {
        CreditCardInfo creditCardInfo = null;
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("cardCode", cardCode);
        List<CreditCardInfo> creditCardInfoList = CreditCardInfoConverter
            .convert2List(creditCardInfoDAO.query(parameters));
        if (CollectionUtils.isNotEmpty(creditCardInfoList)) {
            creditCardInfo = creditCardInfoList.get(0);
        }
        return creditCardInfo;
    }
}
