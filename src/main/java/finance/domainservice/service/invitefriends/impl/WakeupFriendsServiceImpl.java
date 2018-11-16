package finance.domainservice.service.invitefriends.impl;

import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import finance.api.model.response.ResponseResult;
import finance.core.common.enums.CodeEnum;
import finance.domain.user.UserInfo;
import finance.domain.dto.CoinLockParamDto;
import finance.domain.dto.CoinLockResponseDto;
import finance.domain.dto.RedisLockDto;
import finance.domainservice.repository.UserInfoRepository;
import finance.domainservice.repository.UserInviteRepository;
import finance.domainservice.service.AbstractCoinDealMulti;
import finance.domainservice.service.invitefriends.WakeupFriendsService;
import finance.core.dal.dao.FinanceCoinLogDAO;
import finance.core.dal.dataobject.FinanceCoinLog;

/**
 * <p>唤醒好友</p>
 *
 * @author lili
 * @version 1.0: WakeupFriendsServiceImpl.java, v 0.1 2018/9/28 下午9:01 lili Exp $
 */
@Service("wakeupFriendsService")
public class WakeupFriendsServiceImpl extends AbstractCoinDealMulti
                                      implements WakeupFriendsService {

    @Resource
    private UserInviteRepository userInviteRepository;
    @Resource
    private FinanceCoinLogDAO    financeCoinLogMapper;
    @Resource
    private UserInfoRepository   userInfoRepository;

    /**
     * 支付金币邀请好友
     *
     * @param parentUserId 邀请者用户id
     * @param userId       被邀请者用户id
     * @param coinNum      金币数
     * @return ResponseResult
     */
    @Override
    public ResponseResult<UserInfo> wakeup(Long parentUserId, Long userId, int coinNum) {
        String redisKey = String.valueOf(userId);
        CoinLockResponseDto lockResponseDto = null;
        CoinLockParamDto lockParamDto = new CoinLockParamDto();
        lockParamDto.setUserId(userId);
        lockParamDto.setCoinNum(coinNum);
        RedisLockDto<CoinLockParamDto, CoinLockResponseDto> redisLockDto = new RedisLockDto<>(
            redisKey, lockParamDto, lockResponseDto);
        this.handle(redisLockDto);
        if (!redisLockDto.hasLock()) {
            return ResponseResult.error(CodeEnum.joinFail);
        }
        String returnResult = redisLockDto.getRes().getRetrunCode();
        if (!CodeEnum.succ.getCode().equals(returnResult)) {
            return ResponseResult.error(CodeEnum.getParam().get(returnResult));
        }
        // 查询用户信息,手机号码明文
        List<UserInfo> userInfoList = userInfoRepository
            .queryByCondition(Lists.newArrayList(userId));
        UserInfo userInfo = new UserInfo();
        if (CollectionUtils.isNotEmpty(userInfoList)) {
            userInfo = userInfoList.get(0);
        }
        return ResponseResult.success(userInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void dealCoinTask(RedisLockDto redisLockDto) {
        CoinLockResponseDto lockResponseDto = new CoinLockResponseDto();
        CoinLockParamDto lockParamDto = (CoinLockParamDto) redisLockDto.getParam();
        Integer totalCoin = financeCoinLogMapper.selectCoinNumByUserId(lockParamDto.getUserId());
        boolean isFullCoin = false;
        if (Objects.nonNull(totalCoin) && totalCoin >= lockParamDto.getCoinNum()) {
            isFullCoin = true;
        }
        if (!isFullCoin) {
            lockResponseDto.setRetrunCode(CodeEnum.coinNumNotEnough.getCode());
            redisLockDto.setRes(lockResponseDto);
            return;
        }
        FinanceCoinLog financeCoinLog = new FinanceCoinLog();
        financeCoinLog.setUserId(lockParamDto.getUserId());
        financeCoinLog.setTaskId(1L);
        financeCoinLog.setTaskName("唤醒好友");
        financeCoinLog.setNum(-lockParamDto.getCoinNum());
        financeCoinLogMapper.insertSelective(financeCoinLog);
        // 更新支付金币标志
        userInviteRepository.updatePayCoinFlag(lockParamDto.getUserId());
        lockResponseDto.setRetrunCode(CodeEnum.succ.getCode());
        redisLockDto.setRes(lockResponseDto);
    }
}
