package finance.web.controller.response;

import java.util.List;

import com.google.common.collect.Lists;

import finance.api.model.response.ResponseResult;
import finance.api.model.vo.activity.CoinGameVO;
import finance.core.common.util.ConvertBeanUtil;
import finance.domain.activity.CoinGame;

/**
 * <p>
 * 注释
 * </p>
 *
 * @author lili
 * @version 1.0: ActivityCoinGameQueryBuilder.java, v0.1 2018/11/15 9:24 PM PM
 *          lili Exp $
 */
public class ActivityCoinGameQueryBuilder {

	public static ResponseResult<List<CoinGameVO>> build(List<CoinGame> coinGames, List<String> gameCodes) {

		ResponseResult<List<CoinGameVO>> responseResult;
		List<CoinGameVO> coinGameVOList = Lists.newArrayListWithCapacity(gameCodes.size());
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
