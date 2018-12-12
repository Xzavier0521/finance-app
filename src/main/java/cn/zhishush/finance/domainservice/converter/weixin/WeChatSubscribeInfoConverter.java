package cn.zhishush.finance.domainservice.converter.weixin;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

import cn.zhishush.finance.domain.weixin.WeChatSubscribeInfo;
import cn.zhishush.finance.core.dal.dataobject.weixin.WeChatSubscribeInfoDO;
import cn.zhishush.finance.core.common.util.ConvertBeanUtil;

/**
 * <p>∂注释</p>
 * 
 * @author lili
 * @version $Id: WeChatSubscribeInfoConverter.java, v0.1 2018/11/7 3:20 PM lili
 *          Exp $
 */
public class WeChatSubscribeInfoConverter {

	public static WeChatSubscribeInfo convert(WeChatSubscribeInfoDO from) {
		if (Objects.isNull(from)) {
			return null;
		}
		WeChatSubscribeInfo to = new WeChatSubscribeInfo();
		ConvertBeanUtil.copyBeanProperties(from, to);
		return to;
	}

	public static WeChatSubscribeInfoDO convert(WeChatSubscribeInfo from) {
		if (Objects.isNull(from)) {
			return null;
		}
		WeChatSubscribeInfoDO to = new WeChatSubscribeInfoDO();
		ConvertBeanUtil.copyBeanProperties(from, to);
		return to;
	}

	public static List<WeChatSubscribeInfo> convert2List(List<WeChatSubscribeInfoDO> froms) {
		if (CollectionUtils.isEmpty(froms)) {
			return Collections.emptyList();
		}
		List<WeChatSubscribeInfo> tos = Lists.newArrayListWithCapacity(froms.size());
		froms.forEach(from -> tos.add(convert(from)));
		return tos;
	}

	public static List<WeChatSubscribeInfoDO> convert2DoList(List<WeChatSubscribeInfo> froms) {
		if (CollectionUtils.isEmpty(froms)) {
			return Collections.emptyList();
		}
		List<WeChatSubscribeInfoDO> tos = Lists.newArrayListWithCapacity(froms.size());
		froms.forEach(from -> tos.add(convert(from)));
		return tos;
	}
}
