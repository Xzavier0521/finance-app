package cn.zhishush.finance.ext.integration.kameng.impl;

import cn.zhishush.finance.core.common.enums.ReturnCode;
import cn.zhishush.finance.core.common.util.MapUtil;
import cn.zhishush.finance.core.dal.dataobject.user.UserApplyCreditCardDetailDO;
import cn.zhishush.finance.ext.api.request.KaMengUserApplyCreditCardRequest;
import cn.zhishush.finance.ext.api.response.KaMengUserApplyCreditCardResponse;
import cn.zhishush.finance.ext.integration.config.RetrofitHttpClient;
import cn.zhishush.finance.ext.integration.kameng.UserApplyCreditCardDetailClient;
import cn.zhishush.finance.ext.service.kameng.UserApplyCreditCardDetailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 注释
 * </p >
 *
 * @author liwei
 * @version $Id: UserApplyCreditCardDetailClientImpl.java, v0.1 2018/11/19
 *          下午6:30 PM user Exp $
 */
@Slf4j
@Service("userApplyCreditCardDetailClient")
public class UserApplyCreditCardDetailClientImpl implements UserApplyCreditCardDetailClient {

	@Value("${kameng.api.host}")
	private String kaMengApiHost;

	@Resource
	private RetrofitHttpClient retrofitHttpClient;

	private UserApplyCreditCardDetailService create() {
		return retrofitHttpClient.build(kaMengApiHost).create(UserApplyCreditCardDetailService.class);
	}

	/**
	 * 用户申请信用卡办理详情接口
	 * 
	 * @param userApplyCreditCardDetailDO
	 * @return
	 */
	@Override
	public KaMengUserApplyCreditCardResponse applyCreditCard(UserApplyCreditCardDetailDO userApplyCreditCardDetailDO) {
		KaMengUserApplyCreditCardResponse response;
		KaMengUserApplyCreditCardRequest request = new KaMengUserApplyCreditCardRequest();
		request.setIp(userApplyCreditCardDetailDO.getIp());
		request.setFatherId(Long.valueOf(userApplyCreditCardDetailDO.getChannelId()));
		request.setName(userApplyCreditCardDetailDO.getUserName());
		request.setPhone(userApplyCreditCardDetailDO.getMobileNum());
		request.setGoodsId(userApplyCreditCardDetailDO.getProductId());
		request.setType(userApplyCreditCardDetailDO.getProductType());
		request.setOtherUserId(userApplyCreditCardDetailDO.getUserId());
		request.setIdCard(userApplyCreditCardDetailDO.getIdNum());
		request.setSystemVersion(userApplyCreditCardDetailDO.getSystemVersion());
		request.setSoftVersion(userApplyCreditCardDetailDO.getSoftVersion());
		log.info("[开始申请办理信用卡],请求参数:{}", request);
		try {
			Map<String, Object> map = MapUtil.transBean2Map(request);
			response = create().applyCreditCard(map).execute().body();
			log.info("[申请办理信用卡],返回结果{}", response);
		} catch (final Exception e) {
			response = new KaMengUserApplyCreditCardResponse();
			response.setStatus(ReturnCode.SYSTEM_ERROR.getCode());
			response.setMessage("[获取申请办理信用卡结果],异常:" + e.getMessage());
			log.error("[获取申请办理信用卡结果],请求参数:{},异常信息:{}", request, ExceptionUtils.getStackTrace(e));
		}
		log.info("[结束申请办理信用卡],请求参数:{},返回结果:{}", request, response);
		return response;
	}
}
