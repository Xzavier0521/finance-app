package finance.domainservice.service.activity.query.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import finance.core.common.enums.RedEnvelopeRainTimeCodeEnum;
import finance.core.common.util.DateUtils;
import finance.domain.activity.RedEnvelopeRainConfig;
import finance.domainservice.repository.RedEnvelopeRainConfigRepository;
import finance.domainservice.service.activity.query.RedEnvelopeRainConfigQueryService;

/**
 * <p>
 * 红包雨活动时间配置
 * </p>
 *
 * @author lili
 * @version 1.0: RedEnvelopeRainConfigQueryServiceImpl.java, v0.1 2018/11/16
 *          11:56 AM PM lili Exp $
 */
@Slf4j
@Service("redEnvelopeRainConfigQueryService")
public class RedEnvelopeRainConfigQueryServiceImpl implements RedEnvelopeRainConfigQueryService {

	@Resource
	private RedEnvelopeRainConfigRepository redEnvelopeRainConfigRepository;

	@Override
	public RedEnvelopeRainTimeCodeEnum queryTimeCode(String activityCode, LocalDateTime localDateTime) {
		RedEnvelopeRainTimeCodeEnum redEnvelopeRainTimeCode = null;
		Integer requestTime = Integer.valueOf(DateUtils.getFormatDateStr(localDateTime, DateUtils.HOUR_FORMAT));
		List<RedEnvelopeRainConfig> redEnvelopeRainConfigList = redEnvelopeRainConfigRepository
				.queryByCode(activityCode);
		if (CollectionUtils.isEmpty(redEnvelopeRainConfigList)) {
			return null;
		}
		for (RedEnvelopeRainConfig redEnvelopeRainConfig : redEnvelopeRainConfigList) {
			int startTime = Integer.valueOf(redEnvelopeRainConfig.getStartTime());
			int endTime = Integer.valueOf(redEnvelopeRainConfig.getEndTime());
			if (requestTime >= startTime && requestTime <= endTime) {
				redEnvelopeRainTimeCode = RedEnvelopeRainTimeCodeEnum.getByCode(redEnvelopeRainConfig.getTimeCode());
			}

		}
		return redEnvelopeRainTimeCode;
	}
}
