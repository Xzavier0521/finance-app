package finance.domainservice.service.gift;

import java.util.List;

import finance.api.model.base.Page;
import finance.api.model.base.XMap;
import finance.api.model.response.ResponseResult;
import finance.api.model.vo.gift.ExchangeGoodsVO;
import finance.domain.dto.CoinLockParamDto;
import finance.domain.dto.CoinLockResponseDto;
import finance.domain.dto.RedisLockDto;
import finance.core.dal.dataobject.GiftInfoDO;

/**
 * @program: finance-server
 * @description: ${description}
 * @author: MORUIHAI
 * @create: 2018-08-29 15:47
 **/
public interface GiftBiz {
    /**
     * 功能描述:礼物列表展示
     * 
     * @author: moruihai
     * @date: 2018/8/29 15:53
     * @param: [financeGiftInfoPage]
     * @return: java.util.List<finance.model.vo.ExchangeGoodsVO>
     */
    List<ExchangeGoodsVO> queryCoinGoodsList(Page<GiftInfoDO> financeGiftInfoPage);

    /**
     * 功能描述:商品兑换
     * 
     * @author: moruihai
     * @date: 2018/8/29 16:13
     * @param: [paramMap]
     * @return: finance.model.finance.ResponseResult<java.lang.Boolean>
     */
    ResponseResult<Boolean> exchangeGoods(XMap paramMap);

    void dealCoinTask(RedisLockDto<CoinLockParamDto, CoinLockResponseDto> redisLockDto);
}
