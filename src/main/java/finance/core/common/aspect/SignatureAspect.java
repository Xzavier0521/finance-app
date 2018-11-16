package finance.core.common.aspect;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.codec.digest.DigestUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import finance.core.common.exception.SignatureException;
import finance.domainservice.service.jwt.JwtService;
import finance.core.common.util.LogUtil;

/**
 * 签名拦截验证.
 * @author hewenbin
 * @version v1.0 2018年7月3日 下午4:25:37 hewenbin
 */
@Order(-1)
@Aspect
@Component
@Slf4j
public class SignatureAspect {

    private final static Logger logger = LoggerFactory.getLogger(SignatureAspect.class);

    @Value("${system.signature.switch}")
    private String              signatureSwitch;
    @Value("${signature.timeoutseconds}")
    private int                 signatureTimeout;
    @Value("${signature.cache.key.prefix}")
    private String              signatureCacheKeyPrefix;

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private JwtService          jwtService;

    /**
      * 只拦截api包下以Api结尾的文件（注意区分大小写）
      */
    @Around("execution(public * finance.web.controller..*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object res = null;
        try {
            //修改线程名称
            Thread.currentThread().setName(UUID.randomUUID().toString().replace("-", ""));

            // 签名认证
            boolean signatureRes = this.before(joinPoint);
            if (!signatureRes) {
                res = "signature fail";
                throw new SignatureException("signature fail");
            }

            // 执行主操作`
            res = joinPoint.proceed();
        } finally {
            // 移除内存中的缓存值
            jwtService.removeUserInfo();

            // 打印请求出参
            logger.info("httpResponseInfo:{}", res == null ? null : res.toString());
        }
        return res;
    }

    /**
     * 参数验签.
     * @param joinPoint
     * @return
     * @throws SignatureException
     * @author hewenbin
     * @version SignatureAspect.java, v1.0 2018年7月17日 上午10:57:10 hewenbin
     * @see http://git.nonobank.com/html5-maizi/wiki/blob/master/nodejs-api-security-design.md
     */
    public Boolean before(JoinPoint joinPoint) throws SignatureException {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
            .getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // monitor、获取系统时间接口过滤掉，不验证
        String path = request.getServletPath();
        if ("/monitor".equals(path) || "/systemCurrentTime".equals(path)
            || path.startsWith("/book/") // XXX   骗审接口跳过
        ) {
            // 跳过验签
            logger.info(LogUtil.getFormatLog(path, "ignore signature"));
            return true;
        }

        String method = request.getMethod();
        String paramStr = "";
        /*
         * 验签说明：
         * 1.当前的入参中不会有数组；
         * 2.入参的json只有一级；
         * 3.如果入参 json 需要多级，则下一级的数据作为一个整体的jsonString 来传递，不是json格式；
         */
        Object[] paramsArray = joinPoint.getArgs();
        if ("POST".equalsIgnoreCase(method)) {
            if (paramsArray != null && paramsArray.length > 0) {
                for (int i = 0; i < paramsArray.length; i++) {
                    Object param = paramsArray[i];
                    if (param instanceof HttpServletRequest
                        || param instanceof HttpServletResponse) {
                        // request 和 response 忽略
                        continue;
                    }
                    // 只要接收到body 就结束
                    paramStr = JSON.toJSONString(param);
                    break;

                }
            }
        } else if ("GET".equalsIgnoreCase(method)) {
            Enumeration<String> pNames = request.getParameterNames();
            Map<String, String> pMap = new HashMap<>();
            while (pNames.hasMoreElements()) {
                String pName = pNames.nextElement();
                pMap.put(pName, request.getParameter(pName));
            }
            paramStr = JSON.toJSONString(pMap);
        } else {
            // XXX 其他类型的请求暂时没有
        }
        // 打印入参日志
        Map<String, String> logMap = new HashMap<>();
        logMap.put("jwt", request.getHeader("jwt"));
        logMap.put("httpPath", path);
        logMap.put("httpMethod", method);
        logMap.put("httpParams", paramStr);
        logger.info("httpRuestInfo:{}", logMap.toString());

        //
        if (!"1".equals(signatureSwitch)) {
            // 跳过验签
            logger.info(LogUtil.getFormatLog(null, "signature.switch is 0"));
            return true;
        }

        TreeMap<String, String> jsonMap = null;
        try {
            jsonMap = JSON.parseObject(paramStr, new TypeReference<TreeMap<String, String>>() {
            });
        } catch (Exception e) {
            throw new TypeMismatchException(paramStr, TypeReference.class);
        }

        String timestampStr = jsonMap.get("timestamp");
        String noncestr = jsonMap.get("noncestr");
        String signature = jsonMap.get("signature");
        String appId = jsonMap.get("appId");
        if (StringUtils.isEmpty(timestampStr) || StringUtils.isEmpty(noncestr)
            || StringUtils.isEmpty(signature) || StringUtils.isEmpty(appId)) {
            logger.info(LogUtil.getFormatLog(null, "lock necessary signature param"));
            return false;
        }

        String appKey = DigestUtils.md5Hex(appId).substring(7, 23);

        // 验证 timestamp 是否过期
        Long timestamp = Long.valueOf(timestampStr);
        if ((System.currentTimeMillis() - timestamp) > signatureTimeout * 1000) {
            logger.info(LogUtil.getFormatLog("timestamp:" + timestamp, "timestamp is timeout"));
            return false;
        }

        // 验证 signature 是否有效
        // 不对appId、signature验签
        jsonMap.remove("appId");
        jsonMap.remove("signature");
        String newParamStr = "";
        for (Entry<String, String> entry : jsonMap.entrySet()) {
            String value = entry.getValue();
            try {
                // 对参数进行编码，按照RFC3986标准
                value = URLEncoder.encode(value, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            newParamStr += entry.getKey() + "=" + value + "&";
        }
        newParamStr = newParamStr.substring(0, newParamStr.length() - 1);
        newParamStr += appKey;
        String targetSignature = DigestUtils.md5Hex(newParamStr);
        if (!signature.equals(targetSignature)) {
            logger.info(LogUtil.getFormatLog(null, "signature is error"));
            return false;
        }

        // XXX 放重攻击，是否有必要放在验签之前？
        String signatureCacheKey = signatureCacheKeyPrefix + targetSignature;
        if (stringRedisTemplate.opsForValue().get(signatureCacheKey) != null) {
            logger.warn(LogUtil.getFormatLog(signature, "signature is repeat"));
            return false;
        }

        // 将signature 加到 redis 中，防重复攻击 ,过期时间和timestamp过期时间一样
        stringRedisTemplate.opsForValue().set(signatureCacheKey, "", signatureTimeout,
            TimeUnit.SECONDS);

        // 返回给 API 接口的参数中去掉四个验签的参数
        jsonMap.remove("timestamp");
        jsonMap.remove("noncestr");
        // XXX GET请求如何删除参数
        return true;

    }

}
