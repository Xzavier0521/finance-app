package finance.domainservice.service.user.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import finance.api.model.response.ResponseResult;
import finance.core.common.constant.Constant;
import finance.core.common.enums.CodeEnum;
import finance.core.common.enums.ThirdLoginAction;
import finance.core.common.util.LogUtil;
import finance.domain.dto.WechatOpenInfoDto;
import finance.domainservice.service.login.impl.LoginServiceImpl;
import finance.domainservice.service.user.ThirdBindService;
import finance.ext.integration.weixin.WechatClient;
import finance.core.dal.dao.FinanceThirdAccountActionLogDAO;
import finance.core.dal.dao.FinanceThirdAccountInfoDAO;
import finance.core.dal.dataobject.FinanceThirdAccountActionLog;
import finance.core.dal.dataobject.FinanceThirdAccountInfo;

/**
 * @author hewenbin
 * @version v1.0 2018年8月14日 下午2:43:22 hewenbin
 */
@Service
public class ThirdBindServiceImpl implements ThirdBindService {
    private final static Logger             logger = LoggerFactory
        .getLogger(LoginServiceImpl.class);

    @Resource
    private FinanceThirdAccountInfoDAO      thirdAccountInfoMapper;
    @Resource
    private FinanceThirdAccountActionLogDAO thirdAccountActionLogMapper;
    @Resource
    private WechatClient                    wechatClient;

    @Override
    public ResponseResult<Boolean> bindUser(Long userId, String openId, String channel,
                                            String wechatPubName) {
        // 保存绑定信息
        FinanceThirdAccountInfo queryParam = new FinanceThirdAccountInfo();
        queryParam.setOpenId(openId);
        queryParam.setChannel(channel);
        queryParam.setPublicName(wechatPubName);
        FinanceThirdAccountInfo accountInfo = thirdAccountInfoMapper
            .selectOneBySelective(queryParam);
        if (accountInfo != null && accountInfo.getStatus().equals("1")) {
            // 已被绑定
            return ResponseResult.error(CodeEnum.thirdAccountExist);
        }

        queryParam = new FinanceThirdAccountInfo();
        queryParam.setUserId(userId);
        queryParam.setChannel(channel);
        queryParam.setPublicName(wechatPubName);
        accountInfo = thirdAccountInfoMapper.selectOneBySelective(queryParam);
        if (accountInfo != null) {
            if (accountInfo.getStatus().equals("1")) {
                // 已绑定，不需要重复绑定
                return ResponseResult.error(CodeEnum.thirdBinded);
            }
            if (!accountInfo.getStatus().equals("1")) {
                // 重新绑定状态和 openid
                FinanceThirdAccountInfo updateParam = new FinanceThirdAccountInfo();
                updateParam.setId(accountInfo.getId());
                updateParam.setStatus("1");
                updateParam.setOpenId(openId);
                thirdAccountInfoMapper.updateByPrimaryKeySelective(updateParam);
            }
        } else {
            accountInfo = new FinanceThirdAccountInfo();
            accountInfo.setOpenId(openId);
            accountInfo.setChannel(channel);
            accountInfo.setUserId(userId);
            accountInfo.setStatus("1");
            accountInfo.setPublicName(wechatPubName);
            thirdAccountInfoMapper.insertSelective(accountInfo);
        }

        // 记录绑定日志
        FinanceThirdAccountActionLog actionLog = new FinanceThirdAccountActionLog();
        actionLog.setActionType(ThirdLoginAction.bind.toString());
        actionLog.setChannel(channel);
        actionLog.setOpenId(openId);
        actionLog.setUserId(userId);
        actionLog.setPublicName(wechatPubName);
        thirdAccountActionLogMapper.insertSelective(actionLog);
        return ResponseResult.success(true);
    }

    @Override
    public void unBindUser(Long userId, String channel) {
        FinanceThirdAccountInfo queryParam = new FinanceThirdAccountInfo();
        queryParam.setUserId(userId);
        queryParam.setChannel(channel);
        FinanceThirdAccountInfo accountInfo = thirdAccountInfoMapper
            .selectOneBySelective(queryParam);

        if (accountInfo == null || !accountInfo.getStatus().equals("1")) {
            // 无需解绑
            return;
        }

        // 更新状态
        FinanceThirdAccountInfo updateParam = new FinanceThirdAccountInfo();
        updateParam.setId(accountInfo.getId());
        updateParam.setStatus((accountInfo.getId() * -1) + "");
        thirdAccountInfoMapper.updateByPrimaryKeySelective(updateParam);

        // 记录绑定日志
        FinanceThirdAccountActionLog actionLog = new FinanceThirdAccountActionLog();
        actionLog.setActionType(ThirdLoginAction.unbind.toString());
        actionLog.setChannel(channel);
        actionLog.setUserId(userId);
        actionLog.setOpenId(accountInfo.getOpenId());
        thirdAccountActionLogMapper.insertSelective(actionLog);
    }

    @Override
    public FinanceThirdAccountInfo queryBindAccount(String channel, String openId, Long userId) {
        FinanceThirdAccountInfo queryParam = new FinanceThirdAccountInfo();
        queryParam.setOpenId(openId);
        queryParam.setChannel(channel);
        queryParam.setUserId(userId);
        FinanceThirdAccountInfo accountInfo = thirdAccountInfoMapper
            .selectOneBySelective(queryParam);
        return accountInfo;
    }

    @Override
    public String queryOpenInfo(String channel, String code, String openId) {
        String realOpenId = null;
        switch (channel) {
            case "wechat":
                WechatOpenInfoDto openInfo = wechatClient.getOpenInfo(code,
                    Constant.THIRD_PARD_WECHAT);
                if (openInfo != null && StringUtils.isEmpty(openInfo.getErrcode())) {
                    realOpenId = openInfo.getOpenid();
                } else {
                    logger.error(LogUtil.getFormatLog(openInfo, "获取微信OPENINFO失败"));
                }
                break;
            case "wechatPub":
                WechatOpenInfoDto openInfoPub = wechatClient.getOpenInfo(code,
                    Constant.THIRD_PARD_WECHATPUB);
                if (openInfoPub != null && StringUtils.isEmpty(openInfoPub.getErrcode())) {
                    realOpenId = openInfoPub.getOpenid();
                } else {
                    logger.error(LogUtil.getFormatLog(openInfoPub, "获取微信公众号OPENINFO失败"));
                }
                break;
            case "qq":
                // XXX code就是openId
                realOpenId = openId;
                break;

            default:
                break;
        }
        return realOpenId;
    }

}
