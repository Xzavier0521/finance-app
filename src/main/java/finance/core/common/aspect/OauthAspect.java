package finance.core.common.aspect;

import finance.core.common.exception.JwtException;
import finance.domain.log.OperationLog;
import finance.domainservice.repository.OperationLogRepository;
import finance.core.dal.dataobject.FinanceUserInfo;
import finance.domainservice.service.jwt.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 请求权限验证.
 *
 * @author hewenbin
 * @version v1.0 2018年7月4日 上午9:05:12 hewenbin
 */
@Slf4j
@Aspect
@Component
public class OauthAspect {

    @Resource
    private JwtService             jwtService;
    @Resource
    private OperationLogRepository operationLogRepository;

    @Before("execution(public * finance.web.controller.oauth..*(..))")
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
        FinanceUserInfo userInfo = jwtService.getUserInfo();
        if (Objects.nonNull(userInfo)) {
            Long userId = userInfo.getId();
            OperationLog operationLog = OperationLog.builder().userId(userId).opCode(path)
                .opName(path).creator("system").updator("system").version(1).build();
            log.info("插入操作日志记录:{}", operationLog);
            operationLogRepository.save(operationLog);
        }
    }

}
