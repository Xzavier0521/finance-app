package cn.zhishush.finance.domainservice.service.wechat.impl;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import cn.zhishush.finance.core.common.enums.WeChatPubGlobalReturnCodeEnum;
import cn.zhishush.finance.core.common.enums.WeiXinMessageTemplateCodeEnum;
import cn.zhishush.finance.core.common.util.CommonUtils;
import cn.zhishush.finance.core.common.util.DateUtils;
import cn.zhishush.finance.domain.user.ThirdAccountInfo;
import cn.zhishush.finance.domain.user.UserInfo;
import cn.zhishush.finance.domain.weixin.WeiXinMessageTemplate;
import cn.zhishush.finance.domainservice.service.wechat.WechatService;
import cn.zhishush.finance.domainservice.service.wechat.WeiXinTemplateMessageSendService;
import cn.zhishush.finance.ext.api.model.WeiXinTemplateData;
import cn.zhishush.finance.ext.api.request.WeiXinTemplateMessageSendRequest;
import cn.zhishush.finance.ext.api.response.WeiXinTemplateMessageSendResponse;
import cn.zhishush.finance.ext.integration.weixin.WeiXinTemplateMessageClient;

/**
 * <p>微信消息模版 </p>
 * 
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
     * @param userInfo 新注册用户的基本信息
     * @param thirdAccountInfo 新注册用户的父级用户的绑定信息
     * @param weiXinMessageTemplate 模版信息
     * @param parameters 参数
     *
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
        if (WeChatPubGlobalReturnCodeEnum.REQUEST_SUCCESSFUL == WeChatPubGlobalReturnCodeEnum
            .getByCode(response.getErrcode())) {
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
                data.put("first",
                    WeiXinTemplateData.builder().value("欢迎您，关注金榕家！").color("#0000ff").build());
                data.put("keyword1", WeiXinTemplateData.builder().value(parameters.get("nickName"))
                    .color("#0000ff").build());
                data.put("keyword2",
                    WeiXinTemplateData.builder()
                        .value(DateUtils.format(new Date(), DateUtils.LONG_WEB_FORMAT))
                        .color("#0000ff").build());
                data.put("remark", WeiXinTemplateData.builder().value("现在办理信用卡奖励升级了，90%佣金，赶快去办理！")
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
            case SEND_COIN_NOTICE:
                data.put("first",
                    WeiXinTemplateData.builder().value(MessageFormat.format("您尾号{0}的账户最新的金币变动信息",
                        userInfo.getMobileNum().substring(7, 11))).color("#0000ff").build());
                data.put("keyword1",
                    WeiXinTemplateData.builder().value(
                        DateUtils.getFormatDateStr(LocalDateTime.now(), DateUtils.LONG_WEB_FORMAT))
                        .color("#0000ff").build());
                data.put("keyword2",
                    WeiXinTemplateData.builder().value("拼手速红包雨").color("#0000ff").build());
                data.put("keyword3",
                    WeiXinTemplateData.builder()
                        .value(MessageFormat.format("{0}金币", parameters.get("coinNum")))
                        .color("#0000ff").build());
                data.put("remark", WeiXinTemplateData.builder().value("获得金币，可免费抓娃娃！若需帮助请联系客服。")
                    .color("#0000ff").build());
                break;
            case RED_ENVELOPE_RAIN_INVITE_REWARD_NOTICE:
                data.put("first",
                    WeiXinTemplateData.builder().value(MessageFormat.format("您尾号{0}的账户最新的金币变动信息",
                        userInfo.getMobileNum().substring(7, 11))).color("#0000ff").build());
                data.put("keyword1",
                    WeiXinTemplateData.builder().value(
                        DateUtils.getFormatDateStr(LocalDateTime.now(), DateUtils.LONG_WEB_FORMAT))
                        .color("#0000ff").build());
                data.put("keyword2",
                    WeiXinTemplateData.builder().value("邀请好友参加，红包雨活动").color("#0000ff").build());
                data.put("keyword3",
                    WeiXinTemplateData.builder()
                        .value(MessageFormat.format("{0}金币", parameters.get("coinNum")))
                        .color("#0000ff").build());
                data.put("remark", WeiXinTemplateData.builder().value("获得金币，可免费抓娃娃！若需帮助请联系客服。")
                    .color("#0000ff").build());
                break;
            case RED_ENVELOPE_RAIN_NOTICE:
                buildRedEnvelopeRainData(data, parameters);
                break;
            case CUSTOMER_MESSAGE_NOTICE:
                // 客户留言通知
                data.put("first",
                    WeiXinTemplateData.builder().value(MessageFormat.format(
                        "         你的好友{0}通知你，金榕家佣金升级到90%了，奖励高到你难以想象，还是当天返现！（金榕家老板说：我就不信还有谁比我返现更快！）",
                        parameters.get("parentNickName"))).color("#0000ff").build());
                data.put("keyword1", WeiXinTemplateData.builder()
                    .value(parameters.get("parentNickName")).color("#0000ff").build());
                data.put("keyword2", WeiXinTemplateData.builder()
                    .value("金榕家佣金大升级，90%佣金全部发，办卡最高可得150元").color("#0000ff").build());
                data.put("keyword3",
                    WeiXinTemplateData.builder()
                        .value(DateUtils.format(new Date(), DateUtils.LONG_WEB_FORMAT))
                        .color("#0000ff").build());
                data.put("remark", WeiXinTemplateData.builder().value(
                    "金榕家3大升级\r\n1、平台升级,1000万奖金冲刺12月\r\n2、奖励升级：返还90%金融红利\r\n3、结算升级：当天结算返现")
                    .color("#0000ff").build());
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

    private void buildRedEnvelopeRainData(Map<String, WeiXinTemplateData> data,
                                          Map<String, String> parameters) {
        data.put("first",
            WeiXinTemplateData.builder().value("榕麽麽给小主你新留言了，赶快看看吧").color("#0000ff").build());
        data.put("keyword1", WeiXinTemplateData.builder().value("榕麽麽").color("#0000ff").build());
        data.put("keyword2",
            WeiXinTemplateData.builder().value("拼手速，抢红包活动马上开始了").color("#0000ff").build());
        data.put("keyword3",
            WeiXinTemplateData.builder()
                .value(DateUtils.format(new Date(), DateUtils.LONG_WEB_FORMAT)).color("#0000ff")
                .build());
        data.put("remark", WeiXinTemplateData.builder()
            .value("拼手速，抢红包，抢的越多奖励越多，红包个数上排行榜，会有双倍奖励哦！点击赶快参加吧！").color("#0000ff").build());
    }
}
