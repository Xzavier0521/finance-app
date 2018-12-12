package cn.zhishush.finance.ext.integration.datapay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.zhishush.finance.core.common.constants.Constant;
import cn.zhishush.finance.core.common.util.LogUtil;
import cn.zhishush.finance.core.dal.dao.log.IdAuthInfoLogDAO;
import cn.zhishush.finance.core.dal.dataobject.account.IdAuthInfoLogDO;
import cn.zhishush.finance.domain.dto.DataPayAuthResDto;
import cn.zhishush.finance.domain.dto.UserBankCardDto;
import cn.zhishush.finance.domainservice.service.auth.impl.AuthServiceImpl;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cdp.product.security.decode.CdpDecryptUtil;
import com.cdp.product.security.encode.CdpEncryptUtil;
import com.cdp.product.security.sign.CdpSignUtil;

/**
 * 调用数据宝服务
 * 
 * @author panzhongkang
 * @date 2018/8/24 9:31
 */
@Service
public class DataPayClient {
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Resource
    private IdAuthInfoLogDAO authInfoLogMapper;

    /**
     * 数据宝提供的 key
     */
    @Value("${bank.card.auth.key}")
    private String              key;
    /**
     * 秘钥
     */
    @Value("${bank.card.auth.secretKey}")
    private String              secretKey;
    /**
     * 接口host
     */
    @Value("${bank.card.auth.host}")
    private String              host;

    public static String post(String url, Map<String, String> params) throws Exception {
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        return PostHttpRequest(url, pairs);
    }

    public static String PostHttpRequest(String Url, List<NameValuePair> params) throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        // 超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(300000)
            .setConnectTimeout(300000).build();
        String result = null;
        try {
            HttpPost request = new HttpPost(Url);
            request.setConfig(requestConfig);
            request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            HttpResponse respones = client.execute(request);
            if (respones.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(respones.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
        return result;
    }

    private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, String> params) {
        ArrayList<NameValuePair> pairs = new ArrayList<>();
        if (params == null || params.size() == 0) {
            return pairs;
        }
        for (Map.Entry<String, String> param : params.entrySet()) {
            Object value = param.getValue();
            if (value instanceof String[]) {
                String[] values = (String[]) value;
                for (String v : values) {
                    pairs.add(new BasicNameValuePair(param.getKey(), v));
                }
            } else {
                if (value instanceof Integer) {
                    value = Integer.toString((Integer) value);
                } else if (value instanceof Long) {
                    value = Long.toString((Long) value);
                }
                pairs.add(new BasicNameValuePair(param.getKey(), (String) value));
            }
        }
        return pairs;
    }

    public DataPayAuthResDto bankCardAuth(UserBankCardDto bankCardDto) {

        // 请求参数
        Map<String, String> params = new HashMap<>();
        String url = host + "/communication/personal/1887";
        String result = null;
        DataPayAuthResDto authResDto = null;
        try {
            // 入参集合 针对入参 value 值进行加密
            params.put("name", CdpEncryptUtil.aesEncrypt(bankCardDto.getAccountName(), secretKey));
            params.put("idcard", CdpEncryptUtil.aesEncrypt(bankCardDto.getIdNum(), secretKey));
            params.put("acc_no", CdpEncryptUtil.aesEncrypt(bankCardDto.getAccountNo(), secretKey));
            params.put("mobile", CdpEncryptUtil.aesEncrypt(bankCardDto.getMobileNum(), secretKey));
            params.put("timestamp",
                CdpEncryptUtil.aesEncrypt(System.currentTimeMillis() + "", secretKey));

            String sign = CdpSignUtil.sign(params);
            params.put("sign", sign);
            // 输入数据宝提供的 key ,key不参与加密
            params.put("key", key);

            result = this.post(url, params);
            authResDto = JSON.parseObject(result, DataPayAuthResDto.class);
            if (Constant.authSucc.equals(authResDto.getCode())) {
                if (authResDto.getData() != null) {
                    authResDto.setData(CdpDecryptUtil.aesDecrypt(authResDto.getData(), secretKey));
                }
            }
        } catch (Exception e) {
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("name", bankCardDto.getAccountName());
            paramMap.put("idcard", bankCardDto.getIdNum());
            paramMap.put("acct_no", bankCardDto.getAccountNo());
            logger.error(LogUtil.getFormatLog(paramMap, "银行卡验证异常"), e);
        } finally {
            logger.info(LogUtil.getFormatLog(
                "url:" + url + String.format(";params:", params) + ";result:" + result, "银行卡验证"));
            // 记录日志
            IdAuthInfoLogDO authInfoLog = new IdAuthInfoLogDO();
            authInfoLog.setUserId(bankCardDto.getUserId());
            authInfoLog.setRealName(bankCardDto.getAccountName());
            authInfoLog.setIdNum(bankCardDto.getIdNum());
            authInfoLog.setAccountNo(bankCardDto.getAccountNo());
            if (authResDto != null) {
                authInfoLog.setCode(authResDto.getCode());
                authInfoLog.setMessage(authResDto.getMessage());
                if (Constant.authSucc.equals(authResDto.getCode())) {
                    String data = authResDto.getData();
                    Map<String, Object> dataMap = (Map) JSON.parse(data);
                    authInfoLog.setDataState(String.valueOf(dataMap.get("state")));
                }
                authInfoLog.setSeqNo(authResDto.getSeqNo());
            }
            authInfoLogMapper.insertSelective(authInfoLog);
        }
        return authResDto;
    }
}
