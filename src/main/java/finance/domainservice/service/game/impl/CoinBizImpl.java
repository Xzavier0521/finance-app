package finance.domainservice.service.game.impl;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import finance.api.model.base.Page;
import finance.api.model.response.ResponseResult;
import finance.api.model.vo.*;
import finance.core.common.constants.Constant;
import finance.core.common.constants.WeChatConstant;
import finance.core.common.enums.CodeEnum;
import finance.core.common.enums.GameType;
import finance.core.common.util.DateUtil;
import finance.core.common.util.DateUtils;
import finance.core.dal.dao.FinanceCoinGameDAO;
import finance.core.dal.dao.FinanceCoinGameLogDAO;
import finance.core.dal.dao.FinanceCoinLogDAO;
import finance.core.dal.dao.FinanceUserInfoDAO;
import finance.core.dal.dataobject.FinanceCoinGame;
import finance.core.dal.dataobject.FinanceCoinGameLog;
import finance.core.dal.dataobject.FinanceCoinLog;
import finance.core.dal.dataobject.FinanceUserInfo;
import finance.domain.dto.CoinLockParamDto;
import finance.domain.dto.CoinLockResponseDto;
import finance.domain.dto.RedisLockDto;
import finance.domain.user.ThirdAccountInfo;
import finance.domainservice.repository.ThirdAccountInfoRepository;
import finance.domainservice.service.AbstractCoinDealMulti;
import finance.domainservice.service.game.CoinBiz;
import finance.domainservice.service.jwt.JwtService;
import finance.domainservice.service.wechat.WechatService;
import finance.ext.api.model.WeiXinTemplateData;
import finance.ext.api.request.WeiXinTemplateMessageSendRequest;
import finance.ext.api.response.WeiXinTemplateMessageSendResponse;
import finance.ext.integration.weixin.WeiXinTemplateMessageClient;

/**
 * @program: finance-server
 *
 * @description: 金币游戏BIZ
 *
 * @author: MORUIHAI
 *
 * @create: 2018-08-21 16:32
 **/
@Service
public class CoinBizImpl extends AbstractCoinDealMulti implements CoinBiz {
    private static final Logger           logger = LoggerFactory.getLogger(CoinBizImpl.class);
    @Value("${earlycard.sign.begintime}")
    private int                           signBeginTime;
    @Value("${earlycard.sign.endtime}")
    private int                           signEndTime;
    @Value("${earlycard.sign.switch}")
    private String                        signSwitch;
    @Resource
    private JwtService                    jwtService;
    @Resource
    private FinanceCoinGameLogDAO         financeCoinGameLogMapper;
    @Resource
    private FinanceUserInfoDAO            financeUserInfoMapper;
    @Resource
    private FinanceCoinLogDAO             financeCoinLogMapper;
    @Resource
    private FinanceCoinGameDAO            financeCoinGameMapper;
    @Resource
    private ThirdAccountInfoRepository    thirdAccountInfoRepository;
    @Resource
    private WechatService                 wechatService;
    @Resource
    private WeiXinTemplateMessageClient   weiXinTemplateMessageClient;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public ResponseResult<EarlyClockPageVO> getClockPageData() {
        /**1.jwt 获取用户user_id **/
        Long userId = jwtService.getUserInfo().getId();
        EarlyClockPageVO earlyClockPageVO = new EarlyClockPageVO();
        /**2.根据当前时间查看用户是否参与活动,是否已经打卡**/
        //2.1 查看昨日报名信息
        FinanceCoinGameLog userYerterdayLog = financeCoinGameLogMapper.selectByCurrentDate("Y", "",
            "", userId);
        if (userYerterdayLog != null) {
            earlyClockPageVO.setYesterdayJoinTask(true);
            FinanceCoinGameLog userClockLog = financeCoinGameLogMapper.selectByCurrentDate("", "",
                "Y", userId);
            if (userClockLog != null) {
                earlyClockPageVO.setClockTask(true);
            } else {
                earlyClockPageVO.setClockTask(false);
            }

        } else {
            earlyClockPageVO.setYesterdayJoinTask(false);
            earlyClockPageVO.setClockTask(false);
        }
        FinanceCoinGameLog userJoinLog = financeCoinGameLogMapper.selectByCurrentDate("", "Y", "",
            userId);
        if (userJoinLog != null) {
            earlyClockPageVO.setTodayJoinTask(true);

        } else {
            earlyClockPageVO.setTodayJoinTask(false);
        }
        /**3. 查询页面相应字段数据**/
        //3.1 查询所有 totalJoinNum 总参加打卡人数（报名参加明天的打卡） totalCoinNum 总金币数（报名参加明天打卡的金币
        JoinTaskBaseVO joinTaskBaseVO = financeCoinGameLogMapper.selectAllDataByCurrentDay();
        earlyClockPageVO.setTotalJoinNum(joinTaskBaseVO.getTotalJoinNum().intValue());
        earlyClockPageVO.setTotalCoinNum(joinTaskBaseVO.getTotalCoinNum() == null ? 0
            : joinTaskBaseVO.getTotalCoinNum().intValue());
        earlyClockPageVO.setAmountReward(joinTaskBaseVO.getTotalJoinNum() == null ? 0
            : (joinTaskBaseVO.getTotalJoinNum().intValue() / 10 > 10 ? 10
                : joinTaskBaseVO.getTotalJoinNum().intValue() / 10)
              * 1000);

        int todaySignNum = 0;
        int todayNotSignNum = 0;
        List<FinanceCoinGameLog> todaySignLogList = financeCoinGameLogMapper.selectByCondition("",
            "", "");
        if (todaySignLogList != null && todaySignLogList.size() > 0) {
            todaySignNum = todaySignLogList.size();
            earlyClockPageVO.setSignNum(todaySignNum);
        } else {
            earlyClockPageVO.setSignNum(0);
        }
        //3.2 noSignNum 昨天报名，但是今日未打卡人数
        List<FinanceCoinGameLog> yesterdayJoinLogList = financeCoinGameLogMapper
            .selectByYesterdayDate();
        Integer yesterdayTotalJoinCoinNum = 0;
        if (yesterdayJoinLogList != null && yesterdayJoinLogList.size() > 0) {
            todayNotSignNum = yesterdayJoinLogList.size() - todaySignNum;
            earlyClockPageVO.setNoSignNum(todayNotSignNum);
            for (FinanceCoinGameLog fcg : yesterdayJoinLogList) {
                yesterdayTotalJoinCoinNum = yesterdayTotalJoinCoinNum + fcg.getOutNum();
            }
            earlyClockPageVO.setYesterdayTotalJoinPersonNum(yesterdayJoinLogList.size());
        } else {
            earlyClockPageVO.setNoSignNum(0);
            earlyClockPageVO.setYesterdayTotalJoinPersonNum(0);
        }
        //新增 3.6 昨日报名总金币 yesterdayTotalJoinCoinNum
        earlyClockPageVO.setYesterdayTotalJoinCoinNum(yesterdayTotalJoinCoinNum);

        //3.3 earliestMobile 最早打卡人手机号 earliestTime 最早打卡时间
        List<FinanceCoinGameLog> earliestSignLogList = financeCoinGameLogMapper
            .selectByCondition("Y", "", "");
        if (earliestSignLogList != null && earliestSignLogList.size() > 0) {
            earlyClockPageVO
                .setEarliestMobile(findUserMobileByUserId(earliestSignLogList.get(0).getUserId()));
            earlyClockPageVO.setEarliestTime(
                new SimpleDateFormat("HH:mm:ss").format(earliestSignLogList.get(0).getSignTime()));
        } else {
            earlyClockPageVO.setEarliestMobile("");
            earlyClockPageVO.setEarliestTime("");
        }
        //3.4 maxCoinMobile 手气最好的手机号 maxCoinNum 手气最好的金币数量
        List<FinanceCoinGameLog> mostInNumLogList = financeCoinGameLogMapper.selectByCondition("",
            "Y", "");
        if (mostInNumLogList != null && mostInNumLogList.size() > 0) {
            earlyClockPageVO
                .setMaxCoinMobile(findUserMobileByUserId(mostInNumLogList.get(0).getUserId()));
            earlyClockPageVO.setMaxCoinNum(mostInNumLogList.get(0).getInNum().toString());

        } else {
            earlyClockPageVO.setMaxCoinMobile("");
            earlyClockPageVO.setMaxCoinNum("");
        }
        //3.5 longestMobile 连续打卡天数最长的手机号 longestNum 连续打卡最长天数
        List<FinanceCoinGameLog> mostClockCountLogList = financeCoinGameLogMapper
            .selectByCondition("", "", "Y");
        if (mostClockCountLogList != null && mostClockCountLogList.size() > 0) {
            earlyClockPageVO
                .setLongestMobile(findUserMobileByUserId(mostClockCountLogList.get(0).getUserId()));
            earlyClockPageVO.setLongestNum(mostClockCountLogList.get(0).getClockCount().toString());
        } else {
            earlyClockPageVO.setLongestMobile("");
            earlyClockPageVO.setLongestNum("");
        }
        //当前可用金币
        Integer totalCanUserCoin = financeCoinLogMapper.selectCoinNumByUserId(userId);
        earlyClockPageVO.setTotalCanUserCoin(totalCanUserCoin == null ? 0 : totalCanUserCoin);

        //活动表里查询早起打卡的信息
        FinanceCoinGame financeCoinGame = financeCoinGameMapper
            .selectByTaskType(Constant.EARLY_SIGN, GameType.activity.getCode());
        if (financeCoinGame == null) {
            ResponseResult.error(CodeEnum.joinFail);
        }
        earlyClockPageVO.setEarlyCardUseCoinNum(financeCoinGame.getNum());
        buildData(earlyClockPageVO);
        return ResponseResult.success(earlyClockPageVO);
    }

    /**
     * 获取早起打卡开始时间=
     */
    private void buildData(EarlyClockPageVO earlyClockPageVO) {
        earlyClockPageVO.setSignBeginTime(getWebFormatTime(String.valueOf(signBeginTime)));
        earlyClockPageVO.setSignEndTime(getWebFormatTime(String.valueOf(signEndTime)));
    }

    private String getWebFormatTime(String timeStr) {
        if (timeStr.length() < 2) {
            return MessageFormat.format("0{}:00:00", timeStr);
        } else {
            return MessageFormat.format("{}:00:00", timeStr);
        }
    }

    @Override
    public ResponseResult<MyRecordVO> findMyRecordList(Page<FinanceCoinLog> financeCoinLogPage) {
        /**1.jwt 查找user_id **/
        Long userId = jwtService.getUserInfo().getId();
        MyRecordVO myRecordVO = new MyRecordVO();
        /**2.totalOutCoin 累计投入金币总数 totalInCoin 累计赚取金币总数 totalSign 累计打卡天数 **/
        MyRecordVO totalRecord = financeCoinGameLogMapper.selectMyRecordTotalData(userId);
        if (totalRecord != null) {
            myRecordVO.setTotalInCoin(totalRecord.getTotalInCoin());
            myRecordVO.setTotalOutCoin(totalRecord.getTotalOutCoin());
            myRecordVO.setTotalSign(totalRecord.getTotalSign());
        } else {
            myRecordVO.setTotalInCoin(new Integer(0));
            myRecordVO.setTotalOutCoin(new Integer(0));
            myRecordVO.setTotalSign(new Integer(0));
        }
        /**2.活动表里查询早起打卡的信息**/
        FinanceCoinGame financeCoinGame = financeCoinGameMapper
            .selectByTaskType(Constant.EARLY_SIGN, GameType.activity.getCode());
        /*if(financeCoinGame == null){
            return ResponseResult.error(CodeEnum.fail);
        }
        */
        /** 3.投入、赚取记录 **/
        List<FinanceCoinLog> financeCoinLogList = financeCoinLogMapper.selectByUserId(userId,
            financeCoinGame.getId(), financeCoinLogPage);
        MyCoinGameLogVO myCoinGameLogVO = null;
        List<MyCoinGameLogVO> myCoinGameLogVOList = new ArrayList();
        if (financeCoinLogList != null && financeCoinLogList.size() > 0) {
            for (FinanceCoinLog fcl : financeCoinLogList) {
                myCoinGameLogVO = new MyCoinGameLogVO();
                myCoinGameLogVO
                    .setDatetime(DateUtil.dateToString(fcl.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
                myCoinGameLogVO.setCoinNum(fcl.getNum());
                myCoinGameLogVO.setDesc(fcl.getTaskName());
                myCoinGameLogVO.setType(fcl.getNum());
                myCoinGameLogVOList.add(myCoinGameLogVO);
            }

        }
        myRecordVO.setRecords(myCoinGameLogVOList);
        return ResponseResult.success(myRecordVO);
    }

    @Override
    public ResponseResult<Boolean> joinEarlyCoinGame() {
        /**1.jwt 获取用户user_id **/
        Long userId = jwtService.getUserInfo().getId();
        //防刷
        FinanceCoinGameLog userJoinLog = financeCoinGameLogMapper.selectByCurrentDate("", "Y", "",
            userId);
        if (userJoinLog != null) {
            //
            return ResponseResult.error(CodeEnum.invalidOperation);
        }
        CoinLockParamDto lockParamDto = new CoinLockParamDto();
        lockParamDto.setUserId(userId);
        String redisKey = userId + "";
        String returnResult = "";
        CoinLockResponseDto lockResponseDto = null;
        RedisLockDto<CoinLockParamDto, CoinLockResponseDto> redisLockDto = new RedisLockDto(
            redisKey, lockParamDto, lockResponseDto);
        this.handle(redisLockDto);
        if (!redisLockDto.hasLock()) {
            return ResponseResult.error(CodeEnum.joinFail);
        }
        returnResult = redisLockDto.getRes().getRetrunCode();
        if (!CodeEnum.succ.getCode().equals(returnResult)) {
            return ResponseResult.error(CodeEnum.getParam().get(returnResult));
        }
        return ResponseResult.success(true);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult<SignCoinVO> signEarlyCoinGame() {
        /**1.jwt 获取用户user_id **/
        Long userId = jwtService.getUserInfo().getId();
        //防刷
        FinanceCoinGameLog userSignLog = financeCoinGameLogMapper.selectByCurrentDate("", "", "Y",
            userId);
        if (userSignLog != null) {
            return ResponseResult.error(CodeEnum.invalidOperation);
        }
        FinanceCoinGameLog userYesJoinLog = financeCoinGameLogMapper.selectByCurrentDate("Y", "",
            "", userId);
        if (userYesJoinLog == null) {
            return ResponseResult.error(CodeEnum.invalidOperation);
        }

        /**2.参加时间 限制 **/
        //int hour = LocalTime.now().getHour();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if ("1".equals(signSwitch)) { //打开开关
            if (!(hour >= signBeginTime && hour < signEndTime)) {
                return ResponseResult.error(CodeEnum.signTimeInvalid);
            }
        }
        //从缓存获取参加打卡的总人数和总金币数
        Integer joinCoinNum = (Integer) redisTemplate.opsForValue()
            .get("coin_game_log:joinCoinClockIn");
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        String randomCoin = (String) hashOperations.get("coin_game_log:signCoinClockIn",
            "randomCoin");
        Map<String, Integer> randomCoinMap = (Map<String, Integer>) JSON.parse(randomCoin);
        logger.info("randomCoinMap:{}", randomCoinMap);
        Integer coin = 0;
        SignCoinVO signCoinVO = new SignCoinVO();
        if (null != randomCoinMap) {
            coin = randomCoinMap.get(String.valueOf(joinCoinNum));
            signCoinVO.setSignCoin(coin);
        }
        if (null != joinCoinNum) {
            joinCoinNum -= 1;
        }
        /**3.更新数据 **/
        FinanceCoinGameLog financeCoinGameLog = new FinanceCoinGameLog();
        financeCoinGameLog.setUserId(userId);
        financeCoinGameLog.setInNum(coin);
        //3.1 根据userid和时间查询昨天打卡活动信息
        FinanceCoinGameLog yesterdayGameLog = financeCoinGameLogMapper
            .selectByUserIdAndYesterdayDate(userId);
        if (yesterdayGameLog == null) {
            financeCoinGameLog.setClockCount(new Integer(1));
        } else {
            financeCoinGameLog.setClockCount(yesterdayGameLog.getClockCount() + new Integer(1));
        }
        financeCoinGameLog.setSignTime(new Date());
        financeCoinGameLog.setStatus(new Integer(1));
        financeCoinGameLogMapper.updateByUserIdAndDateSelective(financeCoinGameLog);
        FinanceCoinGame financeCoinGame = financeCoinGameMapper
            .selectByTaskType(Constant.EARLY_SIGN, GameType.activity.getCode());
        FinanceCoinLog financeCoinLog = new FinanceCoinLog();
        financeCoinLog.setUserId(financeCoinGameLog.getUserId());
        financeCoinLog.setTaskId(financeCoinGame.getId());
        financeCoinLog.setTaskName(financeCoinGame.getTaskName());
        financeCoinLog.setNum(coin);
        financeCoinLogMapper.insertSelective(financeCoinLog);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        redisTemplate.opsForValue().set("coin_game_log:joinCoinClockIn", joinCoinNum);
        //查询是否是被邀请的第一次打卡,如果成立，需要给邀请人分配奖励
        InviteUserCoinReward(userId);
        return ResponseResult.success(signCoinVO);
    }

    @Override
    public Map<String, Object> queryUserCoinInfo(Long userId) {
        Map<String, Object> map = new HashMap<>();
        //当前可用金币
        Integer totalCanUserCoin = financeCoinLogMapper.selectCoinNumByUserId(userId);
        //累计获取金币数量
        Integer totalGetCoin = financeCoinLogMapper.selectTotalGetCoinNumByUserId(userId);
        map.put("currentCoin", totalCanUserCoin == null ? 0 : totalCanUserCoin);
        map.put("totalCoin", totalGetCoin == null ? 0 : totalGetCoin);
        return map;
    }

    @Override
    public List<CoinRecordVO> queryUserCoinRecords(String type) {
        /**1.jwt 获取用户user_id **/
        Long userId = jwtService.getUserInfo().getId();
        List<CoinRecordVO> coinRecordVOList = new ArrayList<>();
        Page<FinanceCoinLog> financeCoinLogPage = new Page(30, 1l);
        List<FinanceCoinLog> financeCoinLogList = financeCoinLogMapper
            .queryUserCoinRecordsByType(userId, type, financeCoinLogPage);
        if (financeCoinLogList != null && financeCoinLogList.size() > 0) {
            CoinRecordVO coinRecordVO = null;
            for (FinanceCoinLog fc : financeCoinLogList) {
                coinRecordVO = new CoinRecordVO();
                coinRecordVO.setDatetime(fc.getCreateTime());
                coinRecordVO.setCoinNum(fc.getNum());
                coinRecordVO.setDesc(fc.getTaskName());
                coinRecordVOList.add(coinRecordVO);
            }
        }

        return coinRecordVOList;
    }

    /**
      *功能描述:根据用户userId 查询手机号
      * @author: moruihai
      * @date: 2018/8/22 11:13
      * @param: [UserId]
      * @return: java.lang.String
      */
    private String findUserMobileByUserId(Long UserId) {
        FinanceUserInfo financeUserInfo = financeUserInfoMapper.selectByPrimaryKey(UserId);
        String mobile = "";
        if (financeUserInfo != null) {
            mobile = financeUserInfo.getMobileNum();
            mobile = mobile.substring(0, 3) + "****" + mobile.substring(7, 11);

        } else {
            mobile = "神秘大侠";
        }
        return mobile;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void dealCoinTask(RedisLockDto redisLockDto) {
        CoinLockResponseDto lockResponseDto = new CoinLockResponseDto();
        /**1.获取数据 **/
        CoinLockParamDto lockParamDto = (CoinLockParamDto) redisLockDto.getParam();

        /**2.活动表里查询早起打卡的信息**/
        FinanceCoinGame financeCoinGame = financeCoinGameMapper
            .selectByTaskType(Constant.EARLY_SIGN, GameType.activity.getCode());

        /** 3.判断金币是否足够 **/
        Integer totalCoin = financeCoinLogMapper.selectCoinNumByUserId(lockParamDto.getUserId());
        Boolean isFullCoin = true;
        if (totalCoin != null) {
            if (totalCoin.intValue() < financeCoinGame.getNum().intValue()) {
                isFullCoin = false;
            }
        } else {
            isFullCoin = false;
        }

        if (!isFullCoin) {
            lockResponseDto.setRetrunCode(CodeEnum.coinNumNotEnough.getCode());
            redisLockDto.setRes(lockResponseDto);
            return;
        }

        /**4.插入数据 **/
        FinanceCoinGameLog financeCoinGameLog = new FinanceCoinGameLog();
        financeCoinGameLog.setUserId(lockParamDto.getUserId());
        financeCoinGameLog
            .setOutNum(financeCoinGame == null ? new Integer(0) : financeCoinGame.getNum());
        financeCoinGameLog.setJoinDate(new Date());
        financeCoinGameLog.setJoinTime(new Date());
        financeCoinGameLogMapper.insertSelective(financeCoinGameLog);

        FinanceCoinLog financeCoinLog = new FinanceCoinLog();

        financeCoinLog.setUserId(lockParamDto.getUserId());
        financeCoinLog.setTaskId(financeCoinGame.getId());
        financeCoinLog.setTaskName(financeCoinGame.getTaskName());
        financeCoinLog.setNum(-financeCoinGame.getNum());
        financeCoinLogMapper.insertSelective(financeCoinLog);

        lockResponseDto.setRetrunCode(CodeEnum.succ.getCode());
        redisLockDto.setRes(lockResponseDto);
    }

    @Override
    public void InviteUserCoinReward(Long userId) {
        //查询当前用户是不是第一次打卡
        Integer count = financeCoinGameLogMapper.selectSignByFirst(userId);
        if (count != null && count > 0) {
            return;
        }
        //查询当前用户是否存在被邀请人并是通过打卡邀请
        Long parentUserId = financeUserInfoMapper.selectInviterByUserId(userId,
            WeChatConstant.SIGN_ACTIVITY_CODE);
        if (parentUserId == null) {
            return;
        }
        //进行分发邀请人奖励
        FinanceCoinGame financeCoinGame = financeCoinGameMapper
            .selectByTaskType(Constant.GOLD_REWORD, GameType.activity.getCode());
        if (financeCoinGame == null) {
            financeCoinGame = new FinanceCoinGame();
            financeCoinGame.setTaskType(Constant.GOLD_REWORD);
            financeCoinGame.setTaskName("邀请人打卡奖励");
            financeCoinGame.setStatus(new Integer(1));
            financeCoinGame.setGameType(GameType.activity.getCode());
            financeCoinGame.setCreator("system");
            financeCoinGame.setUpdator("system");
            financeCoinGame.setNum(500);
            financeCoinGameMapper.insertSelective(financeCoinGame);
        }
        Integer totalCoin = financeCoinLogMapper.selectTotalGetCoinNumByUserId(parentUserId);
        FinanceCoinLog financeCoinLog = new FinanceCoinLog();
        financeCoinLog.setUserId(parentUserId);
        financeCoinLog.setTaskId(financeCoinGame.getId());
        financeCoinLog.setTaskName(financeCoinGame.getTaskName());
        financeCoinLog.setNum(financeCoinGame.getNum());
        financeCoinLogMapper.insertSelective(financeCoinLog);
        //推送模版信息给用户
        ThirdAccountInfo parentThirdAccountInfo = thirdAccountInfoRepository
            .queryByCondition(parentUserId);
        if (parentThirdAccountInfo == null) {
            logger.info("邀请人信息有误，userId=" + parentUserId);
            return;
        }
        WeiXinTemplateMessageSendRequest request = new WeiXinTemplateMessageSendRequest();
        request.setTemplate_id("pkbZK6NsJcbqkzx8O4UnQ0NgPk5zoE9pH2IRm4FFCcY");
        request.setUrl("https://finance.zhishush.cn/finance-h5/msite/#/punch");
        request.setTouser(parentThirdAccountInfo.getOpenId());
        Map<String, WeiXinTemplateData> data = Maps.newHashMap();
        data.put("first",
            WeiXinTemplateData.builder().value("您好，您的积分账户有了新的变动，具体如下：").color("#0000ff").build());
        data.put("keyword1",
            WeiXinTemplateData.builder()
                .value(DateUtils.format(new Date(), DateUtils.LONG_WEB_FORMAT)).color("#0000ff")
                .build());
        data.put("keyword2", WeiXinTemplateData.builder()
            .value(String.valueOf(financeCoinGame.getNum())).color("#0000ff").build());
        data.put("keyword3",
            WeiXinTemplateData.builder().value("您邀请的用户已经打卡，可获得500金币奖励").color("#0000ff").build());
        data.put("keyword4", WeiXinTemplateData.builder()
            .value(String.valueOf(totalCoin + financeCoinGame.getNum())).color("#0000ff").build());
        data.put("remark",
            WeiXinTemplateData.builder().value("查看详情，参与打卡活动吧").color("#0000ff").build());
        request.setData(data);
        request.setAccessToken(wechatService.getWechatPubAccessToken());
        WeiXinTemplateMessageSendResponse response = weiXinTemplateMessageClient
            .sendMessage(request);
        if ("0".equals(response.getErrcode())) {
            logger.info("open_id:{},微信模版消息发送成功", request.getTouser());
        } else {
            logger.error("open_id:{},微信模版消息发送失败:{}", request.getTouser(), response.getErrmsg());
        }
    }

    /* (non-Javadoc)
     * @see finance.biz.game.CoinBiz#pushRewardMsg()
     */
    @Override
    public ResponseResult<PushRewardVO> pushRewardMsg() {
        /**1.jwt 获取用户user_id **/
        Long userId = jwtService.getUserInfo().getId();
        //查询当前用户今日所得打卡奖励
        PushRewardVO vo = financeCoinLogMapper.selectSignCoinRewardByUid(userId,
            Constant.EARLY_SIGN, GameType.activity.getCode());
        return ResponseResult.success(vo);
    }

}
