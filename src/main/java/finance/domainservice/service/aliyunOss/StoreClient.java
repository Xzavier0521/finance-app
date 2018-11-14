package finance.domainservice.service.aliyunOss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * @author yaolei
 * @Title: StoreClient
 * @ProjectName finance-app
 * @Description: aliyunOss存储
 * @date 2018/7/6下午5:59
 */
@Service
public class StoreClient {

    private static final Logger logger = LoggerFactory.getLogger(StoreClient.class);

    @Value("${oss.endpoint}")
    private  String endpoint;
    @Value("${oss.accessKeyId}")
    private  String accessKeyId;
    @Value("${oss.accessKeySecret}")
    private  String accessKeySecret;
    @Value("${oss.bucketName}")
    private  String bucketName;

    private OSS ossClient;

    @PostConstruct
    public void init(){
        synchronized (StoreClient.class){
            if(ossClient == null){
                this.ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            }
        }
    }

    /**
     * 上传字符串
     * @param name
     * @param str
     * @return
     */
    public String putObject(String name,String str){

        try {
            logger.info("#putString-name={},str={}",name,str);
            PutObjectResult res = ossClient.putObject(bucketName, name, new ByteArrayInputStream(str.getBytes()));
        } catch (Exception e) {
            logger.info("#putString=e{}",e);
            return null;
        }
        return getUrl(name);
    }

    /**
     * 上传字符数组
     * @param name
     * @param bytes
     * @return
     */
    public String putObject(String name,byte[] bytes){
        try {
            logger.info("#putObject-name={}",name);
            PutObjectResult res = ossClient.putObject(bucketName, name, new ByteArrayInputStream(bytes));
        } catch (Exception e) {
            logger.info("#putString=e{}",e);
            return null;
        }
        return getUrl(name);
    }

    /**
     * 上传流信息
     * @param name
     * @param inputStream
     * @return
     */
    public String putObject(String name,InputStream inputStream){
        try {
            logger.info("#putObject-name={}",name);
            PutObjectResult res = ossClient.putObject(bucketName,name,inputStream);
        } catch (Exception e) {
            logger.info("#putString=e{}",e);
            return null;
        }
        return getUrl(name);
    }


    public String getUrl(String key) {
        // 设置URL过期时间为1h  3600l* 1000
        Date expiration = new Date(new Date().getTime() + 3600l*1000);
        URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }

}
