package cn.zhishush.finance.domainservice.service.register.impl;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javax.annotation.Resource;

import cn.zhishush.finance.core.dal.dao.account.IdCardInfoDAO;
import cn.zhishush.finance.core.dal.dao.log.UserRegisterLogDAO;
import cn.zhishush.finance.core.dal.dao.user.UserInfoDAO;
import cn.zhishush.finance.core.dal.dao.user.UserInviteInfoDAO;
import cn.zhishush.finance.core.dal.dao.user.UserRegisterChannelInfoDAO;
import cn.zhishush.finance.core.dal.dataobject.account.IdCardInfoDO;
import cn.zhishush.finance.core.dal.dataobject.user.UserInfoDO;
import cn.zhishush.finance.core.dal.dataobject.user.UserInviteInfoDO;
import cn.zhishush.finance.core.dal.dataobject.user.UserRegisterChannelInfoDO;
import cn.zhishush.finance.core.dal.dataobject.log.UserRegisterLogDO;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import cn.zhishush.finance.core.common.enums.AuthStatus;
import cn.zhishush.finance.core.common.enums.ReturnCode;
import cn.zhishush.finance.core.common.util.CommonUtils;
import cn.zhishush.finance.core.common.util.PreconditionUtils;
import cn.zhishush.finance.domain.dto.LoginParamDto;
import cn.zhishush.finance.domain.log.BarrageMessage;
import cn.zhishush.finance.domain.user.UserInfo;
import cn.zhishush.finance.domain.weixin.InviteOpenInfo;
import cn.zhishush.finance.domainservice.repository.log.BarrageMessageRepository;
import cn.zhishush.finance.domainservice.repository.user.InviteOpenInfoRepository;
import cn.zhishush.finance.domainservice.repository.user.UserInfoRepository;
import cn.zhishush.finance.domainservice.service.register.RegisterSendMessageService;
import cn.zhishush.finance.domainservice.service.register.RegisterService;
import cn.zhishush.finance.domainservice.service.trans.AccountService;

import com.google.common.collect.Maps;

/**
 * <p>用户注册</p>
 * 
 * @author lili
 * @version $Id: RegisterServiceImpl.java, v0.1 2018/11/13 1:53 PM lili Exp $
 */
@Slf4j
@Service("registerService")
public class RegisterServiceImpl implements RegisterService {

    @Resource
    private UserInfoDAO userInfoDAO;
    @Resource
    private UserInviteInfoDAO userInviteInfoDAO;
    @Resource
    private UserRegisterLogDAO registerLogMapper;
    @Resource
    private UserRegisterChannelInfoDAO registerChannelMapper;
    @Resource
    private AccountService             accountService;
    @Resource
    private IdCardInfoDAO idCardInfoMapper;
    @Resource
    private BarrageMessageRepository   barrageMessageRepository;

    @Resource
    private RegisterSendMessageService registerSendMessageService;
    @Resource
    private InviteOpenInfoRepository   inviteOpenInfoRepository;

    @Resource
    private UserInfoRepository         userInfoRepository;

    @Resource
    private TransactionTemplate        transactionTemplate;

    @Override
    public UserInfoDO registerUser(LoginParamDto paramDto) {

        UserInfoDO financeUserInfo = transactionTemplate.execute(transactionStatus -> {
            // 保存用户信息
            UserInfoDO userInfo = saveUserInfo(paramDto);
            // 保存邀请人关系
            saveInviteInfo(userInfo, paramDto);
            // 保存姓名
            saveIdCardInfo(userInfo, paramDto);
            // 记录注册日志
            saveUserRegisterLog(userInfo, paramDto);
            // 记录渠道信息
            saveUserRegisterChannelInfo(userInfo, paramDto);
            // 创建账户
            accountService.createAccountInfo(userInfo.getId());
            // 记录弹幕消息
            saveBarrageMessage(paramDto);
            return userInfo;
        });
        PreconditionUtils.checkArgument(Objects.nonNull(financeUserInfo),
            ReturnCode.USER_REGISTER_ERROR);
        // 新用户注册发送微信模版消息
        registerSendMessageService.sendMessage(financeUserInfo.getId());
        return financeUserInfo;
    }

    private UserInfoDO saveUserInfo(LoginParamDto paramDto) {
        // 生成邀请码
        String inviteCode = UUID.randomUUID().toString().replaceAll("-", "");
        UserInfoDO userInfo = new UserInfoDO();
        userInfo.setMobileNum(paramDto.getMobileNum());
        userInfo.setInviteCode(inviteCode);
        // 活动代码
        userInfo.setActivityCode(paramDto.getActivityCode());
        // 创建用户
        userInfoDAO.insertSelective(userInfo);
        return userInfo;
    }

    private void saveInviteInfo(UserInfoDO userInfo, LoginParamDto paramDto) {
        // 保存邀请人关系
        if (StringUtils.isNotBlank(paramDto.getInviteCode())) {
            // 查询邀请码对应的 UserInfo
            UserInfoDO parentUser = userInfoDAO.selectByInviteCode(paramDto.getInviteCode());
            if (Objects.isNull(parentUser)) {
                return;
            }
            UserInviteInfoDO inviteInfo = new UserInviteInfoDO();
            // 保存
            inviteInfo.setParentUserId(parentUser.getId());
            inviteInfo.setUserId(userInfo.getId());
            // 新增活动类型
            if (paramDto.getActivityType() != null) {
                inviteInfo.setInviteType(Integer.valueOf(paramDto.getActivityType()));
            }
            // 活动代码
            inviteInfo.setActivityCode(paramDto.getActivityCode());
            userInviteInfoDAO.insertSelective(inviteInfo);
            //
        } else {
            if (StringUtils.isBlank(paramDto.getOpenId())) {
                log.info("用户:{} 无open_id不生成邀请关系", paramDto.getMobileNum());
                return;
            }
            String openId = paramDto.getOpenId();
            Map<String, Object> param = Maps.newHashMap();
            param.put("openId", openId);
            List<InviteOpenInfo> inviteOpenInfoList = inviteOpenInfoRepository
                .queryByCondition(param);
            if (CollectionUtils.isEmpty(inviteOpenInfoList)) {
                log.info("根据 open_id:{} 查询绑定关系无记录", openId);
                return;
            }
            InviteOpenInfo inviteOpenInfo = inviteOpenInfoList.get(0);
            // 根据邀请码查询用户信息
            UserInfo parentUserInfo = userInfoRepository
                .queryByCondition(inviteOpenInfo.getInviteCode());
            if (Objects.isNull(parentUserInfo)) {
                log.info("invite_code:{},查询用户信息无记录", inviteOpenInfo.getInviteCode());
                return;
            }
            UserInviteInfoDO inviteInfo = new UserInviteInfoDO();
            // 保存
            inviteInfo.setParentUserId(parentUserInfo.getId());
            inviteInfo.setUserId(userInfo.getId());
            // 新增活动类型
            if (paramDto.getActivityType() != null) {
                inviteInfo.setInviteType(Integer.valueOf(paramDto.getActivityType()));
            }
            userInviteInfoDAO.insertSelective(inviteInfo);
        }
    }

    private void saveIdCardInfo(UserInfoDO userInfo, LoginParamDto paramDto) {

        if (StringUtils.isBlank(paramDto.getRealName())) {
            return;
        }
        // 保存姓名
        IdCardInfoDO idCardInfo = new IdCardInfoDO();
        idCardInfo.setUserId(userInfo.getId());
        idCardInfo.setRealName(paramDto.getRealName());
        idCardInfo.setAuthStatus(AuthStatus.not_save.getCode());
        idCardInfoMapper.insertSelective(idCardInfo);
    }

    private void saveUserRegisterLog(UserInfoDO userInfo, LoginParamDto paramDto) {
        UserRegisterLogDO registerLog = new UserRegisterLogDO();
        registerLog.setMobileNum(paramDto.getMobileNum());
        registerLog.setIp(paramDto.getIp());
        registerLog.setUserAgent(paramDto.getUserAgent());
        registerLog.setUserId(userInfo.getId());
        registerLogMapper.insertSelective(registerLog);
    }

    private void saveUserRegisterChannelInfo(UserInfoDO userInfo, LoginParamDto paramDto) {
        UserRegisterChannelInfoDO registerChannelInfo = new UserRegisterChannelInfoDO();
        BeanUtils.copyProperties(paramDto, registerChannelInfo);
        registerChannelInfo.setUserId(userInfo.getId());
        registerChannelMapper.insertSelective(registerChannelInfo);
    }

    private void saveBarrageMessage(LoginParamDto paramDto) {
        String message = MessageFormat.format("新用户{0} 获得0.88元",
            CommonUtils.mobileEncrypt(paramDto.getMobileNum()));
        // 记录弹幕消息
        barrageMessageRepository.save(BarrageMessage.builder()
            // 后续使用枚举+模版实现
            .messageCode("user_invitation_registration").messageDesc(message).creator("system")
            .updator("system").build());
    }

}
