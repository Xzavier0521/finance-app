package cn.zhishush.finance.domainservice.service.login.impl;

import static cn.zhishush.finance.core.common.util.PreconditionUtils.checkArgument;

import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import cn.zhishush.finance.api.model.response.ResponseResult;
import cn.zhishush.finance.core.common.constants.Constant;
import cn.zhishush.finance.core.common.constants.RedEnvelopConstant;
import cn.zhishush.finance.core.common.enums.ActivityType;
import cn.zhishush.finance.core.common.enums.CodeEnum;
import cn.zhishush.finance.core.common.enums.LoginType;
import cn.zhishush.finance.core.common.enums.ReturnCode;
import cn.zhishush.finance.core.common.util.NetUtils;
import cn.zhishush.finance.core.common.util.PreconditionUtils;
import cn.zhishush.finance.core.common.util.ResponseResultUtils;
import cn.zhishush.finance.core.dal.dao.account.FinanceThirdAccountInfoDAO;
import cn.zhishush.finance.core.dal.dao.log.UserFirstLoginLogDAO;
import cn.zhishush.finance.core.dal.dao.log.UserLoginLogDAO;
import cn.zhishush.finance.core.dal.dao.user.UserInfoDAO;
import cn.zhishush.finance.core.dal.dataobject.account.FinanceThirdAccountInfo;
import cn.zhishush.finance.core.dal.dataobject.log.UserFirstLoginLogDO;
import cn.zhishush.finance.core.dal.dataobject.user.UserInfoDO;
import cn.zhishush.finance.core.dal.dataobject.user.UserLoginLogDO;
import cn.zhishush.finance.domain.dto.LoginParamDto;
import cn.zhishush.finance.domain.dto.ThirdLoginParamDto;
import cn.zhishush.finance.domain.user.UserInfo;
import cn.zhishush.finance.domain.weixin.InviteOpenInfo;
import cn.zhishush.finance.domainservice.converter.user.UserInfoConverter;
import cn.zhishush.finance.domainservice.repository.user.InviteOpenInfoRepository;
import cn.zhishush.finance.domainservice.service.activity.RedEnvelopeRainRegisterRewardService;
import cn.zhishush.finance.domainservice.service.jwt.JwtService;
import cn.zhishush.finance.domainservice.service.login.LoginService;
import cn.zhishush.finance.domainservice.service.register.RegisterService;
import cn.zhishush.finance.domainservice.service.trans.InviteActivityService;
import cn.zhishush.finance.domainservice.service.user.ThirdBindService;
import cn.zhishush.finance.domainservice.service.validate.ImgValidateService;
import cn.zhishush.finance.domainservice.service.validate.SmsValidateService;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * <p>登录逻辑</p>
 * 
 * @author lili
 * @version 1.0: LoginServiceImpl.java, v0.1 2018/11/13 1:33 PM lili Exp $
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Value("${third.openId.cache.timeout.minute}")
    private Long                                 openIdCacheTimeout;
    @Value("${third.openId.cacke.key.prefix}")
    private String                               openIdCacheKeyPrefix;
    @Resource
    private ImgValidateService                   imgValidateService;
    @Resource
    private SmsValidateService                   smsValidateService;
    @Resource
    private UserInfoDAO                          uerInfoMapper;
    @Resource
    private RegisterService                      registerService;
    @Resource
    private UserFirstLoginLogDAO                 firstLoginLogMapper;
    @Resource
    private UserLoginLogDAO                      loginLogMapper;
    @Resource
    private ThirdBindService                     thirdBindService;
    @Resource
    private JwtService                           jwtService;
    @Resource
    private StringRedisTemplate                  stringRedisTemplate;
    @Resource
    private InviteActivityService                inviteActivityService;
    @Resource
    private FinanceThirdAccountInfoDAO           thirdAccountInfoMapper;
    @Resource
    private InviteOpenInfoRepository             inviteOpenInfoRepository;
    @Resource
    private RedEnvelopeRainRegisterRewardService redEnvelopeRainRegisterRewardService;

    @Override
    public ResponseResult<Map<String, Object>> login(HttpServletRequest req,
                                                     HttpServletResponse resp,
                                                     LoginParamDto paramDto) {

        ResponseResult<Map<String, Object>> response;
        try {
            // Agent 中取设备信息ß
            paramDto.setUserAgent(req.getHeader("User-Agent"));
            // 获取客户端IP
            paramDto.setIp(NetUtils.getIpAdrress(req));
            // 校验验证码
            CodeEnum resCodeEnum = this.validateImageAndSmsCode(paramDto);
            checkArgument(CodeEnum.succ == resCodeEnum, resCodeEnum.getMsg());
            // 创建用户
            // 是否是注册操作
            // 校验手机号是否存在
            UserInfoDO userInfo = uerInfoMapper.selectByMobile(paramDto.getMobileNum());
            // 如果不存在，则创建用户、记录注册日志、渠道信息、创建账户
            boolean isRegister = Objects.isNull(userInfo);
            if (isRegister) {
                userInfo = registerService.registerUser(paramDto);
            }
            setInviteCodeByOpenId(paramDto);
            // 首次登录
            this.firstLogin(userInfo, paramDto);
            // 记录登录日志
            this.saveLoginLog(userInfo, paramDto);
            // 记录绑定信息
            this.saveOpenInfo(userInfo, paramDto);
            // 保存JWT
            String jwt = this.saveJwt(userInfo);
            Map<String, Object> resData = new HashMap<>(4, 4);
            // 红包活动类型
            if (isRegister) {
                // 红包雨活动奖励
                UserInfo user = UserInfoConverter.convert(userInfo);
                if (StringUtils.isBlank(paramDto.getActivityCode())) {
                    String activityCode = getActivityCode(user, paramDto);
                    log.info("用户:{},活动代码:{}", user.getMobileNum(), activityCode);
                    if (RedEnvelopConstant.RED_ENVELOPE_RAIN_CODE.equals(activityCode)) {
                        redEnvelopeRainRegisterRewardService.process(user, paramDto);
                    }
                }
            }
            //
            if (isRegister && ActivityType.step_red_envelope == ActivityType
                .getByCode(paramDto.getActivityType())) {
                // 红包活动奖励
                /*Map<String, Object> resMap = this.redEnvelopeRewards(userInfo, paramDto);
                if (Objects.nonNull(resMap)) {
                    if (ActivityType.fixed_red_envelope == ActivityType
                        .getByCode(paramDto.getActivityType())) {
                        resData.put("fixedAmountActivity", resMap);
                    }
                    resData.put("bindOpenId", resMap.get("bindOpenId"));
                }*/

            }
            // 登录逻辑结束，返回
            if (Objects.nonNull(jwt)) {
                resData.put("jwt", jwt);
                resData.put("inviteCode", userInfo.getInviteCode());
                resData.put("isRegister", isRegister);
                response = ResponseResult.success(resData);
            } else {
                response = ResponseResult.success(null);
            }

        } catch (final Exception e) {
            response = ResponseResultUtils.error(e.getMessage());
            log.error("登陆异常:{}", ExceptionUtils.getStackTrace(e));
        }

        return response;
    }

    private String getActivityCode(UserInfo userInfo, LoginParamDto paramDto) {
        String activityCode = StringUtils.EMPTY;
        if (Objects.isNull(userInfo)) {
            return activityCode;
        }
        if (StringUtils.isNotBlank(paramDto.getOpenId())) {
            InviteOpenInfo inviteOpenInfo = inviteOpenInfoRepository
                .queryInviteOpenInfo(paramDto.getOpenId());
            if (Objects.nonNull(inviteOpenInfo)) {
                activityCode = inviteOpenInfo.getActivityCode();
            } else {
                log.info("用户:{}邀请绑定关系不存在", userInfo.getMobileNum());
            }
        } else {
            log.info("用户:{}绑定信息不存在", userInfo.getMobileNum());
        }
        return activityCode;
    }

    private CodeEnum validateImageAndSmsCode(LoginParamDto paramDto) {
        CodeEnum resCodeEnum = CodeEnum.succ;
        Set<String> whitelist = Sets.newHashSet();
        whitelist.add("17192197807");
        if (whitelist.contains(paramDto.getMobileNum())) {
            log.info("手机号码:{},白名单不校验[短信验证码&图片验证码]", paramDto.getMobileNum());
            return resCodeEnum;
        }
        // 校验图片验证码
        Boolean imgValidateRes = this.validateImgCode(paramDto.getType(), paramDto.getImgCodeId(),
            paramDto.getImgCode());
        PreconditionUtils.checkArgument(imgValidateRes, ReturnCode.LOGIN_IMG_VALIDATE_FAIL);
        // 校验短信验证码
        Boolean smsValidateRes = smsValidateService.validateSmsCode(paramDto.getMobileNum(),
            paramDto.getMobileCode(), paramDto.getType());
        PreconditionUtils.checkArgument(smsValidateRes, ReturnCode.LOGIN_SMS_VALIDATE_FAIL);
        return resCodeEnum;
    }

    private void setInviteCodeByOpenId(LoginParamDto paramDto) {
        // 根据open_id 获取邀请码
        if (StringUtils.isNotBlank(paramDto.getInviteCode())) {
            log.info("邀请码为:{},不需要查询邀请码和open_id绑定关系", paramDto.getInviteCode());
            return;
        }
        if (StringUtils.isBlank(paramDto.getOpenId())) {
            log.info("open_id不存在，无法绑定上下级关系，更新红包数据");
        }
        Map<String, Object> param = Maps.newHashMap();
        param.put("openId", paramDto.getOpenId());
        List<InviteOpenInfo> inviteOpenInfoList = inviteOpenInfoRepository.queryByCondition(param);
        if (CollectionUtils.isEmpty(inviteOpenInfoList)) {
            log.info("open_id:{},查询邀请码无记录", paramDto.getOpenId());
            return;
        }
        paramDto.setInviteCode(inviteOpenInfoList.get(0).getInviteCode());
    }

    /**
     * 红包活动奖励
     */
    private Map<String, Object> redEnvelopeRewards(UserInfoDO userInfo, LoginParamDto paramDto) {
        Map<String, Object> redEnvelopeMap = Maps.newHashMap();
        String activityType = paramDto.getActivityType();
        // 判断是否成功绑定openId
        String channel = paramDto.getType();
        if (StringUtils.isNotBlank(paramDto.getWechatPubName())) {
            channel = Constant.THIRD_PARD_WECHATPUB;
        }
        if (ActivityType.step_red_envelope == ActivityType.getByCode(activityType)) {
            inviteActivityService.stepRewards(userInfo, paramDto);
        }
        // 账号绑定信息
        FinanceThirdAccountInfo queryParam = new FinanceThirdAccountInfo();
        queryParam.setUserId(userInfo.getId());
        String openId = stringRedisTemplate.opsForValue()
            .get(openIdCacheKeyPrefix + paramDto.getOpenId());
        queryParam.setOpenId(openId);
        queryParam.setChannel(channel);
        queryParam.setPublicName(paramDto.getWechatPubName());
        FinanceThirdAccountInfo accountInfo = thirdAccountInfoMapper
            .selectOneBySelective(queryParam);
        if (accountInfo == null) {
            redEnvelopeMap.put("bindOpenId", "0");
            return redEnvelopeMap;
        }
        return null;
    }

    private Boolean validateImgCode(String type, String imgCodeId, String imgCode) {
        Boolean imgValidateRes;
        if (LoginType.MOBILE == LoginType.getByCode(type)) {
            return true;
        }
        imgValidateRes = imgValidateService.validateImgCode(imgCodeId, imgCode);
        return imgValidateRes;
    }

    private void firstLogin(UserInfoDO userInfo, LoginParamDto paramDto) {

        if (Objects.isNull(userInfo) || userInfo.getId() < 1
            || StringUtils.isBlank(paramDto.getPlatformCode())) {
            return;
        }
        // 判断是否是首次登录某个平台（模块）
        UserFirstLoginLogDO firstLoginLog = firstLoginLogMapper.selectByUserId(userInfo.getId(),
            paramDto.getPlatformCode());
        if (Objects.nonNull(firstLoginLog)) {
            return;
        }
        // 如果是首次登录，则记录首次登录记录
        firstLoginLog = new UserFirstLoginLogDO();
        firstLoginLog.setPlatformCode(paramDto.getPlatformCode());
        firstLoginLog.setPlatformDetail(paramDto.getPlatformDetail());
        firstLoginLog.setUserId(userInfo.getId());
        firstLoginLogMapper.insertSelective(firstLoginLog);
    }

    private void saveLoginLog(UserInfoDO userInfo, LoginParamDto paramDto) {
        UserLoginLogDO loginLog = new UserLoginLogDO();
        BeanUtils.copyProperties(paramDto, loginLog);
        loginLog.setUserId(userInfo != null ? userInfo.getId() : null);
        loginLog.setLoginName(paramDto.getMobileNum());
        loginLog.setLoginStatus(CodeEnum.succ.getCode());
        loginLog.setLoginDesc(CodeEnum.succ.getMsg());
        loginLogMapper.insertSelective(loginLog);
    }

    private String saveJwt(UserInfoDO userInfo) {
        return jwtService.saveJwt(userInfo);
    }

    /**
     * 保存用户的openId等信息
     */
    private void saveOpenInfo(UserInfoDO userInfo, LoginParamDto paramDto) {
        String loginType = paramDto.getType();
        String activityType = paramDto.getActivityType();
        String weChatPubName = paramDto.getWechatPubName();
        if (StringUtils.isBlank(loginType)) {
            return;
        }
        if (StringUtils.isBlank(paramDto.getActivityType())) {
            return;
        }
        Set<ActivityType> activityTypeSet = Sets.newHashSet(ActivityType.fixed_red_envelope,
            ActivityType.step_red_envelope);
        Set<LoginType> loginTypeSet = Sets.newHashSet(LoginType.QQ, LoginType.WE_CHAT);
        if (loginTypeSet.contains(LoginType.getByCode(paramDto.getType()))
            || activityTypeSet.contains(ActivityType.getByCode(activityType))) {
            // 保存绑定信息
            String openId = paramDto.getOpenId();
            // 红包分享分享的是微信公众号
            if (StringUtils.isNotBlank(paramDto.getWechatPubName())) {
                loginType = Constant.THIRD_PARD_WECHATPUB;
            }
            thirdBindService.bindUser(userInfo.getId(), openId, loginType, weChatPubName);
            // 删除openid-key
            stringRedisTemplate.delete(openIdCacheKeyPrefix + paramDto.getOpenId());
        }

    }

    @Override
    public ResponseResult<Map<String, Object>> thirdLogin(HttpServletRequest req,
                                                          HttpServletResponse resp,
                                                          ThirdLoginParamDto paramDto) {
        String channel = paramDto.getChannel();
        String openId = paramDto.getOpenId();
        String code = paramDto.getCode();

        // 获取 OpenId
        openId = thirdBindService.queryOpenInfo(channel, code, openId);
        if (StringUtils.isEmpty(openId)) {
            return ResponseResult.error(CodeEnum.getOpenIdFail);
        }

        Map<String, Object> resMap = new HashMap<>();
        resMap.put("channel", channel);
        // 检查是否已绑定
        FinanceThirdAccountInfo accountInfo = thirdBindService.queryBindAccount(channel, openId,
            null);
        if (accountInfo != null) {
            // 已绑定
            // 记录登录日志
            UserInfoDO userInfo = uerInfoMapper.selectByPrimaryKey(accountInfo.getUserId());
            LoginParamDto loginParamDto = new LoginParamDto();
            BeanUtils.copyProperties(paramDto, loginParamDto);
            loginParamDto.setType(channel);
            loginParamDto.setMobileNum(openId);
            // Agent 中取设备信息
            loginParamDto.setUserAgent(req.getHeader("User-Agent"));
            // 获取客户端IP
            loginParamDto.setIp(NetUtils.getIpAdrress(req));
            this.saveLoginLog(userInfo, loginParamDto);
            // 保存JWT
            String jwt = this.saveJwt(userInfo);
            // 登录逻辑结束，返回
            resMap.put("jwt", jwt);
            resMap.put("isBind", true);
            return ResponseResult.success(resMap);
        } else {
            // 未绑定
            // 临时保存openId，10分钟过期，然后然后返回openId 对应的 KEY 返回给前端（在登录绑定时同时绑定该openId）
            String openKey = UUID.randomUUID().toString();
            stringRedisTemplate.opsForValue().set(openIdCacheKeyPrefix + openKey, openId,
                openIdCacheTimeout, TimeUnit.MINUTES);
            resMap.put("isBind", false);
            resMap.put("openId", openKey);
            return ResponseResult.success(resMap);
        }
    }

}
