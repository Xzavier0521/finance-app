package finance.domainservice.repository.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

import finance.api.model.base.Page;
import finance.core.dal.dao.CreditCardApplyInfoDAO;
import finance.domain.creditcard.CreditCardApplyInfo;
import finance.domainservice.converter.CreditCardApplyInfoConverter;
import finance.domainservice.repository.CreditCardApplyInfoRepository;

/**
 * <p>信用卡申请信息</p>
 *
 * @author lili
 * @version 1.0: CreditCardApplyInfoRepositoryImpl.java, v0.1 2018/11/29 5:26 PM PM lili Exp $
 */
@Repository("creditCardApplyInfoRepository")
public class CreditCardApplyInfoRepositoryImpl implements CreditCardApplyInfoRepository {

    @Resource
    private CreditCardApplyInfoDAO creditCardApplyInfoDAO;

    @Override
    public int save(CreditCardApplyInfo creditCardApplyInfo) {
        return creditCardApplyInfoDAO
            .insertSelective(CreditCardApplyInfoConverter.convert(creditCardApplyInfo));
    }

    @Override
    public int update(CreditCardApplyInfo creditCardApplyInfo) {
        return creditCardApplyInfoDAO
            .updateByPrimaryKeySelective(CreditCardApplyInfoConverter.convert(creditCardApplyInfo));
    }

    @Override
    public CreditCardApplyInfo query(Long userId, String productCode) {
        CreditCardApplyInfo creditCardApplyInfo = null;
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("userId", userId);
        parameters.put("productCode", productCode);
        List<CreditCardApplyInfo> creditCardApplyInfoList = CreditCardApplyInfoConverter
            .convert2List(creditCardApplyInfoDAO.query(parameters));
        if (CollectionUtils.isNotEmpty(creditCardApplyInfoList)) {
            creditCardApplyInfo = creditCardApplyInfoList.get(0);
        }
        return creditCardApplyInfo;
    }

    @Override
    public Page<CreditCardApplyInfo> query(Long userId, int pageSize, int pageNum) {
        Page<CreditCardApplyInfo> page = new Page<>(pageSize, (long) pageNum);
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("userId", userId);
        parameters.put("page", page);
        int count = creditCardApplyInfoDAO.count(parameters);
        page.setTotalCount((long) count);
        if (count > 0) {
            page.setDataList(CreditCardApplyInfoConverter
                .convert2List(creditCardApplyInfoDAO.query(parameters)));
        }
        return page;
    }
}
