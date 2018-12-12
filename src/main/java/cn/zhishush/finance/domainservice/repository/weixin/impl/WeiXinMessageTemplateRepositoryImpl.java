package cn.zhishush.finance.domainservice.repository.weixin.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

import cn.zhishush.finance.core.dal.dao.weixin.WeiXinMessageTemplateDAO;
import cn.zhishush.finance.core.dal.dataobject.weixin.WeiXinMessageTemplateDO;
import cn.zhishush.finance.domain.weixin.WeiXinMessageTemplate;
import cn.zhishush.finance.domainservice.converter.weixin.WeiXinMessageTemplateConverter;
import cn.zhishush.finance.domainservice.repository.weixin.WeiXinMessageTemplateRepository;

/**
 * <p>微信公众号消息模版</p>
 * @author lili
 * @version 1.0: WeiXinMessageTemplateRepositoryImpl.java, v0.1 2018/11/26 9:51 PM lili Exp $
 */
@Repository("weiXinMessageTemplateRepository")
public class WeiXinMessageTemplateRepositoryImpl implements WeiXinMessageTemplateRepository {

	@Resource
	private WeiXinMessageTemplateDAO weiXinMessageTemplateDAO;

	/**
	 * 插入记录
	 *
	 * @param weiXinMessageTemplate
	 *            微信消息模版
	 * @return int
	 */
	@Override
	public int save(WeiXinMessageTemplate weiXinMessageTemplate) {
		return weiXinMessageTemplateDAO.insertSelective(WeiXinMessageTemplateConverter.convert(weiXinMessageTemplate));
	}

	/**
	 * 单个查询
	 *
	 * @param templateCode
	 *            模版代码
	 * @return WeiXinMessageTemplate
	 */
	@Override
	public WeiXinMessageTemplate query(String templateCode) {

		WeiXinMessageTemplate weiXinMessageTemplate = new WeiXinMessageTemplate();
		Map<String, Object> parameters = Maps.newHashMap();
		parameters.put("templateCode", templateCode);
		List<WeiXinMessageTemplateDO> weiXinMessageTemplateDOList = weiXinMessageTemplateDAO.query(parameters);
		if (CollectionUtils.isNotEmpty(weiXinMessageTemplateDOList)) {
			weiXinMessageTemplate = WeiXinMessageTemplateConverter.convert(weiXinMessageTemplateDOList.get(0));
		}
		return weiXinMessageTemplate;
	}
}
