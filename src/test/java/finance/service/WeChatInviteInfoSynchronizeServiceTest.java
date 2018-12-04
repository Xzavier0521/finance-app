package finance.service;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import finance.domainservice.service.wechat.WeChatInviteInfoSynchronizeService;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: WeChatInviteInfoSynchronizeServiceTest.java, v0.1 2018/12/4 1:35 PM PM lili Exp $
 */

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class WeChatInviteInfoSynchronizeServiceTest {

    @Resource
    private WeChatInviteInfoSynchronizeService weChatInviteInfoSynchronizeService;

    @Test
    public void test1() {
        weChatInviteInfoSynchronizeService.process();
    }
}
