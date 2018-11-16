package finance.coin;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import finance.core.common.constants.RedEnvelopConstant;
import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import finance.api.model.base.Page;
import finance.api.model.response.ResponseResult;
import finance.api.model.vo.activity.CoinGameVO;
import finance.api.model.vo.activity.RedEnvelopeRainDataVO;
import finance.api.model.vo.activity.UserCurrentRankingVO;

import static finance.core.common.constants.RedEnvelopConstant.RED_ENVELOPE_RAIN_CODE;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: RestObjectTest.java, v0.1 2018/11/16 10:01 AM PM lili Exp $
 */
@Slf4j
public class RestObjectTest {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws JsonProcessingException {

        List<CoinGameVO> coinGameVOList = new ArrayList<>();

        CoinGameVO coinGameVO1 = new CoinGameVO();
        coinGameVO1.setGameCode("10001");
        coinGameVO1.setIsPayCoin(true);
        coinGameVOList.add(coinGameVO1);
        CoinGameVO coinGameVO2 = new CoinGameVO();
        coinGameVO2.setGameCode("10001");
        coinGameVO2.setIsPayCoin(true);
        coinGameVOList.add(coinGameVO2);
        ResponseResult<List<CoinGameVO>> responseResult = ResponseResult.success(coinGameVOList);

        String json = objectMapper.writeValueAsString(responseResult);
        log.info("{}", json);
        Page<RedEnvelopeRainDataVO> page = new Page<>(10, 1L);
        List<RedEnvelopeRainDataVO> dataVOList = new ArrayList<>();
        dataVOList.add(RedEnvelopeRainDataVO.builder().build());
        page.setDataList(dataVOList);
        page.setTotalCount(1L);
        ResponseResult<Page<RedEnvelopeRainDataVO>> result = ResponseResult.success(page);
        String json1 = objectMapper.writeValueAsString(result);
        log.info(json1);
        //
        Page<UserCurrentRankingVO> userCurrentRankingVOPage = new Page<>(20, 1L);
        userCurrentRankingVOPage.setTotalCount(1L);
        List<UserCurrentRankingVO> userCurrentRankingVOS = new ArrayList<>();
        UserCurrentRankingVO userCurrentRankingVO = new UserCurrentRankingVO();
        userCurrentRankingVOS.add(userCurrentRankingVO);
        userCurrentRankingVOPage.setDataList(userCurrentRankingVOS);
        ResponseResult<Page<UserCurrentRankingVO>> res = ResponseResult
            .success(userCurrentRankingVOPage);
        String json2 = objectMapper.writeValueAsString(res);
        log.info(json2);
    }

}