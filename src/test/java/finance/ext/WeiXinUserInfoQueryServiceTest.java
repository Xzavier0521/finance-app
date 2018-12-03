package finance.ext;

import javax.annotation.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import finance.api.model.vo.weixin.WeiXinUserInfoDetailVO;
import finance.domain.user.UserInfo;
import finance.domainservice.service.wechat.WeiXinUserInfoQueryService;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: WeiXinUserInfoQueryServiceTest.java, v0.1 2018/12/2 1:13 PM PM lili Exp $
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableTransactionManagement
public class WeiXinUserInfoQueryServiceTest {
    private static ObjectMapper        objectMapper = new ObjectMapper();

    @Resource
    private WeiXinUserInfoQueryService weiXinUserInfoQueryService;

    @Test
    public void test() {
        try {

            UserInfo userInfo = new UserInfo();
            userInfo.setId(1345L);
            WeiXinUserInfoDetailVO weiXinUserInfoDetailVO = weiXinUserInfoQueryService
                .query(userInfo);
            log.info("weiXinUserInfoDetailVO:{}", weiXinUserInfoDetailVO);
          log.info("{}",  objectMapper.writeValueAsString(weiXinUserInfoDetailVO));
        } catch (final Exception e) {
            log.error("{}", ExceptionUtils.getStackTrace(e));
        }
    }
}
