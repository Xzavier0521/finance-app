package cn.zhishush.finance.domainservice.converter.coin;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import cn.zhishush.finance.core.common.util.ConvertBeanUtil;
import cn.zhishush.finance.core.dal.dataobject.coin.CoinGameLogDO;
import cn.zhishush.finance.domain.coin.CoinGameLog;

import com.google.common.collect.Lists;

/**
 * <p>金币游戏日志</p>
 *
 * @author lili
 * @version 1.0: CoinGameLogConverter.java, v 0.1 2018/9/28 上午10:54 lili Exp $
 */
public class CoinGameLogConverter {

    public static CoinGameLog convert(CoinGameLogDO from) {
        if (Objects.isNull(from)) {
            return null;
        }
        CoinGameLog to = new CoinGameLog();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static CoinGameLogDO convert(CoinGameLog from) {
        if (Objects.isNull(from)) {
            return null;
        }
        CoinGameLogDO to = new CoinGameLogDO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<CoinGameLog> convert2List(List<CoinGameLogDO> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<CoinGameLog> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<CoinGameLogDO> convert2DoList(List<CoinGameLog> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<CoinGameLogDO> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
