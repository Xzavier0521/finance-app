package cn.zhishush.finance.domainservice.service.wechat.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.Resource;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.response.BasicResponse;
import cn.zhishush.finance.core.common.constants.CommonConstant;
import cn.zhishush.finance.core.common.enums.ReturnCode;
import cn.zhishush.finance.core.common.enums.WeChatSendStatusEnum;
import cn.zhishush.finance.core.common.enums.WeiXinMessageTemplateCodeEnum;
import cn.zhishush.finance.core.common.util.ResponseUtils;
import cn.zhishush.finance.domain.user.ThirdAccountInfo;
import cn.zhishush.finance.domain.user.UserInfo;
import cn.zhishush.finance.domain.weixin.WeiXinInviteInfo;
import cn.zhishush.finance.domain.weixin.WeiXinMessageTemplate;
import cn.zhishush.finance.domainservice.repository.weixin.WeiXinInviteInfoRepository;
import cn.zhishush.finance.domainservice.repository.weixin.WeiXinMessageTemplateRepository;
import cn.zhishush.finance.domainservice.service.wechat.CustomerMessageNotificationSendService;
import cn.zhishush.finance.domainservice.service.wechat.WechatService;
import cn.zhishush.finance.domainservice.service.wechat.WeiXinTemplateMessageSendService;
import cn.zhishush.finance.ext.api.model.WeiXinUserInfoDetail;
import cn.zhishush.finance.ext.integration.weixin.WeiXinUserInfoQueryClient;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

import cn.zhishush.finance.api.model.condition.WeiXinInviteInfoQueryCondition;

/**
 * <p>客户留言通知</p>
 *
 * @author lili
 * @version 1.0: CustomerMessageNotificationSendServiceImpl.java, v0.1 2018/12/4 3:31 PM PM lili Exp $
 */
@Slf4j
@Service("customerMessageNotificationSendService")
public class CustomerMessageNotificationSendServiceImpl implements
        CustomerMessageNotificationSendService {

    @Resource
    private WechatService wechatService;
    @Resource
    private WeiXinUserInfoQueryClient weiXinUserInfoQueryClient;
    @Resource
    private ThreadPoolExecutor               threadPoolExecutor;
    @Resource
    private WeiXinInviteInfoRepository weiXinInviteInfoRepository;
    @Resource
    private WeiXinMessageTemplateRepository weiXinMessageTemplateRepository;
    @Resource
    private WeiXinTemplateMessageSendService weiXinTemplateMessageSendService;

    @Override
    public void process() {

        // 消息模版
        WeiXinMessageTemplate weiXinMessageTemplate = weiXinMessageTemplateRepository
            .query(WeiXinMessageTemplateCodeEnum.CUSTOMER_MESSAGE_NOTICE.getCode());
        WeiXinInviteInfoQueryCondition condition = new WeiXinInviteInfoQueryCondition();
        condition.setActivityCode(CommonConstant.CUSTOMER_MESSAGE_ACTIVITY_CODE);
        condition.setIsSend(WeChatSendStatusEnum.UN_SEND);
        condition.setPageSize(20);
        condition.setCurrentPage(1);
        Page<WeiXinInviteInfo> page = weiXinInviteInfoRepository.query4Page(condition);
        log.info("page:{}", page);
        List<WeiXinInviteInfo> weiXinInviteInfoList;
        if (page.getTotalPage() > 0) {
            weiXinInviteInfoList = page.getDataList();
            if (CollectionUtils.isEmpty(weiXinInviteInfoList)) {
                batchSend(weiXinInviteInfoList, weiXinMessageTemplate);
            }
            Long totalPage = page.getTotalPage();
            while (totalPage > 0) {
                page = weiXinInviteInfoRepository.query4Page(condition);
                totalPage = page.getTotalPage();
                weiXinInviteInfoList = page.getDataList();
                batchSend(weiXinInviteInfoList, weiXinMessageTemplate);
            }
        } else {
            log.info("查询无记录");
        }

    }

    private BasicResponse batchSend(List<WeiXinInviteInfo> weiXinInviteInfoList,
                                    WeiXinMessageTemplate weiXinMessageTemplate) {

        weiXinInviteInfoList.forEach(weiXinInviteInfo -> send(weiXinInviteInfo, weiXinMessageTemplate));
        return ResponseUtils.buildResp(ReturnCode.SUCCESS);
    }

    private void send(WeiXinInviteInfo weiXinInviteInfo,
                      WeiXinMessageTemplate weiXinMessageTemplate) {
        log.info("[客户留言通知]");
        String token = wechatService.getWechatPubAccessToken();
        UserInfo userInfo = new UserInfo();
        userInfo.setId(weiXinInviteInfo.getUserId());
        ThirdAccountInfo thirdAccountInfo = new ThirdAccountInfo();
        thirdAccountInfo.setOpenId(weiXinInviteInfo.getOpenId());
        Map<String, String> parameters = Maps.newHashMap();
        String parentNickName;
        if (StringUtils.isNotBlank(weiXinInviteInfo.getParentOpenId())) {
            WeiXinUserInfoDetail weiXinUserInfoDetail = weiXinUserInfoQueryClient
                .queryUserInfo(token, weiXinInviteInfo.getParentOpenId());
            if (Objects.nonNull(weiXinUserInfoDetail)) {
                parentNickName = weiXinUserInfoDetail.getNickname();
            } else {
                parentNickName = CommonConstant.DEFAULT_WE_CHAT_NUMBER;
            }
        } else {
            parentNickName = CommonConstant.DEFAULT_WE_CHAT_NUMBER;
        }
        parameters.put("parentNickName", parentNickName);
        weiXinTemplateMessageSendService.send(userInfo, thirdAccountInfo, weiXinMessageTemplate,
            parameters);
        // 更新状态
        weiXinInviteInfo.setIsSend(WeChatSendStatusEnum.HAS_SEND);
        weiXinInviteInfoRepository.update(weiXinInviteInfo);

    }
}
