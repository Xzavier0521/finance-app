package finance.domainservice.service.activity;

import finance.api.model.response.BasicResponse;
import finance.domain.basic.BasicParameter;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: ActivityCoinGameRecordService.java, v0.1 2018/11/15 11:13 PM PM lili Exp $
 */
@FunctionalInterface
public interface ActivityCoinGameRecordService {
    /**
     * 执行
     * @param parameter 参数
     * @return BasicResponse
     */
    BasicResponse process(BasicParameter parameter);
}
