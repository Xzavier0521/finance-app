package finance.service;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import finance.domainservice.service.register.RegisterSendMessageService;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: WeiXinMessageSendServiceTest.java, v0.1 2018/10/24 4:24 PM lili Exp $
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableTransactionManagement
public class WeiXinMessageSendServiceTest {

    @Resource
    private RegisterSendMessageService registerSendMessageService;

    @Test
    public void test() {
        registerSendMessageService.sendMessage(1172L);
    }

}
