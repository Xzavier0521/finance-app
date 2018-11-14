package finance.domainservice.repository.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

import finance.mapper.WeiXinMessageTemplateDAO;
import finance.core.dal.dataobject.WeiXinMessageTemplateDO;
import finance.domain.weixin.WeiXinMessageTemplate;
import finance.domainservice.converter.WeiXinMessageTemplateConverter;
import finance.domainservice.repository.WeiXinMessageTemplateRepository;

/**
 * <p>微信公众号消息模版</p>
 * @author lili
 * @version $Id: WeiXinMessageTemplateRepositoryImpl.java, v0.1 2018/10/24 10:50 AM lili Exp $
 */
@Repository("weiXinMessageTemplateRepository")
public class WeiXinMessageTemplateRepositoryImpl implements WeiXinMessageTemplateRepository {

    @Resource
    private WeiXinMessageTemplateDAO weiXinMessageTemplateDAO;

    /**
     * 插入记录
     *
     * @param weiXinMessageTemplate 微信消息模版
     * @return int
     */
    @Override
    public int save(WeiXinMessageTemplate weiXinMessageTemplate) {
        return weiXinMessageTemplateDAO
            .insertSelective(WeiXinMessageTemplateConverter.convert(weiXinMessageTemplate));
    }

    /**
     * 单个查询
     *
     * @param templateCode 模版代码
     * @return WeiXinMessageTemplate
     */
    @Override
    public WeiXinMessageTemplate query(String templateCode) {

        WeiXinMessageTemplate weiXinMessageTemplate = new WeiXinMessageTemplate();
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("templateCode", templateCode);
        List<WeiXinMessageTemplateDO> weiXinMessageTemplateDOList = weiXinMessageTemplateDAO
            .query(parameters);
        if (CollectionUtils.isNotEmpty(weiXinMessageTemplateDOList)) {
            weiXinMessageTemplate = WeiXinMessageTemplateConverter
                .convert(weiXinMessageTemplateDOList.get(0));
        }
        return weiXinMessageTemplate;
    }
}
