package finance.store;

import finance.core.common.util.HttpClientUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import finance.domainservice.service.aliyunOss.StoreClient;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private StoreClient client;

    @Test
    public void testStore(){
        InputStream inputStream = HttpClientUtil.getImageStream("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQHM7zwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyVnlxXzh6Y1Y4RlAxNW9QcmhzY3AAAgRY5vNbAwQAjScA");
        client.init();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String picName = formatter.format(new Date()) + "wechatQR.jpg";
        String qrUrl = client.putObject(picName, inputStream);
        System.out.println(qrUrl);
    }
}
