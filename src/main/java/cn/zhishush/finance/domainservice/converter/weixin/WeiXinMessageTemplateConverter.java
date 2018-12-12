package cn.zhishush.finance.domainservice.converter.weixin;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import lombok.Data;

import org.apache.commons.collections4.CollectionUtils;

import cn.zhishush.finance.core.common.enums.WeiXinMessageTemplateCodeEnum;
import cn.zhishush.finance.core.common.util.ConvertBeanUtil;
import cn.zhishush.finance.core.dal.dataobject.weixin.WeiXinMessageTemplateDO;
import cn.zhishush.finance.domain.weixin.WeiXinMessageTemplate;

import com.google.common.collect.Lists;

/**
 * <p>微信公众号消息模版</p>
 * 
 * @author lili
 * @version $Id: WeiXinMessageTemplateConverter.java, v0.1 2018/10/24 10:53 AM
 *          lili Exp $
 */
@Data
public class WeiXinMessageTemplateConverter {

    public static WeiXinMessageTemplate convert(WeiXinMessageTemplateDO from) {
        if (Objects.isNull(from)) {
            return null;
        }
        WeiXinMessageTemplate to = new WeiXinMessageTemplate();
        ConvertBeanUtil.copyBeanProperties(from, to);
        to.setTemplateCode(WeiXinMessageTemplateCodeEnum.getByCode(from.getTemplateCode()));
        return to;
    }

    public static WeiXinMessageTemplateDO convert(WeiXinMessageTemplate from) {
        if (Objects.isNull(from)) {
            return null;
        }
        WeiXinMessageTemplateDO to = new WeiXinMessageTemplateDO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<WeiXinMessageTemplate> convert2List(List<WeiXinMessageTemplateDO> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<WeiXinMessageTemplate> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<WeiXinMessageTemplateDO> convert2DoList(List<WeiXinMessageTemplate> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<WeiXinMessageTemplateDO> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
