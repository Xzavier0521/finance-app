package finance.domainservice.service.wechat.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

import finance.api.model.base.Page;
import finance.api.model.condition.WeiXinInviteInfoQueryCondition;
import finance.api.model.response.BasicResponse;
import finance.core.common.constants.CommonConstant;
import finance.core.common.enums.ReturnCode;
import finance.core.common.enums.WeChatSendStatusEnum;
import finance.core.common.enums.WeiXinMessageTemplateCodeEnum;
import finance.core.common.util.ConcurrentUtils;
import finance.core.common.util.ResponseUtils;
import finance.domain.user.ThirdAccountInfo;
import finance.domain.user.UserInfo;
import finance.domain.weixin.WeiXinInviteInfo;
import finance.domain.weixin.WeiXinMessageTemplate;
import finance.domainservice.repository.WeiXinInviteInfoRepository;
import finance.domainservice.repository.WeiXinMessageTemplateRepository;
import finance.domainservice.service.wechat.CustomerMessageNotificationSendService;
import finance.domainservice.service.wechat.WechatService;
import finance.domainservice.service.wechat.WeiXinTemplateMessageSendService;
import finance.ext.api.model.WeiXinUserInfoDetail;
import finance.ext.integration.weixin.WeiXinUserInfoQueryClient;

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
    private WechatService                    wechatService;
    @Resource
    private WeiXinUserInfoQueryClient        weiXinUserInfoQueryClient;
    @Resource
    private ThreadPoolExecutor               threadPoolExecutor;
    @Resource
    private WeiXinInviteInfoRepository       weiXinInviteInfoRepository;
    @Resource
    private WeiXinMessageTemplateRepository  weiXinMessageTemplateRepository;
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
        condition.setPageSize(10);
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
        List<CompletableFuture<BasicResponse>> completableFutures = weiXinInviteInfoList.stream()
            .map(weiXinInviteIn -> CompletableFuture.supplyAsync(() -> {
                send(weiXinInviteIn, weiXinMessageTemplate);
                return ResponseUtils.buildResp(ReturnCode.SUCCESS);
            }, threadPoolExecutor)).collect(Collectors.toList());
        return ConcurrentUtils.getExecuteResult(completableFutures);
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
        String parentNickName = weiXinInviteInfo.getParentNickName();
        if (StringUtils.isBlank(parentNickName)) {
            WeiXinUserInfoDetail weiXinUserInfoDetail = weiXinUserInfoQueryClient
                .queryUserInfo(token, weiXinInviteInfo.getParentOpenId());
            parentNickName = weiXinUserInfoDetail.getNickname();
        }
        parameters.put("parentNickName", parentNickName);
        weiXinTemplateMessageSendService.send(userInfo, thirdAccountInfo, weiXinMessageTemplate,
            parameters);
        // 更新状态
        weiXinInviteInfo.setIsSend(WeChatSendStatusEnum.HAS_SEND);
        weiXinInviteInfoRepository.update(weiXinInviteInfo);

    }
}
