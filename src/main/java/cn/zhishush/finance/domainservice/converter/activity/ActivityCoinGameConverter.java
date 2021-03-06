package cn.zhishush.finance.domainservice.converter.activity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

import cn.zhishush.finance.core.common.util.ConvertBeanUtil;
import cn.zhishush.finance.core.dal.dataobject.activity.ActivityCoinGameDO;
import cn.zhishush.finance.domain.activity.ActivityCoinGame;
/**
 * <p>活动-金币游戏</p>
 * @author lili
 * @version 1.0: ActivityCoinGameConverter.java, v0.1 2018/11/26 9:33 AM lili Exp $
 */
public class ActivityCoinGameConverter {

	public static ActivityCoinGame convert(ActivityCoinGameDO from) {
		if (Objects.isNull(from)) {
			return null;
		}
		ActivityCoinGame to = new ActivityCoinGame();
		ConvertBeanUtil.copyBeanProperties(from, to);
		return to;
	}

	public static ActivityCoinGameDO convert(ActivityCoinGame from) {
		if (Objects.isNull(from)) {
			return null;
		}
		ActivityCoinGameDO to = new ActivityCoinGameDO();
		ConvertBeanUtil.copyBeanProperties(from, to);
		return to;
	}

	public static List<ActivityCoinGame> convert2List(List<ActivityCoinGameDO> froms) {
		if (CollectionUtils.isEmpty(froms)) {
			return Collections.emptyList();
		}
		List<ActivityCoinGame> tos = Lists.newArrayListWithCapacity(froms.size());
		froms.forEach(from -> tos.add(convert(from)));
		return tos;
	}

	public static List<ActivityCoinGameDO> convert2DoList(List<ActivityCoinGame> froms) {
		if (CollectionUtils.isEmpty(froms)) {
			return Collections.emptyList();
		}
		List<ActivityCoinGameDO> tos = Lists.newArrayListWithCapacity(froms.size());
		froms.forEach(from -> tos.add(convert(from)));
		return tos;
	}
}
