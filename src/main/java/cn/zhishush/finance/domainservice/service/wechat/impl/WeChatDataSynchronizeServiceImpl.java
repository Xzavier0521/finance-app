package cn.zhishush.finance.domainservice.service.wechat.impl;

import static cn.zhishush.finance.core.common.constants.QueryConstants.DEFAULT_MAX_PAGE_SIZE;
import static cn.zhishush.finance.core.common.constants.WeChatConstant.MAX_PAGE_SIZE;
import static cn.zhishush.finance.core.common.constants.WeChatConstant.WE_CHAT_FLOW_NUM;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import cn.zhishush.finance.domainservice.repository.user.InviteOpenInfoRepository;
import cn.zhishush.finance.domainservice.repository.weixin.WeiXinMessageTemplateRepository;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cn.zhishush.finance.core.common.enums.ConcernStatusEnum;
import cn.zhishush.finance.core.common.enums.OperatorTypeEnum;
import cn.zhishush.finance.core.common.enums.WeiXinMessageTemplateCodeEnum;
import cn.zhishush.finance.domain.user.ThirdAccountInfo;
import cn.zhishush.finance.domain.weixin.WeiXinMessageTemplate;
import cn.zhishush.finance.domainservice.service.wechat.WeChatDataSynchronizeService;
import cn.zhishush.finance.domainservice.service.wechat.WechatService;
import cn.zhishush.finance.domainservice.service.wechat.WeiXinTemplateMessageSendService;
import cn.zhishush.finance.ext.api.model.WeiXinUserInfoDetail;
import cn.zhishush.finance.ext.api.response.UserInfoQueryResponse;
import cn.zhishush.finance.ext.integration.weixin.WeiXinUserInfoQueryClient;

/**
 * <p> 微信公众号用户数据同步到redis </p>
 * 
 * @author lili
 * @version $Id: WeChatDataSynchronizeServiceImpl.java, v0.1 2018/10/23 10:24 PM lili Exp $
 */
@Slf4j
@Service("weChatDataSynchronizeService")
public class WeChatDataSynchronizeServiceImpl implements WeChatDataSynchronizeService {

    @Resource
    private RedisTemplate<String, String>    redisTemplate;
    @Resource
    private WechatService                    wechatService;
    @Resource
    private WeiXinUserInfoQueryClient        weiXinUserInfoQueryClient;
    @Resource
    private InviteOpenInfoRepository inviteOpenInfoRepository;
    @Resource
    private WeiXinMessageTemplateRepository weiXinMessageTemplateRepository;
    @Resource
    private WeiXinTemplateMessageSendService weiXinTemplateMessageSendService;

    @Override
    public void process() {
        String accessToken = wechatService.getWechatPubAccessToken();
        UserInfoQueryResponse response = weiXinUserInfoQueryClient.queryUserList(accessToken, "");
        String[] openIds;
        if (Objects.nonNull(response)) {
            int total = response.getTotal();
            if (total == 0) {
                log.info("微信公众号用户数为0");
                return;
            }
            log.info("微信公众号用户数:{}", total);
            SetOperations<String, String> setOperations = redisTemplate.opsForSet();
            openIds = response.getData().getOpenid()
                .toArray(new String[response.getData().getOpenid().size()]);
            setOperations.add(WE_CHAT_FLOW_NUM, openIds);
            processSubscribeInfo(Lists.newArrayList(openIds));
            if (total <= MAX_PAGE_SIZE) {
                log.info("微信公众号用户数小于:{}", MAX_PAGE_SIZE);
                return;
            }
            log.info("微信公众号用户数大于:{}", MAX_PAGE_SIZE);
            setOperations.add(WE_CHAT_FLOW_NUM, openIds);
            while (StringUtils.isNotBlank(response.getNext_openid())) {
                response = weiXinUserInfoQueryClient.queryUserList(accessToken,
                    response.getNext_openid());
                if (Objects.nonNull(response) && Objects.nonNull(response.getData())) {
                    if (CollectionUtils.isNotEmpty(response.getData().getOpenid())) {
                        openIds = response.getData().getOpenid()
                            .toArray(new String[response.getData().getOpenid().size()]);
                    }
                }
                processSubscribeInfo(Lists.newArrayList(openIds));
                setOperations.add(WE_CHAT_FLOW_NUM, openIds);
            }

        }
    }

    /**
     * 更新微信公众号关注用户信息
     *
     * @param openId
     *            微信open_id
     */
    @Override
    public void process(String openId, OperatorTypeEnum operatorType) {
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
        switch (operatorType) {
            case ADD:
                setOperations.add(WE_CHAT_FLOW_NUM, openId);
                inviteOpenInfoRepository.batchUpdateStatusByOpenId(Lists.newArrayList(openId),
                    ConcernStatusEnum.SUBSCRIBE);
                this.sendMessage(openId);
                break;
            case DEL:
                setOperations.remove(WE_CHAT_FLOW_NUM, openId);
                // 更新关注状态
                inviteOpenInfoRepository.batchUpdateStatusByOpenId(Lists.newArrayList(openId),
                    ConcernStatusEnum.UNSUBSCRIBE);
                break;
            default:
        }

    }

    private void sendMessage(String openId) {
        try {
            // 发送模版消息
            WeiXinMessageTemplate weiXinMessageTemplate = weiXinMessageTemplateRepository
                .query(WeiXinMessageTemplateCodeEnum.FIRST_SUBSCRIBE_NOTICE.getCode());
            WeiXinUserInfoDetail weiXinUserInfoDetail = weiXinUserInfoQueryClient
                .queryUserInfo(wechatService.getWechatPubAccessToken(), openId);
            if (Objects.nonNull(weiXinUserInfoDetail)) {
                Map<String, String> parameters = Maps.newHashMap();
                parameters.put("nickName", weiXinUserInfoDetail.getNickname());
                ThirdAccountInfo thirdAccountInfo = new ThirdAccountInfo();
                thirdAccountInfo.setOpenId(openId);
                weiXinTemplateMessageSendService.send(null, thirdAccountInfo, weiXinMessageTemplate,
                    parameters);
            } else {
                log.error("open_id:{},获取微信昵称失败:{}", openId);
            }
        } catch (final Exception e) {
            log.error("[新用户关注发送模版消息],异常:{}", ExceptionUtils.getStackTrace(e));
        }

    }

    private void processSubscribeInfo(List<String> openIds) {
        int totalPage = ((openIds.size() % DEFAULT_MAX_PAGE_SIZE == 0)
            ? (openIds.size() / DEFAULT_MAX_PAGE_SIZE)
            : (openIds.size() / DEFAULT_MAX_PAGE_SIZE + 1));
        log.info("总页数:{}", totalPage);
        // 异步执行任务
        // 获取异步执行结果
        for (int pageNum = 1; pageNum <= totalPage; pageNum++) {
            processBatch(pageNum, totalPage, openIds);
        }
    }

    private void processBatch(int pageNum, int totalPage, List<String> dateList) {
        // 开始索引
        int startIndex = (pageNum - 1) * DEFAULT_MAX_PAGE_SIZE;
        // 结束索引
        int endIndex = pageNum < totalPage ? pageNum * DEFAULT_MAX_PAGE_SIZE : dateList.size();
        // 批处理的open_id列表
        List<String> dateSubList = Lists.newArrayList(dateList.subList(startIndex, endIndex));
        inviteOpenInfoRepository.batchUpdateStatusByOpenId(dateSubList,
            ConcernStatusEnum.SUBSCRIBE);
    }
}
