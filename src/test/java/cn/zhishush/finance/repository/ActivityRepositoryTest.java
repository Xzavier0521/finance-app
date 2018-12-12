package cn.zhishush.finance.repository;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import cn.zhishush.finance.domainservice.repository.activity.RedEnvelopeRepository;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableTransactionManagement
public class ActivityRepositoryTest {

    @Resource
    private RedEnvelopeRepository redEnvelopeRepository;

    @Test
    public void testQuery() {

        redEnvelopeRepository.querySingleDetail(969L, "0001");
    }
}
