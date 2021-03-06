package cn.zhishush.finance.domainservice.service.wechat.impl;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import cn.zhishush.finance.domain.weixin.WeiXinInviteInfo;
import cn.zhishush.finance.domainservice.repository.third.ThirdAccountInfoRepository;
import cn.zhishush.finance.domainservice.repository.user.UserInviteInfoRepository;
import cn.zhishush.finance.domainservice.repository.weixin.WeiXinInviteInfoRepository;
import cn.zhishush.finance.domainservice.service.wechat.WeChatInviteInfoSynchronizeService;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.zhishush.finance.api.model.response.BasicResponse;
import cn.zhishush.finance.core.common.constants.CommonConstant;
import cn.zhishush.finance.core.common.enums.ReturnCode;
import cn.zhishush.finance.core.common.enums.WeChatSendStatusEnum;
import cn.zhishush.finance.core.common.util.ConcurrentUtils;
import cn.zhishush.finance.core.common.util.ResponseUtils;
import cn.zhishush.finance.domain.user.ThirdAccountInfo;
import cn.zhishush.finance.domain.user.UserInviteInfo;
import cn.zhishush.finance.domainservice.service.wechat.WechatService;
import cn.zhishush.finance.ext.api.response.UserInfoQueryResponse;
import cn.zhishush.finance.ext.integration.weixin.WeiXinUserInfoQueryClient;

/**
 * <p>微信用户邀请关系数据同步</p>
 *
 * @author lili
 * @version 1.0: WeChatInviteInfoSynchronizeServiceImpl.java, v0.1 2018/12/3 8:32 PM lili Exp $
 */
@Slf4j
@Service("weChatInviteInfoSynchronizeService")
public class WeChatInviteInfoSynchronizeServiceImpl implements WeChatInviteInfoSynchronizeService {

    private static final int           BATCH_SIZE = 20;

    @Resource
    private ThreadPoolExecutor         threadPoolExecutor;
    @Resource
    private WechatService              wechatService;
    @Resource
    private WeiXinUserInfoQueryClient  weiXinUserInfoQueryClient;
    @Resource
    private WeiXinInviteInfoRepository weiXinInviteInfoRepository;

    @Resource
    private UserInviteInfoRepository userInviteInfoRepository;
    @Resource
    private ThirdAccountInfoRepository thirdAccountInfoRepository;

    @Override
    public void process() {
        String accessToken = wechatService.getWechatPubAccessToken();
        UserInfoQueryResponse response = weiXinUserInfoQueryClient.queryUserList(accessToken, "");
        if (Objects.isNull(response)) {
            return;
        }
        int total = response.getTotal();
        log.info("[总记录数:{}", total);
        if (total == 0) {
            return;
        }
        List<String> openIds = response.getData().getOpenid();
        handle(openIds);
        while (StringUtils.isNotBlank(response.getNext_openid())) {
            response = weiXinUserInfoQueryClient.queryUserList(accessToken,
                response.getNext_openid());
            if (Objects.isNull(response)) {
                return;
            }
            if (Objects.isNull(response.getData())) {
                return;
            }
            if (CollectionUtils.isNotEmpty(response.getData().getOpenid())) {
                openIds = response.getData().getOpenid();
                handle(openIds);
            }
        }
        //
    }

    private void handle(List<String> openIds) {
        int totalPage = ((openIds.size() % BATCH_SIZE == 0) ? (openIds.size() / BATCH_SIZE)
            : (openIds.size() / BATCH_SIZE + 1));
        int failureCount = 0;
        for (int pageNum = 1; pageNum <= totalPage; pageNum++) {
            BasicResponse result = processBatch(pageNum, totalPage, openIds);
            if (!result.isSuccess()) {
                failureCount++;
            }
        }
    }

    private BasicResponse processBatch(int pageNum, int totalPage, List<String> openIds) {
        List<CompletableFuture<BasicResponse>> completableFutures;
        log.info("处理第[{}]批", pageNum);
        // 开始索引
        int startIndex = (pageNum - 1) * BATCH_SIZE;
        // 结束索引
        int endIndex = pageNum < totalPage ? pageNum * BATCH_SIZE : openIds.size();
        // 批处理的openId列表
        List<String> openIdSubList = Lists.newArrayList(openIds.subList(startIndex, endIndex));
        // 异步执行任务
        completableFutures = openIdSubList.stream()
            .map(openId -> CompletableFuture.supplyAsync(() -> execute(openId), threadPoolExecutor))
            .collect(Collectors.toList());
        // 获取异步执行结果
        BasicResponse response = ConcurrentUtils.getExecuteResult(completableFutures);
        log.info("openId列表:[{}],执行结果:{}", openIdSubList, response);
        return response;
    }

    private BasicResponse execute(String openId) {
        // open_id 绑定关系
        ThirdAccountInfo thirdAccountInfo = thirdAccountInfoRepository.queryByOenId(openId);
        String nickName = "";
        WeiXinInviteInfo weiXinInviteInfo = weiXinInviteInfoRepository
            .query(CommonConstant.CUSTOMER_MESSAGE_ACTIVITY_CODE, openId);
        if (Objects.nonNull(weiXinInviteInfo)) {
            return ResponseUtils.buildResp(ReturnCode.SUCCESS);
        }
        if (Objects.isNull(thirdAccountInfo)) {
            weiXinInviteInfoRepository.save(WeiXinInviteInfo.builder().openId(openId)
                .nickName(nickName).activityCode(CommonConstant.CUSTOMER_MESSAGE_ACTIVITY_CODE)
                .parentNickName(CommonConstant.DEFAULT_WE_CHAT_NUMBER)
                .isSend(WeChatSendStatusEnum.UN_SEND).build());
            return ResponseUtils.buildResp(ReturnCode.SUCCESS);
        }
        // 邀请关系
        UserInviteInfo userInviteInfo = userInviteInfoRepository
            .queryByCondition(thirdAccountInfo.getUserId());
        if (Objects.isNull(userInviteInfo)) {
            weiXinInviteInfoRepository.save(WeiXinInviteInfo.builder().openId(openId)
                .nickName(nickName).activityCode(CommonConstant.CUSTOMER_MESSAGE_ACTIVITY_CODE)
                .userId(thirdAccountInfo.getUserId())
                .parentNickName(CommonConstant.DEFAULT_WE_CHAT_NUMBER)
                .isSend(WeChatSendStatusEnum.UN_SEND).build());
            return ResponseUtils.buildResp(ReturnCode.SUCCESS);
        }
        // 查询上级是否绑定open_id
        ThirdAccountInfo parentThirdAccountInfo = thirdAccountInfoRepository
            .queryByCondition(userInviteInfo.getParentUserId());
        if (Objects.isNull(parentThirdAccountInfo)) {
            weiXinInviteInfoRepository.save(WeiXinInviteInfo.builder().openId(openId)
                .nickName(nickName).activityCode(CommonConstant.CUSTOMER_MESSAGE_ACTIVITY_CODE)
                .userId(thirdAccountInfo.getUserId()).parentUserId(userInviteInfo.getParentUserId())
                .isSend(WeChatSendStatusEnum.UN_SEND).build());
            return ResponseUtils.buildResp(ReturnCode.SUCCESS);
        }
        // 查询上级是否关注微信公众号
        weiXinInviteInfoRepository.save(WeiXinInviteInfo.builder().openId(openId).nickName(nickName)
            .activityCode(CommonConstant.CUSTOMER_MESSAGE_ACTIVITY_CODE)
            .userId(thirdAccountInfo.getUserId()).parentUserId(userInviteInfo.getParentUserId())
            .parentOpenId(parentThirdAccountInfo.getOpenId()).parentNickName("")
            .isSend(WeChatSendStatusEnum.UN_SEND).build());
        return ResponseUtils.buildResp(ReturnCode.SUCCESS);
    }
}
