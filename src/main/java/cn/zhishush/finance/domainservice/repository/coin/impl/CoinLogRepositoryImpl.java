package cn.zhishush.finance.domainservice.repository.coin.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.zhishush.finance.core.dal.dao.coin.CoinLogDAO;
import cn.zhishush.finance.core.dal.dataobject.coin.CoinLogDO;
import cn.zhishush.finance.domain.coin.CoinLog;
import cn.zhishush.finance.domainservice.converter.coin.CoinLogConverter;
import cn.zhishush.finance.domainservice.repository.coin.CoinLogRepository;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: CoinLogRepositoryImpl.java, v 0.1 2018/9/28 上午9:33 lili Exp $
 */
@Repository("coinLogRepository")
public class CoinLogRepositoryImpl implements CoinLogRepository {

    @Resource
    private CoinLogDAO coinLogDAO;

    @Override
    public List<CoinLog> queryLatestLog(List<Long> userIds) {
        return CoinLogConverter.convert2List(coinLogDAO.queryLatestLog(userIds));
    }

    @Override
    public int save(Long userId, Integer coinNum, String reason) {
        CoinLogDO coinLogDO = new CoinLogDO();
        coinLogDO.setUserId(userId);
        coinLogDO.setTaskId(userId);
        coinLogDO.setTaskName(reason);
        coinLogDO.setNum(coinNum);
        return coinLogDAO.insertSelective(coinLogDO);
    }

    @Override
    public Integer selectCoinNumByUserId(Long userId) {
        return coinLogDAO.selectCoinNumByUserId(userId);
    }
}
