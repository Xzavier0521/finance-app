package finance.domainservice.repository;

import finance.domain.weixin.WeiXinMessageTemplate;

/**
 * <p>微信公众号消息模版</p>
 * @author lili
 * @version 1.0: WeiXinMessageTemplateRepository.java, v0.1 2018/11/26 9:51 PM lili Exp $
 */
public interface WeiXinMessageTemplateRepository {
    /**
     * 插入记录
     * @param weiXinMessageTemplate 微信消息模版
     * @return int
     */
    int save(WeiXinMessageTemplate weiXinMessageTemplate);

    /**
     * 单个查询
     * @param templateCode 模版代码
     * @return WeiXinMessageTemplate
     */
    WeiXinMessageTemplate query(String templateCode);

}
