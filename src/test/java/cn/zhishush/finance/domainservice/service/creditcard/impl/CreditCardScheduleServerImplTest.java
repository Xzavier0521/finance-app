package cn.zhishush.finance.domainservice.service.creditcard.impl;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.core.dal.dao.user.UserInviteInfoDAO;
import cn.zhishush.finance.core.dal.dataobject.product.CreditCardApplyInfoDO;
import cn.zhishush.finance.core.dal.dataobject.user.UserInviteInfoDO;
import cn.zhishush.finance.domainservice.service.creditcard.CreditCardScheduleServer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditCardScheduleServerImplTest {
@Resource
private CreditCardScheduleServer scheduleServer;
    @Resource
    private UserInviteInfoDAO userInviteInfoDAO;
    @Test
    public void query4Page() {
//        Long userId = jwtService.getUserInfo().getId();
        Page< UserInviteInfoDO > page = new Page<>(1, (long) 1l);
        List<Long> parentIdList = new ArrayList<>();
        parentIdList.add(1398L);
        List<UserInviteInfoDO> userInviteInfoDOList = userInviteInfoDAO.selectByParentIds(parentIdList,page,"");
        log.info("得到的结果为{}",userInviteInfoDOList);
        //根据传来的参数查找对应的好友

        //根据查到的好友信息返回相应的值
//        CreditCardApplyInfoDO creditCardApplyInfoDO = creditCardApplyInfoDAO.selectByPrimaryKey(1398L);


    }
}