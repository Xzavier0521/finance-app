package cn.zhishush.finance.coin;

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
import com.google.common.collect.Lists;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.response.ResponseResult;
import cn.zhishush.finance.api.model.vo.activity.CoinGameVO;
import cn.zhishush.finance.api.model.vo.activity.RedEnvelopeRainDataVO;
import cn.zhishush.finance.api.model.vo.activity.UserCurrentRankingVO;
import cn.zhishush.finance.api.model.vo.creditCard.BankInfoVO;
import cn.zhishush.finance.core.common.enums.RedEnvelopeRainTimeCodeEnum;
import cn.zhishush.finance.core.common.util.MD5Util;
import cn.zhishush.finance.domainservice.service.activity.RedEnvelopeRainRankingRewardService;
import cn.zhishush.finance.domainservice.service.activity.RedEnvelopeRainRankingSyncService;

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
        //
        Page<BankInfoVO> bankInfoPage = new Page<>(20, 1L);
        List<BankInfoVO> bankInfoList = Lists.newArrayList();
        bankInfoList.add(BankInfoVO.builder().bankCode("0001").bankName("招商银行")
            .bankLogoUrl("www.xxxx.com").bankTag("易下").build());
        bankInfoPage.setDataList(bankInfoList);
        ResponseResult<Page<BankInfoVO>> pageResponseResult = ResponseResult.success(bankInfoPage);

        String json3 = objectMapper.writeValueAsString(pageResponseResult);
        log.info("json3:{}", json3);

    }

    @Test
    public void testReward() {

        redEnvelopeRainRankingRewardService.process(LocalDate.now(), "1001",
            RedEnvelopeRainTimeCodeEnum.FIRST);
        // redEnvelopeRainRankingSyncService.process();
    }

    @Test
    public void test2() {
        String md5Msg = "deptId=500087&userId=0&realName=测试&mobile=18112345678&certcode=18112345678";
        log.info("md5Msg:{}", MD5Util.sign(md5Msg, "", "utf-8"));
    }
}