package finance.domainservice.service.wechat.impl;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import finance.core.common.enums.WeiXinMessageTemplateCodeEnum;
import finance.domain.ThirdAccountInfo;
import finance.domain.UserInfo;
import finance.domain.weixin.WeiXinMessageTemplate;
import finance.domainservice.service.wechat.WeiXinTemplateMessageSendService;
import finance.ext.api.model.WeiXinTemplateData;
import finance.ext.api.request.WeiXinTemplateMessageSendRequest;
import finance.ext.api.response.WeiXinTemplateMessageSendResponse;
import finance.ext.integration.weixin.WeiXinTemplateMessageClient;
import finance.domainservice.service.wechat.WechatService;
import finance.core.common.util.CommonUtils;
import finance.core.common.util.DateUtils;

/**
 * <p>微信消息模版</p>
 * @author lili
 * @version $Id: WeiXinTemplateMessageServiceImpl.java, v0.1 2018/10/24 11:10 AM lili Exp $
 */
@Slf4j
@Service("weiXinTemplateMessageSendService")
public class WeiXinTemplateMessageServiceImpl implements WeiXinTemplateMessageSendService {

    @Resource
    private WeiXinTemplateMessageClient weiXinTemplateMessageClient;

    @Resource
    private WechatService               wechatService;

    /**
     * 发送微信消息
     * @param userInfo  新注册用户的基本信息
     * @param thirdAccountInfo 新注册用户的父级用户的绑定信息
     * @param weiXinMessageTemplate 模版信息
     * @param parameters  参数
     */
    @Override
    public void send(UserInfo userInfo, ThirdAccountInfo thirdAccountInfo,
                     WeiXinMessageTemplate weiXinMessageTemplate, Map<String, String> parameters) {

        if (Objects.isNull(weiXinMessageTemplate.getTemplateCode())) {
            log.error("用户:{},消息模版类型为空，不能发送消息通知", userInfo.getMobileNum());
            return;
        }
        WeiXinTemplateMessageSendRequest request = buildData(userInfo, thirdAccountInfo,
            weiXinMessageTemplate, parameters);
        WeiXinTemplateMessageSendResponse response = weiXinTemplateMessageClient
            .sendMessage(request);
        if ("0".equals(response.getErrcode())) {
            log.info("open_id:{},微信模版消息发送成功", request.getTouser());
        } else {
            log.error("open_id:{},微信模版消息发送失败:{}", request.getTouser(), response.getErrmsg());
        }
    }

    private WeiXinTemplateMessageSendRequest buildData(UserInfo userInfo,
                                                       ThirdAccountInfo thirdAccountInfo,
                                                       WeiXinMessageTemplate weiXinMessageTemplate,
                                                       Map<String, String> parameters) {
        WeiXinTemplateMessageSendRequest request = new WeiXinTemplateMessageSendRequest();

        request.setTemplate_id(weiXinMessageTemplate.getWxTemplateId());
        request.setUrl(weiXinMessageTemplate.getRedirectUrl());
        request.setTouser(thirdAccountInfo.getOpenId());
        Map<String, WeiXinTemplateData> data = Maps.newHashMap();
        switch (weiXinMessageTemplate.getTemplateCode()) {
            case FIRST_INVITE_NOTICE:
                data.put("first", WeiXinTemplateData.builder().value("恭喜您，您的团队新增一个一级成员")
                    .color("#0000ff").build());
                break;
            case FIRST_SUBSCRIBE_NOTICE:
                data.put("first", WeiXinTemplateData.builder().value("恭喜您，通过分享链接加入金榕家！")
                    .color("#0000ff").build());
                data.put("keyword1", WeiXinTemplateData.builder().value(parameters.get("nickName"))
                    .color("#0000ff").build());
                data.put("keyword2",
                    WeiXinTemplateData.builder()
                        .value(DateUtils.format(new Date(), DateUtils.LONG_WEB_FORMAT))
                        .color("#0000ff").build());
                data.put("remark", WeiXinTemplateData.builder().value("您如果取消关注，会影响邀请人的奖励哦！您要慎重哦！")
                    .color("#0000ff").build());
                break;
            case SECOND_INVITE_NOTICE:
                data.put("first", WeiXinTemplateData.builder().value("恭喜您，您的团队新增一个二级成员")
                    .color("#0000ff").build());
                break;
            case BROKERAGE_ARRIVAL_NOTICE:
                data.put("first",
                    WeiXinTemplateData.builder().value("恭喜您，获得了一笔新的佣金").color("#0000ff").build());
                data.put("keyword1", WeiXinTemplateData.builder()
                    .value(parameters.get("reward_amount")).color("#0000ff").build());
                data.put("keyword2", WeiXinTemplateData.builder()
                    .value(parameters.get("release_time")).color("#0000ff").build());
                data.put("remark",
                    WeiXinTemplateData.builder().value("查看详情，赶快提现吧").color("#0000ff").build());
                break;
            default:
        }
        Set<WeiXinMessageTemplateCodeEnum> templateCodeEnumSet = Sets.newHashSet(
            WeiXinMessageTemplateCodeEnum.FIRST_INVITE_NOTICE,
            WeiXinMessageTemplateCodeEnum.SECOND_INVITE_NOTICE);
        if (templateCodeEnumSet.contains(weiXinMessageTemplate.getTemplateCode())) {
            data.put("keyword1",
                WeiXinTemplateData.builder()
                    .value(CommonUtils.mobileEncrypt(userInfo.getMobileNum())).color("#0000ff")
                    .build());
            data.put("keyword2", WeiXinTemplateData.builder()
                .value(String.valueOf(userInfo.getId())).color("#0000ff").build());
            data.put("keyword3",
                WeiXinTemplateData.builder()
                    .value(DateUtils.format(userInfo.getCreateTime(), DateUtils.LONG_WEB_FORMAT))
                    .color("#0000ff").build());
            data.put("remark", WeiXinTemplateData.builder().value("【热门活动】让新成员参加活动，拿25元返利")
                .color("#0000ff").build());
        }
        request.setData(data);
        request.setAccessToken(wechatService.getWechatPubAccessToken());
        return request;
    }
}
