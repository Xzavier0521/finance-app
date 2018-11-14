package finance.domainservice.service.gift.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import finance.api.model.base.Page;
import finance.api.model.base.XMap;
import finance.api.model.response.ResponseResult;
import finance.api.model.vo.ExchangeGoodsVO;
import finance.core.common.enums.CodeEnum;
import finance.domain.dto.CoinLockParamDto;
import finance.domain.dto.CoinLockResponseDto;
import finance.domain.dto.RedisLockDto;
import finance.domainservice.service.AbstractCoinDealMulti;
import finance.domainservice.service.gift.GiftBiz;
import finance.domainservice.service.jwt.JwtService;
import finance.mapper.FinanceCoinLogDAO;
import finance.mapper.FinanceGiftInfoDAO;
import finance.mapper.FinanceUserGiftInfoDAO;
import finance.model.po.FinanceCoinLog;
import finance.model.po.FinanceGiftInfo;
import finance.model.po.FinanceUserGiftInfo;

/**
 * @program: finance-server
 *
 * @description: 礼物活动
 *
 * @author: MORUIHAI
 *
 * @create: 2018-08-29 15:48
 **/
@Service
public class GiftBizImpl extends AbstractCoinDealMulti implements GiftBiz {
    @Resource
    private FinanceGiftInfoDAO     financeGiftInfoMapper;
    @Resource
    private JwtService             jwtService;
    @Resource
    private FinanceCoinLogDAO      financeCoinLogMapper;
    @Resource
    private FinanceUserGiftInfoDAO financeUserGiftInfoMapper;

    @Override
    public List<ExchangeGoodsVO> queryCoinGoodsList(Page<FinanceGiftInfo> financeGiftInfoPage) {
        List<ExchangeGoodsVO> exchangeGoodsVOList = new ArrayList<ExchangeGoodsVO>();
        //根据类型查主表数据
        List<FinanceGiftInfo> financeGiftInfoList = financeGiftInfoMapper
            .selectGiftByPage(financeGiftInfoPage);

        if (financeGiftInfoList != null && financeGiftInfoList.size() > 0) {
            ExchangeGoodsVO exchangeGoodsVO = null;
            for (FinanceGiftInfo financeGiftInfo : financeGiftInfoList) {
                exchangeGoodsVO = new ExchangeGoodsVO();
                exchangeGoodsVO.setGoodsName(financeGiftInfo.getGiftName());
                exchangeGoodsVO.setGoodsId(financeGiftInfo.getId());
                exchangeGoodsVO.setBannerUrl(financeGiftInfo.getBannerUrl());
                exchangeGoodsVO.setNeedCoinCount(financeGiftInfo.getNeedCoinNum());
                exchangeGoodsVO.setThumbnailUrl(financeGiftInfo.getThumbnailUrl());
                exchangeGoodsVOList.add(exchangeGoodsVO);
            }
        }
        return exchangeGoodsVOList;
    }

    @Override
    public ResponseResult<Boolean> exchangeGoods(XMap paramMap) {
        Long giftId = Long.valueOf(paramMap.get("goodsId").toString());
        /**1.jwt 获取用户user_id **/
        Long userId = jwtService.getUserInfo().getId();
        /**2.商品信息 **/
        FinanceGiftInfo financeGiftInfo = financeGiftInfoMapper.selectByPrimaryKey(giftId);
        if (financeGiftInfo == null) {
            return ResponseResult.error(CodeEnum.giftNotExist);
        }
        CoinLockParamDto lockParamDto = new CoinLockParamDto();
        lockParamDto.setUserId(userId);
        lockParamDto.setGiftId(giftId);
        String redisKey = userId + "";
        String returnResult = "";
        CoinLockResponseDto lockResponseDto = null;
        RedisLockDto<CoinLockParamDto, CoinLockResponseDto> redisLockDto = new RedisLockDto<>(
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
    public void dealCoinTask(RedisLockDto redisLockDto) {
        CoinLockResponseDto lockResponseDto = new CoinLockResponseDto();
        /**1.获取数据 **/
        CoinLockParamDto lockParamDto = (CoinLockParamDto) redisLockDto.getParam();
        /**2.商品信息 **/
        FinanceGiftInfo financeGiftInfo = financeGiftInfoMapper
            .selectByPrimaryKey(lockParamDto.getGiftId());
        /** 3.判断金币是否足够 **/
        Integer totalCoin = financeCoinLogMapper.selectCoinNumByUserId(lockParamDto.getUserId());
        Boolean isFullCoin = true;
        if (totalCoin != null) {
            if (totalCoin.intValue() < financeGiftInfo.getNeedCoinNum().intValue()) {
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
        FinanceUserGiftInfo financeUserGiftInfo = new FinanceUserGiftInfo();
        financeUserGiftInfo.setUserId(lockParamDto.getUserId());
        financeUserGiftInfo.setGiftId(lockParamDto.getGiftId());
        financeUserGiftInfo.setGiftStatus(0);
        financeUserGiftInfoMapper.insertSelective(financeUserGiftInfo);

        FinanceCoinLog financeCoinLog = new FinanceCoinLog();

        financeCoinLog.setUserId(lockParamDto.getUserId());
        //为防止和任务表里出现taskid重复，礼品的统一设为0，后续如有其他场景可以考虑表内增字段与区分。
        financeCoinLog.setTaskId(0l);
        financeCoinLog.setTaskName(financeGiftInfo.getGiftName());
        financeCoinLog.setNum(-financeGiftInfo.getNeedCoinNum());
        financeCoinLogMapper.insertSelective(financeCoinLog);

        lockResponseDto.setRetrunCode(CodeEnum.succ.getCode());
        redisLockDto.setRes(lockResponseDto);
    }
}
