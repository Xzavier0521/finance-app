package cn.zhishush.finance.domainservice.repository.log;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.condition.BarrageMessageQueryCondition;
import cn.zhishush.finance.domain.log.BarrageMessage;

/**
 * <p>弹幕消息：现阶段保存到数据库，后期可以考虑放入redis或者采用rabbitmq</p>
 *
 * @author lili
 * @version 1.0: BarrageMessageRepository.java, v 0.1 2018/9/29 上午9:59 lili Exp$
 */
public interface BarrageMessageRepository {

    /**
     * 保存弹幕消息
     * @param barrageMessage 弹幕消息
     * @return int
     */
    int save(BarrageMessage barrageMessage);

    /**
     * 查询带分页
     * @param condition 查询条件
     * @return Page<BarrageMessage>
     */
    Page<BarrageMessage> query(BarrageMessageQueryCondition condition);
}
