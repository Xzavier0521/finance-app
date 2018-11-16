package finance.domainservice.service.activity.query.impl;

import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import finance.domain.activity.ActivityConfig;
import finance.domain.activity.AgentConfig;
import finance.domain.user.UserInfo;
import finance.domain.user.UserInviteInfo;
import finance.domainservice.repository.ActivityConfigRepository;
import finance.domainservice.repository.AgentConfigRepository;
import finance.domainservice.repository.UserInviteRepository;
import finance.domainservice.service.activity.query.ActivityConfigQueryService;
import finance.api.model.condition.AgentConfigQueryCondition;
import finance.core.common.constants.QueryConstants;
import finance.core.common.enums.ActivityCodeEnum;
import finance.core.common.enums.AgentLevelEnum;

/**
 * <p>推广活动配置查询</p>
 * @author lili
 * @version $Id: ActivityConfigQueryServiceImpl.java, v0.1 2018/10/11 1:33 PM lili Exp $
 */
@Slf4j
@Service("activityQueryService")
public class ActivityConfigQueryServiceImpl implements ActivityConfigQueryService {

    @Resource
    private AgentConfigRepository    agentConfigRepository;
    @Resource
    private ActivityConfigRepository activityConfigRepository;
    @Resource
    private UserInviteRepository     userInviteRepository;

    /**
     * 根据活动代码查询用户推广信息
     * @param userInfo 用户信息
     * @param activityCode 活动代码
     * @return String
     */
    @Override
    public ActivityConfig querySpreadUrlByCode(UserInfo userInfo, ActivityCodeEnum activityCode) {

        log.info("[开始查询用户推广信息],请求参数,userInfo:{},activityCode:{}", userInfo, activityCode);
        ActivityConfig activityConfig;
        if (Objects.nonNull(userInfo)) {
            Long userId = userInfo.getId();
            // 当前用户是否为代理
            activityConfig = getActivityConfig(userId, activityCode);
            if (Objects.isNull(activityConfig)) {
                // 用户非一级代理，查询用户的邀请者（用户的父级）
                UserInviteInfo userInviteInfo = userInviteRepository.queryByCondition(userId);
                if (Objects.nonNull(userInviteInfo)) {
                    Long parentUserId = userInviteInfo.getParentUserId();
                    activityConfig = getActivityConfig(parentUserId, activityCode);
                    if (Objects.isNull(activityConfig)) {
                        activityConfig = getDefaultActivityConfig(activityCode);
                    }
                } else {
                    // 返回默认url
                    activityConfig = getDefaultActivityConfig(activityCode);
                }
            }
        } else {
            log.error("查询[{}]推广url异常：用户信息为空，", activityCode.getDesc());
            // 返回默认url
            activityConfig = getDefaultActivityConfig(activityCode);
        }
        log.info("[结束查询用户推广信息],请求参数,userInfo:{},activityCode:{},返回结果:{}", userInfo, activityCode,
            activityConfig);
        return activityConfig;
    }

    private ActivityConfig getActivityConfig(Long userId, ActivityCodeEnum activityCode) {
        ActivityConfig activityConfig = null;
        List<ActivityConfig> activityConfigs;
        // 判断当前用户是否为一级代理
        AgentConfigQueryCondition queryCondition = new AgentConfigQueryCondition();
        queryCondition.setAgentId(userId);
        queryCondition.setAgentLevel(AgentLevelEnum.FIRST_LEVEL);
        queryCondition.setActivityCode(activityCode);
        List<AgentConfig> agentConfigs = agentConfigRepository.queryByCondition(queryCondition);
        if (CollectionUtils.isNotEmpty(agentConfigs)) {
            activityConfigs = activityConfigRepository.queryByCodes(userId,
                Lists.newArrayList(activityCode.getCode()));
            if (CollectionUtils.isNotEmpty(activityConfigs)) {
                activityConfig = activityConfigs.get(0);
                log.info("用户：userId:{},为一级代理,ebay推广链接为:{}", userId, activityConfig.getSpreadUrl());
            } else {
                log.info("用户：userId:{},mobileNum:{},[{}]活动推广url未配置,返回默认url",
                    activityCode.getDesc());
                // 返回默认url
                activityConfig = getDefaultActivityConfig(activityCode);
            }
        } else {
            log.info("用户userId:{}非代理", userId);
        }
        return activityConfig;
    }

    private ActivityConfig getDefaultActivityConfig(ActivityCodeEnum activityCode) {

        ActivityConfig activityConfig = null;
        List<ActivityConfig> activityConfigs;
        // 返回默认url
        activityConfigs = activityConfigRepository.queryByCodes(QueryConstants.DEFAULT_USER_ID,
            Lists.newArrayList(activityCode.getCode()));
        if (CollectionUtils.isNotEmpty(activityConfigs)) {
            activityConfig = activityConfigs.get(0);
        }
        return activityConfig;
    }

}
