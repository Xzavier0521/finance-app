package cn.zhishush.finance.ext.integration.yunjuhe.impl;

import javax.annotation.Resource;

import cn.zhishush.finance.ext.integration.yunjuhe.YunJuHeClient;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.zhishush.finance.ext.api.request.YunJuHeUnionLoginRequest;
import cn.zhishush.finance.ext.api.response.YunJuHeUnionLoginResponse;
import cn.zhishush.finance.ext.integration.config.RetrofitHttpClient;
import cn.zhishush.finance.ext.service.yunjuhe.YunJuHeService;

/**
 * <p>云聚合</p>
 *
 * @author lili
 * @version 1.0: YunJuHeClientImpl.java, v0.1 2018/11/28 7:16 PM PM lili Exp $
 */
@Slf4j
@Component("yunJuHeClient")
public class YunJuHeClientImpl implements YunJuHeClient {

    @Value("${yunjuhe.service.url}")
    private String             yunJuHeApiHost;

    @Resource
    private RetrofitHttpClient retrofitHttpClient;

    private YunJuHeService create() {
        return retrofitHttpClient.build(yunJuHeApiHost).create(YunJuHeService.class);
    }

    @Override
    public YunJuHeUnionLoginResponse unionLogin(YunJuHeUnionLoginRequest request) {
        YunJuHeUnionLoginResponse response;
        log.info("[开始云聚合联合登陆],请求参数:{}", request);
        try {
            response = create().unionLogin(request).execute().body();
        } catch (final Exception e) {
            response = new YunJuHeUnionLoginResponse();
            response.setCode(1);
            response.setMsg(e.getMessage());
            log.info("[云聚合联合登陆],请求参数:{},异常:{}", request, ExceptionUtils.getStackTrace(e));
        }
        log.info("[结束云聚合联合登陆],请求参数:{},返回结果:{}", request, response);
        return response;
    }
}
