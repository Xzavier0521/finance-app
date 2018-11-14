package finance.domainservice.service.userinfo.impl;

import java.math.BigDecimal;
import java.util.*;
import java.util.Map.Entry;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;

import finance.api.model.base.Page;
import finance.api.model.response.ResponseResult;
import finance.api.model.vo.InviteOrdersVo;
import finance.api.model.vo.UserProfitInfoDetailVo;
import finance.api.model.vo.UserInfoDetailVo;
import finance.core.common.constant.Constant;
import finance.core.common.constant.WeChatConstant;
import finance.core.common.enums.*;
import finance.core.common.util.DateUtil;
import finance.domain.LevelCount;
import finance.domain.TeamInfoQueryResult;
import finance.domain.dto.IdCardInfoDto;
import finance.domain.dto.UserBankCardDto;
import finance.domainservice.service.auth.AuthService;
import finance.domainservice.service.jwt.JwtService;
import finance.domainservice.service.user.query.TeamInfoQueryService;
import finance.domainservice.service.userinfo.UserInfoBiz;
import finance.domainservice.service.validate.PwdValidateService;
import finance.domainservice.service.validate.SmsValidateService;
import finance.mapper.*;
import finance.model.po.*;

/**
 * 用户信息服务类.
 *
 * @author hewenbin
 * @version v1.0 2018年7月3日 下午9:00:27 hewenbin
 */
@Slf4j
@Service
public class UserInfoBizImpl implements UserInfoBiz {

    @Resource
    private FinanceUserInfoDAO            userInfoMapper;
    @Resource
    private FinanceUserBankCardInfoDAO    bankCardInfoMapper;
    @Resource
    private FinanceUserPwdInfoDAO         pwdInfoMapper;
    @Resource
    private PwdValidateService            pwdValidateService;
    @Resource
    private SmsValidateService            smsValidateService;
    @Resource
    private JwtService                    jwtService;
    @Resource
    private FinanceUserInviteInfoDAO      inviteInfoMapper;
    @Resource
    private FinanceUserAccountDAO         accountMapper;
    @Resource
    private FinanceIdCardInfoDAO          idCardInfoMapper;
    @Resource
    private AuthService                   authService;
    @Resource
    private FinanceCoinLogDAO             financeCoinLogMapper;
    @Resource
    private FinanceThirdAccountInfoDAO    thirdAccountInfoMapper;

    @Resource
    private TeamInfoQueryService          teamInfoQueryService;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public UserInfoDetailVo queryUserInfo(Long userId) {
        UserInfoDetailVo detail = new UserInfoDetailVo();
        // 不存在用户不存在的情况，只有登录的用户才可以调用该方法
        FinanceUserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);

        // 检查是否有设置账户密码
        FinanceUserPwdInfo pwdInfo = pwdInfoMapper.selectByUserId(userId, PwdType.withdraw.name());

        //身份证信息
        FinanceIdCardInfo idCardInfo = idCardInfoMapper.selectByUserId(userId);
        if (idCardInfo != null) {
            detail.setRealName(idCardInfo.getRealName());
            detail.setIdNum(idCardInfo.getIdNum());
            detail.setAuthStatus(idCardInfo.getAuthStatus());
        } else {
            //无信息时返回"未完善"
            detail.setAuthStatus(AuthStatus.not_save.getCode());
        }

        //银行卡信息
        FinanceUserBankCardInfo bankCardInfo = bankCardInfoMapper.selectDefaultBankCard(userId);
        if (bankCardInfo != null) {
            detail.setBankName(bankCardInfo.getBankName());
            detail.setAccountNo(bankCardInfo.getAccountNo());
        }
        //是否绑定QQ、微信
        FinanceThirdAccountInfo thirdAccountInfo = new FinanceThirdAccountInfo();
        thirdAccountInfo.setUserId(userId);
        List<FinanceThirdAccountInfo> thirdAccountInfoList = thirdAccountInfoMapper
            .selectListBySelective(thirdAccountInfo);
        boolean isBindQQ = false;
        boolean isBindWechat = false;
        if (CollectionUtils.isNotEmpty(thirdAccountInfoList)) {
            for (FinanceThirdAccountInfo thirdAccountInfo1 : thirdAccountInfoList) {
                if (ThirdLoginChannel.qq.name().equals(thirdAccountInfo1.getChannel())
                    && "1".equals(thirdAccountInfo1.getStatus())) {
                    isBindQQ = true;
                }
                if (ThirdLoginChannel.wechat.name().equals(thirdAccountInfo1.getChannel())
                    && "1".equals(thirdAccountInfo1.getStatus())) {
                    isBindWechat = true;
                }
            }
        }
        // 是否关注公众号
        log.info("查询用户:{}绑定关系", userId);
        FinanceThirdAccountInfo param = new FinanceThirdAccountInfo();
        param.setUserId(userId);
        param.setChannel("wechatPub");
        FinanceThirdAccountInfo financeThirdAccountInfo = thirdAccountInfoMapper
            .selectOneBySelective(param);
        //
        log.info("financeThirdAccountInfo:{}", financeThirdAccountInfo);
        if (Objects.nonNull(financeThirdAccountInfo)) {
            String openId = financeThirdAccountInfo.getOpenId();
            detail.setWeChatOpenId(openId);
            detail.setIsFlowWeChatPub(isFlowWeChatPub(openId));
        } else {
            // 没有绑定open_id
            detail.setIsFlowWeChatPub(true);
        }
        detail.setHasAccountPwd(pwdInfo != null);
        detail.setMobileNum(userInfo.getMobileNum());
        detail.setInviteCode(userInfo.getInviteCode());
        detail.setIsBindQq(isBindQQ);
        detail.setIsBindWechat(isBindWechat);
        return detail;
    }

    @Override
    public UserProfitInfoDetailVo queryUserProfitInfo(Long userId) {
        UserProfitInfoDetailVo detail = new UserProfitInfoDetailVo();

        // 计算当前收益 = 可提现 + 已提现
        BigDecimal canWithdrawMoney = BigDecimal.ZERO;
        BigDecimal totalInCome = BigDecimal.ZERO;
        FinanceUserAccount account = accountMapper.getAccountByUserId(userId);
        if (account != null) {
            canWithdrawMoney = account.getCanWithdrawMoney();
            totalInCome = account.getSumChargeMoney();
        }

        //可用金币数量
        Integer totalAvailableCoin = financeCoinLogMapper.selectCoinNumByUserId(userId);

        //今日返现金额
        //        double todayCashBack = orderMapper.todayCashBack(userId, DateUtil.getDayBegin(new Date()), DateUtil.getDayEnd(new Date()));

        // 计算今天邀请人数
        Long todayInviteCount = this.getInviteCont(userId,
            DateUtil.dateToString(new Date(), DateUtil.fm_yyyy_MM_dd));

        // 计算累计邀请人数
        Long totalInviteCount = this.getInviteCont(userId, "1970-01-01");

        detail.setAvailableCoin(totalAvailableCoin == null ? 0 : totalAvailableCoin);
        detail.setTodayInviteCount(todayInviteCount);
        detail.setTotalInviteCount(totalInviteCount);
        detail.setCanWithdrawMoney(canWithdrawMoney);
        detail.setTotalIncome(totalInCome);
        return detail;
    }

    /**
     * 查询邀请人数（两级，儿子、孙子）.
     *
     * @param userId
     * @param beginDate
     * @return
     * @author hewenbin
     * @version UserInfoBizImpl.java, v1.0 2018年7月18日 上午10:23:29 hewenbin
     */
    private Long getInviteCont(Long userId, String beginDate) {
        Long firstCount = inviteInfoMapper.selectCountByParentId(Arrays.asList(userId), beginDate);
        Long secondCount = inviteInfoMapper.selectCountByGrandParentId(Arrays.asList(userId),
            beginDate);
        secondCount = secondCount == null ? 0 : secondCount;
        return firstCount + secondCount;
    }

    @Transactional
    @Override
    public ResponseResult<String> saveBankCard(UserBankCardDto bankCardDto) {
        FinanceUserBankCardInfo bankCardInfo = new FinanceUserBankCardInfo();

        Long userId = bankCardDto.getUserId();
        String accountNo = bankCardDto.getAccountNo();

        //获取姓名
        FinanceIdCardInfo idCardInfo = idCardInfoMapper.selectByUserId(userId);
        //未完善身份证信息不能实名认证
        if (idCardInfo == null) {
            return ResponseResult.error(CodeEnum.idInfoNotSave);
        } else if (AuthStatus.not_save.getCode() == (idCardInfo.getAuthStatus())) {
            return ResponseResult.error(CodeEnum.idInfoNotSave);
        }
        bankCardDto.setAccountName(idCardInfo.getRealName());
        bankCardDto.setIdNum(idCardInfo.getIdNum());

        //实名身份认证
        ResponseResult<String> res = authService.auth(bankCardDto);

        if (!res.isSucceed()) {
            if (CodeEnum.bankCardAuthDiffer.getCode().equals(res.getErrorCode())) {
                return ResponseResult.error(CodeEnum.bankCardAuthDiffer);
            }
            return ResponseResult.error(CodeEnum.bankCardAuthError);
        }

        //更新身份证信息为已验证
        //如果该用户身份证已验证，返回错误提示
        if (AuthStatus.is_auth.getCode() == idCardInfo.getAuthStatus()) {
            return ResponseResult.error(CodeEnum.bankCardAuthed);
        }
        //如果该用户身份证未验证，则更新身份证信息
        //查询该身份证号是否存在已认证的信息
        FinanceIdCardInfo authIdCardInfo = idCardInfoMapper.selectByAuthId(idCardInfo.getIdNum(),
            AuthStatus.is_auth.getCode());
        if (authIdCardInfo != null) {
            //如果该身份证号已经存在用户认证，则提示错误:身份证已被其他用户验证
            return ResponseResult.error(CodeEnum.idAuthByOthersError);
        }
        Long id = idCardInfo.getId();
        idCardInfo = new FinanceIdCardInfo();
        idCardInfo.setId(id);
        idCardInfo.setUserId(userId);
        idCardInfo.setAuthStatus(AuthStatus.is_auth.getCode());
        idCardInfoMapper.updateByPrimaryKeySelective(idCardInfo);

        BeanUtils.copyProperties(bankCardDto, bankCardInfo);
        // 将用户自己的所有银行卡全部更新为非默认
        FinanceUserBankCardInfo updateCardInfo = new FinanceUserBankCardInfo();
        updateCardInfo.setUserId(userId);
        updateCardInfo.setIsDefault(Integer.valueOf("0"));
        bankCardInfoMapper.updateByUserId(updateCardInfo);

        // 检查本次要添加的银行卡是否存在
        FinanceUserBankCardInfo existCardInfo = bankCardInfoMapper.selectUserBankCard(userId,
            accountNo);

        // 如果有，则更新该银行卡信息，并且为默认（为了兼容用户卡号没问题，但是姓名错误的情况）
        if (existCardInfo != null) {
            // 相同的字段，无需更新
            bankCardInfo.setAccountNo(null);
            bankCardInfo.setUserId(null);

            bankCardInfo.setIsDefault(Integer.valueOf("1"));
            bankCardInfo.setId(existCardInfo.getId());
            bankCardInfoMapper.updateByPrimaryKeySelective(bankCardInfo);
        } else {
            // 如果没有，则添加，并且为默认
            bankCardInfo.setIsDefault(Integer.valueOf("1"));
            bankCardInfoMapper.insertSelective(bankCardInfo);
        }
        //        accountMapper.updateByUserId(userId, bankCardInfo.getAccountName());
        return ResponseResult.success(null);
    }

    @Override
    public FinanceUserBankCardInfo queryDefaultBankCard(Long userId) {
        return bankCardInfoMapper.selectDefaultBankCard(userId);
    }

    @Override
    public ResponseResult<String> savePwd(PwdType pwdType, Long userId, String pwd,
                                          String mobileCode) {
        // 检查是否已经设置密码
        FinanceUserPwdInfo pwdInfo = pwdInfoMapper.selectByUserId(userId, pwdType.name());

        // 生成密码
        String targetPwd = pwdValidateService.getPwd(pwdType, userId, pwd);

        // 如果是，则校验短信验证码，否则不校验
        if (pwdInfo != null) {
            // XXX 可以使用查询表的方式替代
            String mobileNum = jwtService.getUserInfo().getMobileNum();
            Boolean validateRes = smsValidateService.vidateSmsCode(mobileNum, mobileCode,
                SmsUseType.changePayPwd.name());
            if (!validateRes) {
                return ResponseResult.error(CodeEnum.accountPwdSmsValidateFail);
            }
            // 更新
            FinanceUserPwdInfo editInfo = new FinanceUserPwdInfo();
            editInfo.setId(pwdInfo.getId());
            editInfo.setPwd(targetPwd);
            pwdInfoMapper.updateByPrimaryKeySelective(editInfo);
        } else {
            // 添加
            pwdInfo = new FinanceUserPwdInfo();
            pwdInfo.setPwd(targetPwd);
            pwdInfo.setPwdType(pwdType.name());
            pwdInfo.setUserId(userId);
            pwdInfoMapper.insertSelective(pwdInfo);
        }

        return ResponseResult.success(null);
    }

    @Override
    public Map<String, Object> queryInviteListEx(Long userId, int maxCount, int type) {
        Map<String, Object> resultMap = Maps.newHashMap();
        CustomerQueryTypeEnum customerQueryType = CustomerQueryTypeEnum
            .getByCode(String.valueOf(type));
        TeamInfoQueryResult queryResult = teamInfoQueryService.queryTeamUserList(userId,
            customerQueryType, maxCount);
        LevelCount levelCount = LevelCount.builder()
            .firstLevelCount(queryResult.getFirstLevelCount())
            .secondLevelCount(queryResult.getSecondLevelCount())
            .allLevelCount(queryResult.getAllLevelCount()).build();
        resultMap.put("levelCount", levelCount);
        switch (customerQueryType) {
            case FIRST_LEVEL:
                resultMap.put("inviteList", queryResult.getFirstLevelUserList());
                break;
            case SECOND_LEVEL:
                resultMap.put("inviteList", queryResult.getSecondLevelUserList());
                break;
            case ALl:
                resultMap.put("inviteList", queryResult.getAllLevelUserList());
                break;
            default:
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> queryInviteList(Long userId, int maxCount, int type) {
        Map<String, Object> resMap = new HashMap<>();
        List<Map<String, Object>> oneTwoList = new ArrayList<>();
        List<Map<String, Object>> onlyOneList = new ArrayList<>();
        List<Map<String, Object>> onlyTwoList = new ArrayList<>();
        int allLevelCount = 0, firstLevelCount = 0, secondLevelCount = 0;
        Map<String, Integer> countMap = new HashMap<>();

        // 先查询一级、二级，数据量上限为总量（maxCount）的上限
        Page<FinanceUserInviteInfo> page = new Page<>(maxCount, 1L);
        List<Long> rootParentIds = new ArrayList<>();
        rootParentIds.add(userId);
        List<FinanceUserInviteInfo> firstLevels = inviteInfoMapper.selectByParentIds(rootParentIds,
            page, null);
        if (firstLevels.isEmpty()) {
            countMap.put("allLevelCount", allLevelCount);
            countMap.put("firstLevelCount", firstLevelCount);
            countMap.put("secondLevelCount", secondLevelCount);
            resMap.put("levelCount", countMap);
            resMap.put("inviteList", oneTwoList);
            return resMap;
        }
        firstLevelCount = firstLevels.size();
        // 过滤出第一级UserID 列表，作为查询第二级的条件ParentId
        List<Long> firstLevelUserIds = new ArrayList<>();
        firstLevels.forEach(inviteInfo -> firstLevelUserIds.add(inviteInfo.getUserId()));
        List<FinanceUserInviteInfo> secondLevels = inviteInfoMapper
            .selectByParentIds(firstLevelUserIds, page, null);

        secondLevelCount = secondLevels.size();
        allLevelCount = firstLevelCount + secondLevelCount;

        countMap.put("allLevelCount", allLevelCount);
        countMap.put("firstLevelCount", firstLevelCount);
        countMap.put("secondLevelCount", secondLevelCount);

        // 过滤出第一级和第二级的 UserId 映射关系
        Map<Long, List<Long>> firstSecondMap = new HashMap<>();
        for (FinanceUserInviteInfo info : firstLevels) {
            firstSecondMap.put(info.getUserId(), new ArrayList<>());
        }

        for (FinanceUserInviteInfo info : secondLevels) {
            List<Long> tempUserIds = firstSecondMap.get(info.getParentUserId());
            tempUserIds.add(info.getUserId());
        }

        List<Long> userIds = new ArrayList<>(); // 需要查询的用户 ID

        // 过滤在上限(maxCount)之内的用户列表
        Map<Long, List<Long>> firstSecondMap2 = new HashMap<>();// 真正需要查询的用户列表
        int currentCount = 0;
        for (Entry<Long, List<Long>> map : firstSecondMap.entrySet()) {
            userIds.add(map.getKey());
            List<Long> tempList = new ArrayList<>();
            firstSecondMap2.put(map.getKey(), tempList);
            currentCount++;
            if (currentCount == maxCount) {
                break;
            }

            for (Long uuserId : map.getValue()) {
                userIds.add(uuserId);
                tempList.add(uuserId);
                currentCount++;
                if (currentCount == maxCount) {
                    break;
                }
            }
        }

        // 查询用户手机号、银行卡的账户姓名
        List<FinanceUserInfo> userInfos = userIds.isEmpty() ? new ArrayList<>()
            : userInfoMapper.selectByPrimaryKeys(userIds);
        Map<Long, FinanceUserInfo> userInfoMap = new HashMap<>();
        userInfos.forEach(info -> userInfoMap.put(info.getId(), info));

        List<FinanceIdCardInfo> idCardInfos = userIds.isEmpty() ? new ArrayList<>()
            : idCardInfoMapper.selectByUserIdList(userIds);
        Map<Long, FinanceIdCardInfo> idCardInfoMap = new HashMap<>();
        idCardInfos.forEach(info -> idCardInfoMap.put(info.getUserId(), info));

        // 组装最终返回的数据
        for (Entry<Long, List<Long>> map1 : firstSecondMap2.entrySet()) {
            Map<String, Object> oneMap = new HashMap<>();
            FinanceUserInfo userInfo1 = userInfoMap.get(map1.getKey());
            FinanceIdCardInfo cardInfo1 = idCardInfoMap.get(map1.getKey());
            oneMap.put("mobileNum", userInfo1.getMobileNum());
            oneMap.put("registerDate",
                DateUtil.dateToString(userInfo1.getCreateTime(), DateUtil.fm_yyyy_MM_dd_HHmmss));
            oneMap.put("name", cardInfo1 == null ? "***" : cardInfo1.getRealName());
            List<Map<String, Object>> twoList = new ArrayList<>();
            //查询类型为一级时不返回二级客户
            if (Constant.type_first != type) {
                oneMap.put("inviteList", twoList);
            }
            oneTwoList.add(oneMap);
            onlyOneList.add(oneMap);

            for (Long tempUserId : map1.getValue()) {
                Map<String, Object> twoMap = new HashMap<>();
                FinanceUserInfo userInfo2 = userInfoMap.get(tempUserId);
                FinanceIdCardInfo cardInfo2 = idCardInfoMap.get(tempUserId);
                twoMap.put("mobileNum", userInfo2.getMobileNum().substring(0, 3) + "****"
                                        + userInfo2.getMobileNum().substring(7));
                twoMap.put("registerDate", DateUtil.dateToString(userInfo2.getCreateTime(),
                    DateUtil.fm_yyyy_MM_dd_HHmmss));
                twoMap.put("name", cardInfo2 == null ? "***" : cardInfo2.getRealName());
                twoList.add(twoMap);
                onlyTwoList.add(twoMap);
            }
            this.sort(twoList, "registerDate");
        }
        //根据查询类型返回0:所有1:一级客户2:二级客户
        switch (type) {
            case Constant.type_all:
                this.sort(oneTwoList, "registerDate");
                resMap.put("levelCount", countMap);
                resMap.put("inviteList", oneTwoList);
                return resMap;
            case Constant.type_first:
                this.sort(onlyOneList, "registerDate");
                resMap.put("inviteList", onlyOneList);
                return resMap;
            case Constant.type_second:
                this.sort(onlyTwoList, "registerDate");
                resMap.put("inviteList", onlyTwoList);
                return resMap;
            default:
                this.sort(oneTwoList, "registerDate");
                resMap.put("countMap", countMap);
                resMap.put("inviteList", oneTwoList);
                return resMap;
        }
    }

    @Override
    public List<InviteOrdersVo> queryInviteOrders() {
        Page<InviteOrdersVo> page = new Page<>(30, 1L); // 前30个
        List<InviteOrdersVo> orders = inviteInfoMapper.selectInviteOrders(page);
        List<Long> userIds = new ArrayList<>();
        orders.forEach(info -> userIds.add(info.getUserId()));
        //批量查询身份证信息
        List<FinanceIdCardInfo> idCardInfos = idCardInfoMapper.selectByUserIdList(userIds);
        Map<Long, FinanceIdCardInfo> idCardInfoMap = new HashMap<>();
        idCardInfos.forEach(idCardInfo -> idCardInfoMap.put(idCardInfo.getUserId(), idCardInfo));
        for (int i = 0; i < orders.size(); i++) {
            orders.get(i).setOrderNum(i + 1);
            //查询用户真实姓名。然后塞入 orders 中
            if (idCardInfoMap.get(orders.get(i).getUserId()) != null) {
                orders.get(i)
                    .setRealName(idCardInfoMap.get(orders.get(i).getUserId()).getRealName());
            } else {
                // 没有保存姓名
                orders.get(i).setRealName("神秘大侠");
            }
        }
        return orders;
    }

    @Override
    public ResponseResult<Boolean> saveIdCardInfo(IdCardInfoDto idCardInfoDto) {
        FinanceIdCardInfo idCardInfo = new FinanceIdCardInfo();
        BeanUtils.copyProperties(idCardInfoDto, idCardInfo);
        Long userId = idCardInfoDto.getUserId();
        idCardInfo.setAuthStatus((int) -userId);
        //检查是否有身份信息
        FinanceIdCardInfo ExistIdCardInfo = idCardInfoMapper.selectByUserId(userId);

        if (ExistIdCardInfo == null) {
            // 如果没有，则添加
            idCardInfoMapper.insertSelective(idCardInfo);
        } else if (AuthStatus.is_auth.getCode() == ExistIdCardInfo.getAuthStatus()) {
            //如果有，且已验证则不允许修改
            return ResponseResult.error(CodeEnum.idAuthExist);
        } else {
            //如果有,且未验证则更新
            idCardInfo.setUserId(userId);
            idCardInfo.setId(ExistIdCardInfo.getId());
            idCardInfoMapper.updateByPrimaryKeySelective(idCardInfo);
        }

        return ResponseResult.success(true);
    }

    @Override
    public Boolean isFlowWeChatPub(String openId) {
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
        return setOperations.isMember(WeChatConstant.WE_CHAT_FLOW_NUM, openId);
    }

    /**
     * 根据map的指定value排序list
     * @param list
     * @param pkey
     * @return
     * @author panzhongkang
     * @date 2018/8/22 17:12
     */
    public void sort(List<Map<String, Object>> list, String pkey) {
        Collections.sort(list, (o1, o2) -> {
            String map1value = (String) o1.get(pkey);
            String map2value = (String) o2.get(pkey);
            return map2value.compareTo(map1value);
        });
    }
}
