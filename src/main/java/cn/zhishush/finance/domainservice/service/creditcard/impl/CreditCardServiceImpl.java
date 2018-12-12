package cn.zhishush.finance.domainservice.service.creditcard.impl;

import java.util.Objects;

import javax.annotation.Resource;

import cn.zhishush.finance.api.model.response.BasicResponse;
import cn.zhishush.finance.core.common.enums.ReturnCode;
import cn.zhishush.finance.core.common.util.ResponseUtils;
import cn.zhishush.finance.domain.user.UserInfo;
import org.springframework.stereotype.Service;

import cn.zhishush.finance.domain.creditcard.CreditCardApplyInfo;
import cn.zhishush.finance.domainservice.repository.third.impl.product.CreditCardApplyInfoRepository;
import cn.zhishush.finance.domainservice.service.creditcard.CreditCardService;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: CreditCardServiceImpl.java, v0.1 2018/11/29 5:43 PM PM lili Exp $
 */

@Service("creditCardService")
public class CreditCardServiceImpl implements CreditCardService {
    @Resource
    private CreditCardApplyInfoRepository creditCardApplyInfoRepository;

    @Override
    public BasicResponse saveApplyInfo(UserInfo userInfo, String productCode) {
        CreditCardApplyInfo creditCardApplyInfo = creditCardApplyInfoRepository
            .query(userInfo.getId(), productCode);
        if (Objects.isNull(creditCardApplyInfo)) {
            CreditCardApplyInfo record = new CreditCardApplyInfo();
            record.setUserId(userInfo.getId());
            record.setProductCode(productCode);
            creditCardApplyInfoRepository.save(record);
        } else {
            creditCardApplyInfo.setProductCode(productCode);
            creditCardApplyInfoRepository.update(creditCardApplyInfo);
        }
        return ResponseUtils.buildResp(ReturnCode.SUCCESS);
    }
}
