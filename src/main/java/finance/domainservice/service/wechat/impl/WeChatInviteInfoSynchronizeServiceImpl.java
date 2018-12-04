package finance.domainservice.service.wechat.impl;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import finance.api.model.response.BasicResponse;
import finance.core.common.constants.CommonConstant;
import finance.core.common.enums.ReturnCode;
import finance.core.common.enums.WeChatSendStatusEnum;
import finance.core.common.util.ConcurrentUtils;
import finance.core.common.util.ResponseUtils;
import finance.domain.user.ThirdAccountInfo;
import finance.domain.user.UserInviteInfo;
import finance.domain.weixin.WeiXinInviteInfo;
import finance.domainservice.repository.ThirdAccountInfoRepository;
import finance.domainservice.repository.UserInviteInfoRepository;
import finance.domainservice.repository.WeiXinInviteInfoRepository;
import finance.domainservice.service.wechat.WeChatInviteInfoSynchronizeService;
import finance.domainservice.service.wechat.WechatService;
import finance.ext.api.model.WeiXinUserInfoDetail;
import finance.ext.api.response.UserInfoQueryResponse;
import finance.ext.integration.weixin.WeiXinUserInfoQueryClient;

/**
 * <p>微信用户邀请关系数据同步</p>
 *
 * @author lili
 * @version 1.0: WeChatInviteInfoSynchronizeServiceImpl.java, v0.1 2018/12/3 8:32 PM lili Exp $
 */
@Slf4j
@Service("weChatInviteInfoSynchronizeService")
public class WeChatInviteInfoSynchronizeServiceImpl implements WeChatInviteInfoSynchronizeService {

    private static final int           BATCH_SIZE = 10;

    @Resource
    private ThreadPoolExecutor         threadPoolExecutor;
    @Resource
    private WechatService              wechatService;
    @Resource
    private WeiXinUserInfoQueryClient  weiXinUserInfoQueryClient;
    @Resource
    private WeiXinInviteInfoRepository weiXinInviteInfoRepository;

    @Resource
    private UserInviteInfoRepository   userInviteInfoRepository;
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
        String token = wechatService.getWechatPubAccessToken();
        // open_id 绑定关系
        ThirdAccountInfo thirdAccountInfo = thirdAccountInfoRepository.queryByOenId(openId);
        WeiXinUserInfoDetail weiXinUserInfoDetail = weiXinUserInfoQueryClient.queryUserInfo(token,
            openId);
        String nickName = Objects.nonNull(weiXinUserInfoDetail) ? weiXinUserInfoDetail.getNickname()
            : "";
        weiXinInviteInfoRepository.delete(CommonConstant.CUSTOMER_MESSAGE_ACTIVITY_CODE, openId);
        if (Objects.isNull(thirdAccountInfo)) {
            weiXinInviteInfoRepository.save(WeiXinInviteInfo.builder().openId(openId)
                .nickName(nickName).activityCode(CommonConstant.CUSTOMER_MESSAGE_ACTIVITY_CODE)
                .isSend(WeChatSendStatusEnum.UN_SEND).build());
            return ResponseUtils.buildResp(ReturnCode.SUCCESS);
        }
        // 邀请关系
        UserInviteInfo userInviteInfo = userInviteInfoRepository
            .queryByCondition(thirdAccountInfo.getUserId());
        if (Objects.isNull(userInviteInfo)) {
            weiXinInviteInfoRepository.save(WeiXinInviteInfo.builder().openId(openId)
                .nickName(nickName).activityCode(CommonConstant.CUSTOMER_MESSAGE_ACTIVITY_CODE)
                .userId(thirdAccountInfo.getUserId()).isSend(WeChatSendStatusEnum.UN_SEND).build());
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
        WeiXinUserInfoDetail parentWeiXinUserInfoDetail = weiXinUserInfoQueryClient
            .queryUserInfo(token, parentThirdAccountInfo.getOpenId());
        if (Objects.isNull(parentWeiXinUserInfoDetail)) {
            weiXinInviteInfoRepository.save(WeiXinInviteInfo.builder().openId(openId)
                .nickName(nickName).activityCode(CommonConstant.CUSTOMER_MESSAGE_ACTIVITY_CODE)
                .userId(thirdAccountInfo.getUserId()).parentUserId(userInviteInfo.getParentUserId())
                .parentOpenId(parentThirdAccountInfo.getOpenId())
                .isSend(WeChatSendStatusEnum.UN_SEND).build());
            return ResponseUtils.buildResp(ReturnCode.SUCCESS);
        }
        //
        weiXinInviteInfoRepository.save(WeiXinInviteInfo.builder().openId(openId).nickName(nickName)
            .activityCode(CommonConstant.CUSTOMER_MESSAGE_ACTIVITY_CODE)
            .userId(thirdAccountInfo.getUserId()).parentUserId(userInviteInfo.getParentUserId())
            .parentOpenId(parentThirdAccountInfo.getOpenId())
            .nickName(parentWeiXinUserInfoDetail.getNickname()).isSend(WeChatSendStatusEnum.UN_SEND)
            .build());
        return ResponseUtils.buildResp(ReturnCode.SUCCESS);
    }
}
