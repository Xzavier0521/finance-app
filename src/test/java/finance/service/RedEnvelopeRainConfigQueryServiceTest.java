package finance.service;

import java.time.LocalDateTime;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import finance.core.common.enums.RedEnvelopeRainTimeCodeEnum;
import finance.domainservice.service.activity.query.RedEnvelopeRainConfigQueryService;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: redEnvelopeRainConfigQueryServiceTest.java, v0.1 2018/11/16 10:37 PM PM lili Exp $
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableTransactionManagement
public class RedEnvelopeRainConfigQueryServiceTest {

    @Resource
    private RedEnvelopeRainConfigQueryService redEnvelopeRainConfigQueryService;

    @Test
    public void test() {
        RedEnvelopeRainTimeCodeEnum timeCodeEnum = redEnvelopeRainConfigQueryService
            .queryTimeCode("1001", LocalDateTime.now());
        log.info("timeCodeEnum:{}", timeCodeEnum);
    }
}
