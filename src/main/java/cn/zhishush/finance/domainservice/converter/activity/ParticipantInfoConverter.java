package cn.zhishush.finance.domainservice.converter.activity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

import cn.zhishush.finance.domain.activity.ParticipantInfo;
import cn.zhishush.finance.core.dal.dataobject.product.ParticipantInfoDO;
import cn.zhishush.finance.core.common.util.CommonUtils;
import cn.zhishush.finance.core.common.util.ConvertBeanUtil;

/**
 * <p>注释</p>
 * 
 * @author lili
 * @version $Id: ParticipantInfoConverter.java, v0.1 2018/10/20 12:03 PM lili Exp $
 */
public class ParticipantInfoConverter {

	public static ParticipantInfo convert(ParticipantInfoDO from) {
		if (Objects.isNull(from)) {
			return null;
		}
		ParticipantInfo to = new ParticipantInfo();
		ConvertBeanUtil.copyBeanProperties(from, to);
		if (StringUtils.isNotBlank(from.getMobilePhone())) {
			// 手机号码脱敏处理 --后续数据库加密然后脱敏
			to.setMobilePhone(CommonUtils.mobileEncrypt(from.getMobilePhone()));
		}
		return to;
	}

	public static ParticipantInfoDO convert(ParticipantInfo from) {
		if (Objects.isNull(from)) {
			return null;
		}
		ParticipantInfoDO to = new ParticipantInfoDO();
		ConvertBeanUtil.copyBeanProperties(from, to);
		return to;
	}

	public static List<ParticipantInfo> convert2List(List<ParticipantInfoDO> froms) {
		if (CollectionUtils.isEmpty(froms)) {
			return Collections.emptyList();
		}
		List<ParticipantInfo> tos = Lists.newArrayListWithCapacity(froms.size());
		froms.forEach(from -> tos.add(convert(from)));
		return tos;
	}

	public static List<ParticipantInfoDO> convert2DoList(List<ParticipantInfo> froms) {
		if (CollectionUtils.isEmpty(froms)) {
			return Collections.emptyList();
		}
		List<ParticipantInfoDO> tos = Lists.newArrayListWithCapacity(froms.size());
		froms.forEach(from -> tos.add(convert(from)));
		return tos;
	}
}
