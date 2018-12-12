package cn.zhishush.finance.domainservice.service.activity;

import cn.zhishush.finance.api.model.response.BasicResponse;
import cn.zhishush.finance.domain.basic.BasicParameter;

/**
 * <p>注释</p>
 * @author lili
 * @version 1.0: ActivityCoinGameRecordService.java, v0.1 2018/11/26 9:28 AM lili Exp $
 */
@FunctionalInterface
public interface ActivityCoinGameRecordService {
	/**
	 * 执行
	 * 
	 * @param parameter
	 *            参数
	 * @return BasicResponse
	 */
	BasicResponse process(BasicParameter parameter);
}
