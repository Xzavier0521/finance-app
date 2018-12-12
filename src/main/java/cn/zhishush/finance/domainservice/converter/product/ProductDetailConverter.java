package cn.zhishush.finance.domainservice.converter.product;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

import cn.zhishush.finance.core.dal.dataobject.product.ProductDetailDO;
import cn.zhishush.finance.domain.product.ProductDetail;
import cn.zhishush.finance.core.common.util.ConvertBeanUtil;

/**
 * <p>产品明细</p>
 *
 * @author lili
 * @version 1.0: ProductDetailConverter.java, v0.1 2018/11/8 2:08 PM PM lili Exp
 *          $
 */
public class ProductDetailConverter {

	public static ProductDetail convert(ProductDetailDO from) {
		if (Objects.isNull(from)) {
			return null;
		}
		ProductDetail to = new ProductDetail();
		ConvertBeanUtil.copyBeanProperties(from, to);
		return to;
	}

	public static ProductDetailDO convert(ProductDetail from) {
		if (Objects.isNull(from)) {
			return null;
		}
		ProductDetailDO to = new ProductDetailDO();
		ConvertBeanUtil.copyBeanProperties(from, to);
		return to;
	}

	public static List<ProductDetail> convert2List(List<ProductDetailDO> froms) {
		if (CollectionUtils.isEmpty(froms)) {
			return Collections.emptyList();
		}
		List<ProductDetail> tos = Lists.newArrayListWithCapacity(froms.size());
		froms.forEach(from -> tos.add(convert(from)));
		return tos;
	}

	public static List<ProductDetailDO> convert2DoList(List<ProductDetail> froms) {
		if (CollectionUtils.isEmpty(froms)) {
			return Collections.emptyList();
		}
		List<ProductDetailDO> tos = Lists.newArrayListWithCapacity(froms.size());
		froms.forEach(from -> tos.add(convert(from)));
		return tos;
	}
}
