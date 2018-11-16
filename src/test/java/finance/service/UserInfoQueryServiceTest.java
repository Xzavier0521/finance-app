package finance.service;

import com.google.common.collect.Lists;
import finance.domain.coin.CoinLog;
import finance.domainservice.repository.CoinLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: UserInfoQueryServiceTest.java, v 0.1 2018/9/28 上午10:39 lili Exp $
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoQueryServiceTest {
    @Resource
    private CoinLogRepository coinLogRepository;

    @Test
    public  void testQuery(){

        List<Long> userIds = Lists.newArrayList();
        userIds.add(1L);
        List<CoinLog> coinLogList = coinLogRepository.queryLatestLog(userIds);
        log.info("coinLogList:{}",coinLogList);

    }
}
