package finance.mapper;

import finance.model.po.FinanceBarrageMessage;

import java.util.List;
import java.util.Map;

/**
 * <p>弹幕消息</p>
 *
 * @author lili
 * @version 1.0: FinanceBarrageMessageDAO.java, v 0.1 2018/9/29 上午10:08 lili Exp $
 */
public interface FinanceBarrageMessageDAO extends BaseDAO<FinanceBarrageMessage, Long> {


    /**
     * 查询总记录数
     * @param parameters 查询参数
     * @return int
     */
    int count(Map<String,Object> parameters);

    /**
     *  分页查询
     *  List<FinanceBarrageMessage>
     * @param parameters 查询参数
     * @return  List<FinanceBarrageMessage>
     */
    List<FinanceBarrageMessage> query(Map<String,Object> parameters);
}
