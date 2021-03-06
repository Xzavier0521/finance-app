package cn.zhishush.finance.domainservice.converter.product;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import cn.zhishush.finance.core.common.enums.ProductModuleTypeEnum;
import cn.zhishush.finance.core.common.util.ConvertBeanUtil;
import cn.zhishush.finance.core.dal.dataobject.product.ProductModuleDO;
import cn.zhishush.finance.domain.product.ProductModule;

import com.google.common.collect.Lists;

/**
 * <p>产品模块</p>
 *
 * @author lili
 * @version 1.0: ProductModuleConverter.java, v0.1 2018/11/8 2:06 PM PM lili Exp$
 */
public class ProductModuleConverter {

    public static ProductModule convert(ProductModuleDO from) {
        if (Objects.isNull(from)) {
            return null;
        }
        ProductModule to = new ProductModule();
        ConvertBeanUtil.copyBeanProperties(from, to);
        to.setModuleCode(ProductModuleTypeEnum.LOAN_MODULE);
        return to;
    }

    public static ProductModuleDO convert(ProductModule from) {
        if (Objects.isNull(from)) {
            return null;
        }
        ProductModuleDO to = new ProductModuleDO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<ProductModule> convert2List(List<ProductModuleDO> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<ProductModule> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<ProductModuleDO> convert2DoList(List<ProductModule> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<ProductModuleDO> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
