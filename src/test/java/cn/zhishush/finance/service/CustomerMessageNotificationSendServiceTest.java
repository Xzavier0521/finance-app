package cn.zhishush.finance.service;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.zhishush.finance.domainservice.service.wechat.CustomerMessageNotificationSendService;

import javax.annotation.Resource;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: CustomerMessageNotificationSendServiceTest.java, v0.1 2018/12/4 5:42 PM PM lili Exp $
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerMessageNotificationSendServiceTest {

    @Resource
    private CustomerMessageNotificationSendService customerMessageNotificationSendService;

    @Test
    public void test() {
        customerMessageNotificationSendService.process();
    }
}
