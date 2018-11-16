package finance.web.controller.response;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

import finance.api.model.response.ResponseResult;
import finance.api.model.vo.activity.CoinGameVO;
import finance.core.common.enums.ReturnCode;
import finance.core.common.util.ConvertBeanUtil;
import finance.core.common.util.ResponseResultUtils;
import finance.domain.activity.CoinGame;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: ActivityCoinGameQueryBuilder.java, v0.1 2018/11/15 9:24 PM PM lili Exp $
 */
public class ActivityCoinGameQueryBuilder {

    public static ResponseResult<List<CoinGameVO>> build(List<CoinGame> coinGames) {

        ResponseResult<List<CoinGameVO>> responseResult;
        if (CollectionUtils.isNotEmpty(coinGames)) {
            return ResponseResultUtils.error(ReturnCode.SYS_ERROR);
        }
        List<CoinGameVO> coinGameVOList = Lists.newArrayListWithCapacity(coinGames.size());
        CoinGameVO coinGameVO;
        for (CoinGame coinGame : coinGames) {
            coinGameVO = new CoinGameVO();
            ConvertBeanUtil.copyBeanProperties(coinGame, coinGameVO);
            coinGameVOList.add(coinGameVO);
        }
        responseResult = ResponseResult.success(coinGameVOList);
        return responseResult;
    }
}
