package finance.domainservice.service.third.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import finance.api.model.response.BasicResponse;
import finance.core.common.enums.ReturnCode;
import finance.core.common.util.MD5Util;
import finance.core.common.util.ResponseUtils;
import finance.domain.product.ProductInfo;
import finance.domain.third.ThirdUnionLoginLog;
import finance.domain.user.UserInfo;
import finance.domainservice.repository.ThirdUnionLoginLogRepository;
import finance.domainservice.service.third.YunJuHeService;
import finance.ext.api.enums.YunJuHeReturnCode;
import finance.ext.api.request.YunJuHeUnionLoginRequest;
import finance.ext.api.response.YunJuHeUnionLoginResponse;
import finance.ext.integration.yunjuhe.YunJuHeClient;

/**
 * <p>云聚合联合登陆</p>
 *
 * @author lili
 * @version 1.0: YunJuHeServiceImpl.java, v0.1 2018/11/28 8:00 PM PM lili Exp $
 */
@Service("yunJuHeService")
public class YunJuHeServiceImpl implements YunJuHeService {

    @Value("${yunjuhe.service.deptId}")
    private String                       deptId;
    @Resource
    private YunJuHeClient                yunJuHeClient;

    @Resource
    private ThirdUnionLoginLogRepository thirdUnionLoginLogRepository;

    @Override
    public BasicResponse unionLogin(UserInfo userInfo, String realName, ProductInfo productInfo) {

        BasicResponse response;
        YunJuHeUnionLoginRequest yunJuHeUnionLoginRequest = new YunJuHeUnionLoginRequest();
        yunJuHeUnionLoginRequest.setDeptId(deptId);
        yunJuHeUnionLoginRequest.setRealName(realName);
        yunJuHeUnionLoginRequest.setUserId(String.valueOf(userInfo.getId()));
        yunJuHeUnionLoginRequest.setMobile(userInfo.getMobileNum());
        String md5Msg = "deptId=" + deptId + "&userId=" + userInfo.getId() + "&realName=" + realName
                        + "&mobile=" + userInfo.getMobileNum() + "&certcode=";
        yunJuHeUnionLoginRequest.setSign(MD5Util.sign(md5Msg, "", "utf-8"));
        YunJuHeUnionLoginResponse yunJuHeUnionLoginResponse = yunJuHeClient
            .unionLogin(yunJuHeUnionLoginRequest);
        ThirdUnionLoginLog thirdUnionLoginLog = buildData(userInfo, productInfo, realName,
            yunJuHeUnionLoginResponse);
        thirdUnionLoginLogRepository.save(thirdUnionLoginLog);
        if (YunJuHeReturnCode.SUCCESS == YunJuHeReturnCode
            .getByCode(yunJuHeUnionLoginResponse.getCode())) {
            response = ResponseUtils.buildResp(ReturnCode.SUCCESS,
                yunJuHeUnionLoginResponse.getData());
        } else {
            response = ResponseUtils.buildResp(ReturnCode.SYSTEM_ERROR,
                yunJuHeUnionLoginResponse.getMsg());
        }
        return response;
    }

    private ThirdUnionLoginLog buildData(UserInfo userInfo, ProductInfo productInfo,
                                         String realName, YunJuHeUnionLoginResponse response) {
        ThirdUnionLoginLog thirdUnionLoginLog = new ThirdUnionLoginLog();
        thirdUnionLoginLog.setUserId(userInfo.getId());
        thirdUnionLoginLog.setMobileNum(userInfo.getMobileNum());
        thirdUnionLoginLog.setProductCode(productInfo.getProductCode());
        thirdUnionLoginLog.setProductName(productInfo.getProductName());
        thirdUnionLoginLog.setRealName(realName);
        thirdUnionLoginLog.setThirdType("yunjuhe");
        thirdUnionLoginLog.setDeptId(deptId);
        thirdUnionLoginLog.setThirdUserId(response.getData());
        thirdUnionLoginLog.setErrorCode(response.getCode());
        thirdUnionLoginLog.setErrorMsg(response.getMsg());
        return thirdUnionLoginLog;
    }
}
