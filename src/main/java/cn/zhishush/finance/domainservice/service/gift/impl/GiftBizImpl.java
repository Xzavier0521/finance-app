package cn.zhishush.finance.domainservice.service.gift.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import cn.zhishush.finance.core.dal.dao.coin.CoinLogDAO;
import cn.zhishush.finance.core.dal.dao.gift.GiftInfoDAO;
import cn.zhishush.finance.core.dal.dao.gift.UserGiftInfoDAO;
import cn.zhishush.finance.core.dal.dataobject.gift.GiftInfoDO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.base.XMap;
import cn.zhishush.finance.api.model.response.ResponseResult;
import cn.zhishush.finance.api.model.vo.gift.ExchangeGoodsVO;
import cn.zhishush.finance.core.common.enums.CodeEnum;
import cn.zhishush.finance.domain.dto.CoinLockParamDto;
import cn.zhishush.finance.domain.dto.CoinLockResponseDto;
import cn.zhishush.finance.domain.dto.RedisLockDto;
import cn.zhishush.finance.domainservice.service.AbstractCoinDealMulti;
import cn.zhishush.finance.domainservice.service.gift.GiftBiz;
import cn.zhishush.finance.domainservice.service.jwt.JwtService;
import cn.zhishush.finance.core.dal.dataobject.coin.CoinLogDO;
import cn.zhishush.finance.core.dal.dataobject.gift.UserGiftInfoDO;

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
    private GiftInfoDAO     financeGiftInfoMapper;
    @Resource
    private JwtService      jwtService;
    @Resource
    private CoinLogDAO      financeCoinLogMapper;
    @Resource
    private UserGiftInfoDAO financeUserGiftInfoMapper;

    @Override
    public List<ExchangeGoodsVO> queryCoinGoodsList(Page<GiftInfoDO> financeGiftInfoPage) {
        List<ExchangeGoodsVO> exchangeGoodsVOList = new ArrayList<ExchangeGoodsVO>();
        // 根据类型查主表数据
        List<GiftInfoDO> giftInfoDOList = financeGiftInfoMapper
            .selectGiftByPage(financeGiftInfoPage);

        if (giftInfoDOList != null && giftInfoDOList.size() > 0) {
            ExchangeGoodsVO exchangeGoodsVO = null;
            for (GiftInfoDO giftInfoDO : giftInfoDOList) {
                exchangeGoodsVO = new ExchangeGoodsVO();
                exchangeGoodsVO.setGoodsName(giftInfoDO.getGiftName());
                exchangeGoodsVO.setGoodsId(giftInfoDO.getId());
                exchangeGoodsVO.setBannerUrl(giftInfoDO.getBannerUrl());
                exchangeGoodsVO.setNeedCoinCount(giftInfoDO.getNeedCoinNum());
                exchangeGoodsVO.setThumbnailUrl(giftInfoDO.getThumbnailUrl());
                exchangeGoodsVOList.add(exchangeGoodsVO);
            }
        }
        return exchangeGoodsVOList;
    }

    @Override
    public ResponseResult<Boolean> exchangeGoods(XMap paramMap) {
        Long giftId = Long.valueOf(paramMap.get("goodsId").toString());
        /** 1.jwt 获取用户user_id **/
        Long userId = jwtService.getUserInfo().getId();
        /** 2.商品信息 **/
        GiftInfoDO giftInfoDO = financeGiftInfoMapper.selectByPrimaryKey(giftId);
        if (giftInfoDO == null) {
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
        /** 1.获取数据 **/
        CoinLockParamDto lockParamDto = (CoinLockParamDto) redisLockDto.getParam();
        /** 2.商品信息 **/
        GiftInfoDO giftInfoDO = financeGiftInfoMapper.selectByPrimaryKey(lockParamDto.getGiftId());
        /** 3.判断金币是否足够 **/
        Integer totalCoin = financeCoinLogMapper.selectCoinNumByUserId(lockParamDto.getUserId());
        Boolean isFullCoin = true;
        if (totalCoin != null) {
            if (totalCoin.intValue() < giftInfoDO.getNeedCoinNum().intValue()) {
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

        /** 4.插入数据 **/
        UserGiftInfoDO financeUserGiftInfo = new UserGiftInfoDO();
        financeUserGiftInfo.setUserId(lockParamDto.getUserId());
        financeUserGiftInfo.setGiftId(lockParamDto.getGiftId());
        financeUserGiftInfo.setGiftStatus(0);
        financeUserGiftInfoMapper.insertSelective(financeUserGiftInfo);

        CoinLogDO coinLogDO = new CoinLogDO();

        coinLogDO.setUserId(lockParamDto.getUserId());
        // 为防止和任务表里出现taskid重复，礼品的统一设为0，后续如有其他场景可以考虑表内增字段与区分。
        coinLogDO.setTaskId(0l);
        coinLogDO.setTaskName(giftInfoDO.getGiftName());
        coinLogDO.setNum(-giftInfoDO.getNeedCoinNum());
        financeCoinLogMapper.insertSelective(coinLogDO);

        lockResponseDto.setRetrunCode(CodeEnum.succ.getCode());
        redisLockDto.setRes(lockResponseDto);
    }
}
