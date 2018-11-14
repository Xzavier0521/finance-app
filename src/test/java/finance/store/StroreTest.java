package finance.store;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import finance.domainservice.service.aliyunOss.StoreClient;

/**
 * @author yaolei
 * @Title: StroreTest
 * @ProjectName finance-app
 * @Description: TODO
 * @date 2018/7/7下午3:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StroreTest {

    @Autowired
    public StoreClient client;

    @Test
    public void testStore(){
        client.putObject("test","test");
    }
}
