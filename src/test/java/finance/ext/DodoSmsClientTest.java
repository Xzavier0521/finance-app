package finance.ext;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import finance.api.model.response.BasicResponse;
import finance.ext.integration.sms.DodoSmsClient;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: DodoSmsClientTest.java, v0.1 2018/11/25 7:43 PM PM lili Exp $
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableTransactionManagement
public class DodoSmsClientTest {

    @Resource
    private DodoSmsClient dodoSmsClient;

    @Test
    public void testSend() {

        BasicResponse response = dodoSmsClient.sendSms("18101625436", "短信通道测试");
    }
}
