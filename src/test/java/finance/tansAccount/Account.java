package finance.tansAccount;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import finance.core.dal.dao.UserAccountDAO;
import finance.domainservice.service.finance.tansAccount.TransAccBiz;

/**
 * @author yaolei
 * @Title: Account
 * @ProjectName finance-app
 * @Description: TODO
 * @date 2018/7/6下午4:58
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class Account {

    @Autowired
    public TransAccBiz acc;
    @Autowired
    public UserAccountDAO accountMapper;


    @Test
    public void testUpdateNameByUserId() {
//        accountMapper.updateByUserId(3l,"测试更改");
    }

}
