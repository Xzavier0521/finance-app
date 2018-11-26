package finance.domainservice.converter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

import finance.domain.weixin.InviteOpenInfo;
import finance.core.dal.dataobject.InviteOpenInfoDO;
import finance.core.common.util.ConvertBeanUtil;

/**
 * <p>
 * 注释
 * </p>
 *
 * @author lili
 * @version $Id: InviteOpenInfoConverter.java, v0.1 2018/10/31 11:17 PM lili Exp
 *          $
 */
public class InviteOpenInfoConverter {

	public static InviteOpenInfo convert(InviteOpenInfoDO from) {
		if (Objects.isNull(from)) {
			return null;
		}
		InviteOpenInfo to = new InviteOpenInfo();
		ConvertBeanUtil.copyBeanProperties(from, to);
		return to;
	}

	public static InviteOpenInfoDO convert(InviteOpenInfo from) {
		if (Objects.isNull(from)) {
			return null;
		}
		InviteOpenInfoDO to = new InviteOpenInfoDO();
		ConvertBeanUtil.copyBeanProperties(from, to);
		if (Objects.nonNull(from.getConcernStatus())) {
			to.setConcernStatus(Integer.valueOf(from.getConcernStatus().getCode()));
		}
		return to;
	}

	public static List<InviteOpenInfo> convert2List(List<InviteOpenInfoDO> froms) {
		if (CollectionUtils.isEmpty(froms)) {
			return Collections.emptyList();
		}
		List<InviteOpenInfo> tos = Lists.newArrayListWithCapacity(froms.size());
		froms.forEach(from -> tos.add(convert(from)));
		return tos;
	}

	public static List<InviteOpenInfoDO> convert2DoList(List<InviteOpenInfo> froms) {
		if (CollectionUtils.isEmpty(froms)) {
			return Collections.emptyList();
		}
		List<InviteOpenInfoDO> tos = Lists.newArrayListWithCapacity(froms.size());
		froms.forEach(from -> tos.add(convert(from)));
		return tos;
	}
}
