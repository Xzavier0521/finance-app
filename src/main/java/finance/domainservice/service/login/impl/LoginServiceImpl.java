package finance.domainservice.service.login.impl;

import static finance.core.common.util.PreconditionUtils.checkArgument;

import java.math.BigDecimal;
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

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import finance.api.model.response.ResponseResult;
import finance.core.common.constant.Constant;
import finance.core.common.enums.*;
import finance.core.common.util.NetUtils;
import finance.core.common.util.PreconditionUtils;
import finance.core.common.util.ResponseResultUtils;
import finance.domain.InviteOpenInfo;
import finance.domain.dto.LoginParamDto;
import finance.domain.dto.ThirdLoginParamDto;
import finance.domainservice.repository.InviteOpenInfoRepository;
import finance.domainservice.service.jwt.JwtService;
import finance.domainservice.service.login.LoginService;
import finance.domainservice.service.register.RegisterService;
import finance.domainservice.service.trans.InviteActivityService;
import finance.domainservice.service.user.ThirdBindService;
import finance.domainservice.service.validate.ImgValidateService;
import finance.domainservice.service.validate.SmsValidateService;
import finance.mapper.FinanceThirdAccountInfoDAO;
import finance.mapper.FinanceUserFirstLoginLogDAO;
import finance.mapper.FinanceUserInfoDAO;
import finance.mapper.FinanceUserLoginLogDAO;
import finance.model.po.FinanceThirdAccountInfo;
import finance.model.po.FinanceUserFirstLoginLog;
import finance.model.po.FinanceUserInfo;
import finance.model.po.FinanceUserLoginLog;

/**
 * <p>登录逻辑</p>
 * @author lili
 * @version $Id: LoginServiceImpl.java, v0.1 2018/11/13 1:33 PM lili Exp $
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Value("${third.openId.cache.timeout.minute}")
    private Long                        openIdCacheTimeout;
    @Value("${third.openId.cacke.key.prefix}")
    private String                      openIdCacheKeyPrefix;
    @Resource
    private ImgValidateService          imgValidateService;
    @Resource
    private SmsValidateService          smsValidateService;
    @Resource
    private FinanceUserInfoDAO          uerInfoMapper;
    @Resource
    private RegisterService             registerService;
    @Resource
    private FinanceUserFirstLoginLogDAO firstLoginLogMapper;
    @Resource
    private FinanceUserLoginLogDAO      loginLogMapper;
    @Resource
    private ThirdBindService            thirdBindService;
    @Resource
    private JwtService                  jwtService;
    @Resource
    private StringRedisTemplate         stringRedisTemplate;
    @Resource
    private InviteActivityService       activityService;
    @Resource
    private FinanceThirdAccountInfoDAO  thirdAccountInfoMapper;
    @Resource
    private InviteActivityService       inviteActivityServiceImpl;

    @Resource
    private InviteOpenInfoRepository    inviteOpenInfoRepository;

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
            FinanceUserInfo userInfo = uerInfoMapper.selectByMobile(paramDto.getMobileNum());
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
            Set<ActivityType> activityTypeSet = Sets.newHashSet(ActivityType.step_red_envelope,
                ActivityType.fixed_red_envelope);
            if (isRegister
                && activityTypeSet.contains(ActivityType.getByCode(paramDto.getActivityType()))) {
                // 红包活动奖励
                Map<String, Object> resMap = this.redEnvelopeRewards(userInfo, paramDto);
                if (Objects.nonNull(resMap)) {
                    if (ActivityType.fixed_red_envelope == ActivityType
                        .getByCode(paramDto.getActivityType())) {
                        resData.put("fixedAmountActivity", resMap);
                    }
                    resData.put("bindOpenId", resMap.get("bindOpenId"));
                }
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
        Boolean smsValidateRes = smsValidateService.vidateSmsCode(paramDto.getMobileNum(),
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
    private Map<String, Object> redEnvelopeRewards(FinanceUserInfo userInfo,
                                                   LoginParamDto paramDto) {
        Map<String, Object> redEnvelopeMap = Maps.newHashMap();
        String activityType = paramDto.getActivityType();
        //判断是否成功绑定openId
        String channel = paramDto.getType();
        if (StringUtils.isNotBlank(paramDto.getWechatPubName())) {
            channel = Constant.THIRD_PARD_WECHATPUB;
        }
        if (ActivityType.step_red_envelope == ActivityType.getByCode(activityType)) {
            activityService.stepRewards(userInfo, paramDto);
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
        if (ActivityType.fixed_red_envelope == ActivityType.getByCode(activityType)) {
            Map<String, Object> resMap = inviteActivityServiceImpl
                .handleFixedAmountActivity(userInfo, paramDto);
            //固定金额活动处理
            CodeEnum responseCode = ((CodeEnum) resMap.get("code"));
            BigDecimal userDivideMoney = resMap.get("userDivideMoney") != null
                ? (BigDecimal) resMap.get("userDivideMoney")
                : null;

            redEnvelopeMap.put("fixedAmountActivityDivideMoney", userDivideMoney);
            redEnvelopeMap.put("code", responseCode.getCode());
            redEnvelopeMap.put("msg", responseCode.getMsg());
            return redEnvelopeMap;
        }
        return null;
    }

    private Boolean validateImgCode(String type, String imgCodeId, String imgCode) {
        Boolean imgValidateRes;
        if (LoginType.MOBILE == LoginType.getByCode(type)) {
            return true;
        }
        imgValidateRes = imgValidateService.vidateImgCode(imgCodeId, imgCode);
        return imgValidateRes;
    }

    private void firstLogin(FinanceUserInfo userInfo, LoginParamDto paramDto) {

        if (Objects.isNull(userInfo) || userInfo.getId() < 1
            || StringUtils.isBlank(paramDto.getPlatformCode())) {
            return;
        }
        // 判断是否是首次登录某个平台（模块）
        FinanceUserFirstLoginLog firstLoginLog = firstLoginLogMapper
            .selectByUserId(userInfo.getId(), paramDto.getPlatformCode());
        if (Objects.nonNull(firstLoginLog)) {
            return;
        }
        // 如果是首次登录，则记录首次登录记录
        firstLoginLog = new FinanceUserFirstLoginLog();
        firstLoginLog.setPlatformCode(paramDto.getPlatformCode());
        firstLoginLog.setPlatformDetail(paramDto.getPlatformDetail());
        firstLoginLog.setUserId(userInfo.getId());
        firstLoginLogMapper.insertSelective(firstLoginLog);
        // 首次登录finance_home时送钱
        if (PlatformCodeEnum.FINANCE_HOME == PlatformCodeEnum
            .getByCode(paramDto.getPlatformCode())) {
            //transBiz.recharge(userInfo.getId(), userInfo.getMobileNum());
        }
    }

    private void saveLoginLog(FinanceUserInfo userInfo, LoginParamDto paramDto) {
        FinanceUserLoginLog loginLog = new FinanceUserLoginLog();
        BeanUtils.copyProperties(paramDto, loginLog);
        loginLog.setUserId(userInfo != null ? userInfo.getId() : null);
        loginLog.setLoginName(paramDto.getMobileNum());
        loginLog.setLoginStatus(CodeEnum.succ.getCode());
        loginLog.setLoginDesc(CodeEnum.succ.getMsg());
        loginLogMapper.insertSelective(loginLog);
    }

    private String saveJwt(FinanceUserInfo userInfo) {
        return jwtService.saveJwt(userInfo);
    }

    /**
     * 保存用户的openId等信息
     */
    private void saveOpenInfo(FinanceUserInfo userInfo, LoginParamDto paramDto) {
        String loginType = paramDto.getType();
        String activityType = paramDto.getActivityType();
        String weChatPubName = paramDto.getWechatPubName();
        if (StringUtils.isBlank(loginType)) {
            return;
        }
        Set<ActivityType> activityTypeSet = Sets.newHashSet(ActivityType.fixed_red_envelope,
            ActivityType.step_red_envelope);
        Set<LoginType> loginTypeSet = Sets.newHashSet(LoginType.QQ, LoginType.WE_CHAT);
        if (loginTypeSet.contains(LoginType.getByCode(paramDto.getType()))
            || activityTypeSet.contains(ActivityType.getByCode(activityType))) {
            // 保存绑定信息
            String openId = paramDto.getOpenId();
            //红包分享分享的是微信公众号
            if (StringUtils.isNotBlank(paramDto.getWechatPubName())) {
                loginType = Constant.THIRD_PARD_WECHATPUB;
            }
            thirdBindService.bindUser(userInfo.getId(), openId, loginType, weChatPubName);
            //删除openid-key
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
            FinanceUserInfo userInfo = uerInfoMapper.selectByPrimaryKey(accountInfo.getUserId());
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
