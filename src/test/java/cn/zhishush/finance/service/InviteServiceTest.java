package cn.zhishush.finance.service;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.domain.team.InviteInfoAndIncome;
import cn.zhishush.finance.domainservice.service.user.query.UserInviteQueryService;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class InviteServiceTest {

    @Resource
    private UserInviteQueryService userInviteQueryService;

    @Test
    public void testQuery() {
        Page<InviteInfoAndIncome> infoAndIncomePage = userInviteQueryService
            .queryInviteInfoAndIncome(1L, 10, 1);
        log.error("{}", infoAndIncomePage);
    }
}
