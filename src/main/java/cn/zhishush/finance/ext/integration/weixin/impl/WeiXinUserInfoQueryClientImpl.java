package cn.zhishush.finance.ext.integration.weixin.impl;

import javax.annotation.Resource;

import cn.zhishush.finance.core.common.enums.CodeEnum;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.zhishush.finance.ext.api.model.WeiXinUserInfoDetail;
import cn.zhishush.finance.ext.api.request.WeiXinBatchQueryUserInfoRequest;
import cn.zhishush.finance.ext.api.response.UserInfoQueryResponse;
import cn.zhishush.finance.ext.api.response.WeiXinBatchQueryUserInfoResponse;
import cn.zhishush.finance.ext.integration.config.RetrofitHttpClient;
import cn.zhishush.finance.ext.integration.weixin.WeiXinUserInfoQueryClient;
import cn.zhishush.finance.ext.service.weixin.WeiXinUserInfoQueryService;

/**
 * <p>微信公众号用户信息</p>
 *
 * @author lili
 * @version $Id: WeiXinUserInfoQueryClientImpl.java, v0.1 2018/10/23 3:08 PM lili Exp $
 */
@Slf4j
@Service("weiXinUserInfoQueryClient")
public class WeiXinUserInfoQueryClientImpl implements WeiXinUserInfoQueryClient {
    @Value("${wechat.api.host}")
    private String             weiXinApiHost;

    @Resource
    private RetrofitHttpClient retrofitHttpClient;

    private WeiXinUserInfoQueryService create() {
        return retrofitHttpClient.build(weiXinApiHost).create(WeiXinUserInfoQueryService.class);
    }

    /**
     * 获取用户列表
     *
     * @param accessToken
     *            调用接口凭证
     * @param nextOpenid
     *            第一个拉取的OPENID，不填默认从头开始拉取
     * @return UserInfoQueryResponse
     */
    @Override
    public UserInfoQueryResponse queryUserList(String accessToken, String nextOpenid) {
        UserInfoQueryResponse response;
        log.info("[开始获取用户列表],请求参数:accessToken:{},nextOpenid:{}", accessToken, nextOpenid);
        try {

            response = create()
                .queryUserList(accessToken, StringUtils.isNotBlank(nextOpenid) ? nextOpenid : "")
                .execute().body();
            log.info("[获取用户列表],返回结果{}", response);
        } catch (final Exception e) {
            response = new UserInfoQueryResponse();
            response.setErrcode(CodeEnum.systemError.getCode());
            response.setErrmsg("[获取用户列表],异常:" + e.getMessage());
            log.error("[获取用户列表],请求参数:accessToken:{},nextOpenid:{},异常信息:{}", accessToken, nextOpenid,
                ExceptionUtils.getStackTrace(e));
        }
        log.info("[结束获取用户列表],请求参数:accessToken:{},nextOpenid:{},返回结果:{}", accessToken, nextOpenid,
            response);
        return response;
    }

    /**
     * 获取用户基本信息(UnionID机制)
     * 
     * @param accessToken
     *            调用接口凭证
     * @param openId
     *            普通用户的标识，对当前公众号唯一
     * @return WeiXinUserInfoDetail
     */
    @Override
    public WeiXinUserInfoDetail queryUserInfo(String accessToken, String openId) {
        WeiXinUserInfoDetail response;
        log.info("[开始获取用户基本信息],请求参数:accessToken:{},nextOpenid:{}", accessToken, openId);
        try {
            response = create().queryUserInfo(accessToken, openId, "zh_CN").execute().body();
            log.info("[获取用户基本信息],返回结果{}", response);
        } catch (final Exception e) {
            response = new WeiXinUserInfoDetail();
            response.setErrcode(CodeEnum.systemError.getCode());
            response.setErrmsg("[获取用户基本信息],异常:" + e.getMessage());
            log.error("[获取用户基本信息],请求参数:accessToken:{},nextOpenid:{},异常信息:{}", accessToken, openId,
                ExceptionUtils.getStackTrace(e));
        }
        log.info("[结束获取用户基本信息],请求参数:accessToken:{},nextOpenid:{},返回结果:{}", accessToken, openId,
            response);
        return response;
    }

    /**
     * 批量获取用户基本信息
     *
     * @param request
     *            请求参数
     * @return List<WeiXinUserInfoDetail>
     */
    @Override
    public WeiXinBatchQueryUserInfoResponse batchQueryUserInfo(WeiXinBatchQueryUserInfoRequest request) {

        WeiXinBatchQueryUserInfoResponse response;
        log.info("[开始批量获取用户基本信息],请求参数:{}", request);
        try {
            response = create().batchQueryUserInfo(request.getAccessToken(), request).execute()
                .body();
            log.info("[批量获取用户基本信息],返回结果{}", response);
        } catch (final Exception e) {
            response = new WeiXinBatchQueryUserInfoResponse();
            response.setErrcode(CodeEnum.systemError.getCode());
            response.setErrmsg("[批量获取用户基本信息],异常:" + e.getMessage());
            log.error("[批量获取用户基本信息],请求参数:{},异常信息:{}", request, ExceptionUtils.getStackTrace(e));
        }
        log.info("[结束批量获取用户基本信息],请求参数:{},返回结果:{}", request, response);
        return response;
    }
}
