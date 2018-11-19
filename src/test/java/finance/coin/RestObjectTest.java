package finance.coin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import finance.api.model.base.Page;
import finance.api.model.response.ResponseResult;
import finance.api.model.vo.activity.CoinGameVO;
import finance.api.model.vo.activity.RedEnvelopeRainDataVO;
import finance.api.model.vo.activity.UserCurrentRankingVO;
import finance.core.common.enums.RedEnvelopeRainTimeCodeEnum;
import finance.domainservice.service.activity.RedEnvelopeRainRankingRewardService;
import finance.domainservice.service.activity.RedEnvelopeRainRankingSyncService;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: RestObjectTest.java, v0.1 2018/11/16 10:01 AM PM lili Exp $
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableTransactionManagement
public class RestObjectTest {
    private static ObjectMapper                 objectMapper = new ObjectMapper();

    @Resource
    private RedEnvelopeRainRankingRewardService redEnvelopeRainRankingRewardService;

    @Resource
    private RedEnvelopeRainRankingSyncService   redEnvelopeRainRankingSyncService;

    @Test
    public void testJson() throws JsonProcessingException {

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

    @Test
    public void testReward() {

        redEnvelopeRainRankingRewardService.process(LocalDate.now(), "1001",
            RedEnvelopeRainTimeCodeEnum.FIRST);
       // redEnvelopeRainRankingSyncService.process();
    }

}