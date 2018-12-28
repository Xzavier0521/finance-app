package cn.zhishush.finance.domainservice.service.creditcard;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.vo.creditCard.CardParameter;
import cn.zhishush.finance.api.model.vo.creditCard.CreditCardScheduleVO;
import cn.zhishush.finance.api.model.vo.creditCard.CreditCardTeamVO;

import java.util.Map;

/**
 * <p>信用卡模块查询</p>
 *
 * @author zww
 * @version 1.0: PopularizeModuleInfoQueryController.java, v0.1 2018/12/25 14:31 PM PM
 */
public interface CreditCardScheduleServer {
    /**
     * 团队信用卡进度查询
     * @param params
     * @param pageSize
     * @param pageNum
     * @return
     */
    Page<CreditCardTeamVO> query4Page(Map<String, Object> params, int pageSize, Long pageNum);

    /**
     * 信用卡进度查询
     * @param cardParameter
     * @return
     */
    Page<CreditCardScheduleVO> querySchedule(CardParameter cardParameter);





}
