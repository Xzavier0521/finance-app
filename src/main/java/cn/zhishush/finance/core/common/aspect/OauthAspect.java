package cn.zhishush.finance.core.common.aspect;

import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.zhishush.finance.core.common.exception.JwtException;
import cn.zhishush.finance.core.dal.dataobject.user.UserInfoDO;
import cn.zhishush.finance.domain.log.OperationLog;
import cn.zhishush.finance.domainservice.repository.log.OperationLogRepository;
import cn.zhishush.finance.domainservice.service.jwt.JwtService;

/**
 * <p>请求权限验证</p>
 * @author lili
 * @version 1.0: OauthAspect.java, v0.1 2018/11/29 3:49 AM lili Exp $
 */
@Slf4j
@Aspect
@Component
public class OauthAspect {

    @Resource
    private JwtService             jwtService;
    @Resource
    private OperationLogRepository operationLogRepository;

    @Before("execution(public *  cn.zhishush.finance.web.controller.oauth..*(..))")
    public void before(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
            .getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String jwtKey = request.getHeader("jwt");
        String path = request.getServletPath();

        // 检查header中 jwt 是否传递
        if (StringUtils.isEmpty(jwtKey)) {
            throw new JwtException("jwt.head is null");
        }

        // 检查jwt 在缓存中是否存在
        Boolean hasJwt = jwtService.hasJwt(jwtKey);
        if (!hasJwt) {
            throw new JwtException("jwt.session is null");
        }

        // 刷新 jwt 的缓存时间
        jwtService.refreshJwt(jwtKey);
        // 记录操作日志
        UserInfoDO userInfo = jwtService.getUserInfo();
        if (Objects.nonNull(userInfo)) {
            Long userId = userInfo.getId();
            OperationLog operationLog = OperationLog.builder().userId(userId).opCode(path)
                .opName(path).creator("system").updator("system").version(1).build();
            log.info("插入操作日志记录:{}", operationLog);
            operationLogRepository.save(operationLog);
        }
    }

}
