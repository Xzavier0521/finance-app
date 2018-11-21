package finance.repository;

import finance.domain.weixin.InviteOpenInfo;
import finance.domainservice.repository.InviteOpenInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: InviteOpeniInfotest.java, v0.1 2018/11/21 5:53 PM PM lili Exp $
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableTransactionManagement
public class InviteOpenInfotest {

    @Resource
    private InviteOpenInfoRepository inviteOpenInfoRepository;

    @Test
    public  void test(){
        InviteOpenInfo inviteOpenInfo = inviteOpenInfoRepository
                .queryInviteOpenInfo("oI2Hw1TbLcEiHTFOccBEKMCwEOHo");
        log.error("inviteOpenInfo:{}",inviteOpenInfo);
    }
}
