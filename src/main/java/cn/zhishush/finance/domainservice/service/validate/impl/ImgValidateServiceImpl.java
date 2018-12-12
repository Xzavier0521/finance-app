package cn.zhishush.finance.domainservice.service.validate.impl;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import cn.zhishush.finance.api.model.vo.login.ImgValidateVo;
import cn.zhishush.finance.core.common.constants.Constant;
import cn.zhishush.finance.core.common.util.ImgValidateCodeUtil;
import cn.zhishush.finance.core.common.util.ImgValidateCodeUtil.ImgValidate;
import cn.zhishush.finance.domainservice.service.validate.ImgValidateService;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version $Id: ImgValidateServiceImpl.java, v0.1 2018/11/17 1:45 PM lili Exp $
 */
@Service("imgValidateService")
public class ImgValidateServiceImpl implements ImgValidateService {
    @Value("${imgcode.cache.minute}")
    private Long                cacheTimeoutHours;

    @Value("${imgcode.cache.key.prefix}")
    private String              cacheKeyPrefix;
    @Value("${imgcode.test.switch}")
    private String              testSwitch;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public ImgValidateVo getImgValidate() {
        ImgValidate imgCode = ImgValidateCodeUtil.getRandomCode(Constant.imgcode_length);
        String imgCodeValue = imgCode.getValue();
        String imgCodeId = UUID.randomUUID().toString().replaceAll("-", "");
        ImgValidateVo codeVo = new ImgValidateVo();
        codeVo.setImgCodeId(imgCodeId);
        codeVo.setImgCodeBase64("data:image/png;base64," + imgCode.getBase64Str());
        stringRedisTemplate.opsForValue().set(cacheKeyPrefix + imgCodeId, imgCodeValue,
            cacheTimeoutHours, TimeUnit.MINUTES);
        return codeVo;
    }

    @Override
    public Boolean validateImgCode(String imgCodeId, String imgCode) {
        if ("1".equals(testSwitch)) {
            // 如果测试开关打开，则直接验证通过
            return true;
        }
        String cacheKey = cacheKeyPrefix + imgCodeId;
        String imgCodeValue = stringRedisTemplate.opsForValue().get(cacheKey);
        if (!imgCode.equalsIgnoreCase(imgCodeValue)) {
            // 验证不通过，立即失效
            stringRedisTemplate.delete(cacheKey);
            return false;
        }
        return true;
    }

}
