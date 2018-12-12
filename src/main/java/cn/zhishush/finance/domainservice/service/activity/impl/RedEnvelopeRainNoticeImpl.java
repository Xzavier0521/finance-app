package cn.zhishush.finance.domainservice.service.activity.impl;

import static cn.zhishush.finance.core.common.constants.WeChatConstant.MAX_PAGE_SIZE;

import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import cn.zhishush.finance.domainservice.repository.weixin.WeiXinMessageTemplateRepository;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

import cn.zhishush.finance.core.common.enums.WeiXinMessageTemplateCodeEnum;
import cn.zhishush.finance.domain.user.ThirdAccountInfo;
import cn.zhishush.finance.domain.weixin.WeiXinMessageTemplate;
import cn.zhishush.finance.domainservice.service.activity.RedEnvelopeRainNoticeService;
import cn.zhishush.finance.domainservice.service.wechat.WechatService;
import cn.zhishush.finance.domainservice.service.wechat.WeiXinTemplateMessageSendService;
import cn.zhishush.finance.ext.api.response.UserInfoQueryResponse;
import cn.zhishush.finance.ext.integration.weixin.WeiXinUserInfoQueryClient;

/**
 * <p>红包雨活动微信模版消息通知</p>
 * @author lili
 * @version $Id: RedEnvelopeRainNoticeImpl.java, v0.1 2018/11/26 9:29 AM lili Exp $
 */
@Slf4j
@Service("redEnvelopeRainNoticeService")
public class RedEnvelopeRainNoticeImpl implements RedEnvelopeRainNoticeService {
    @Resource
    private WechatService                    wechatService;
    @Resource
    private WeiXinUserInfoQueryClient        weiXinUserInfoQueryClient;
    @Resource
    private WeiXinMessageTemplateRepository weiXinMessageTemplateRepository;
    @Resource
    private WeiXinTemplateMessageSendService weiXinTemplateMessageSendService;

    @Override
    public void process() {
        String accessToken = wechatService.getWechatPubAccessToken();
        UserInfoQueryResponse response = weiXinUserInfoQueryClient.queryUserList(accessToken, "");
        List<String> openIds;
        if (Objects.nonNull(response)) {
            int total = response.getTotal();
            if (total == 0) {
                log.info("微信公众号用户数为0");
                return;
            }
            log.info("微信公众号用户数:{}", total);
            openIds = response.getData().getOpenid();
            sendMessage(openIds);
            if (total <= MAX_PAGE_SIZE) {
                log.info("微信公众号用户数小于:{}", MAX_PAGE_SIZE);
                return;
            }
            log.info("微信公众号用户数大于:{}", MAX_PAGE_SIZE);
            while (StringUtils.isNotBlank(response.getNext_openid())) {
                response = weiXinUserInfoQueryClient.queryUserList(accessToken,
                    response.getNext_openid());
                if (Objects.nonNull(response) && Objects.nonNull(response.getData())) {
                    if (CollectionUtils.isNotEmpty(response.getData().getOpenid())) {
                        openIds = response.getData().getOpenid();
                        sendMessage(openIds);
                    }
                }
            }

        }
    }

    private void sendMessage(List<String> openIds) {
        // 查询微信消息模版
        WeiXinMessageTemplate weiXinMessageTemplate = weiXinMessageTemplateRepository
            .query(WeiXinMessageTemplateCodeEnum.RED_ENVELOPE_RAIN_NOTICE.getCode());
        ThirdAccountInfo thirdAccountInfo;
        int count = 1;
        for (String openId : openIds) {
            thirdAccountInfo = new ThirdAccountInfo();
            thirdAccountInfo.setOpenId(openId);
            weiXinTemplateMessageSendService.send(null, thirdAccountInfo, weiXinMessageTemplate,
                Maps.newHashMap());
            log.info("发送第{}条", count);
            count++;
            try {
                Thread.sleep(100);
                log.info("休眠0.1s");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
