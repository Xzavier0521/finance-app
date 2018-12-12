package cn.zhishush.finance.domainservice.service.third.impl;

import javax.annotation.Resource;

import cn.zhishush.finance.domain.product.ProductInfo;
import cn.zhishush.finance.domain.third.ThirdUnionLoginLog;
import cn.zhishush.finance.domainservice.repository.third.ThirdUnionLoginLogRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.zhishush.finance.api.model.response.BasicResponse;
import cn.zhishush.finance.core.common.enums.ReturnCode;
import cn.zhishush.finance.core.common.util.MD5Util;
import cn.zhishush.finance.core.common.util.ResponseUtils;
import cn.zhishush.finance.domain.user.UserInfo;
import cn.zhishush.finance.domainservice.service.third.YunJuHeService;
import cn.zhishush.finance.ext.api.enums.YunJuHeReturnCode;
import cn.zhishush.finance.ext.api.request.YunJuHeUnionLoginRequest;
import cn.zhishush.finance.ext.api.response.YunJuHeUnionLoginResponse;
import cn.zhishush.finance.ext.integration.yunjuhe.YunJuHeClient;

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
        yunJuHeUnionLoginRequest.setCertcode(userInfo.getMobileNum());
        String md5Msg = "deptId=" + deptId + "&userId=" + userInfo.getId() + "&realName=" + realName
                        + "&mobile=" + userInfo.getMobileNum() + "&certcode="
                        + userInfo.getMobileNum();
        yunJuHeUnionLoginRequest.setSign(MD5Util.sign(md5Msg, "", "utf-8"));
        YunJuHeUnionLoginResponse yunJuHeUnionLoginResponse = yunJuHeClient
            .unionLogin(yunJuHeUnionLoginRequest);
        ThirdUnionLoginLog thirdUnionLoginLog = buildData(userInfo, productInfo, realName,
            yunJuHeUnionLoginResponse);
        thirdUnionLoginLogRepository.save(thirdUnionLoginLog);
        if (YunJuHeReturnCode.SUCCESS == YunJuHeReturnCode
            .getByCode(String.valueOf(yunJuHeUnionLoginResponse.getCode()))) {
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
        thirdUnionLoginLog.setErrorCode(String.valueOf(response.getCode()));
        thirdUnionLoginLog.setErrorMsg(response.getMsg());
        return thirdUnionLoginLog;
    }
}
