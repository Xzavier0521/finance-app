package finance.domainservice.service.activity;

import java.math.BigDecimal;

import finance.api.model.response.BasicResponse;
import finance.core.common.enums.RedEnvelopeRainTimeCodeEnum;
import finance.domain.user.UserInfo;

/**
 * <p>红包雨活动数据</p>
 * @author lili
 * @version 1.0: RedEnvelopeRainDataService.java, v0.1 2018/11/26 9:32 AM lili Exp $
 */
public interface RedEnvelopeRainDataService {

	/**
	 * 保存红包雨活动数据
	 * 
	 * @param userInfo
	 *            用户信息
	 * @param activityCode
	 *            活动代码
	 * @param timeCode
	 *            时间编码
	 * @param totalNum
	 *            总红包数
	 * @param totalAmount
	 *            总金额/金币
	 * @return BasicResponse
	 */
	BasicResponse localData(UserInfo userInfo, String activityCode, RedEnvelopeRainTimeCodeEnum timeCode, Long totalNum,
			BigDecimal totalAmount);
}
