package finance.mapper;

import java.util.List;
import java.util.Map;

import finance.core.dal.dataobject.WeiXinMessageTemplateDO;

/**
 * <p>微信公众号消息模版</p>
 * @author lili
 * @version $Id: WeiXinMessageTemplateDAO.java, v0.1 2018/10/24 10:23 AM lili Exp $
 */
public interface WeiXinMessageTemplateDAO {

    int insertSelective(WeiXinMessageTemplateDO record);

    WeiXinMessageTemplateDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WeiXinMessageTemplateDO record);

    Long count(Map<String, Object> parameters);

    List<WeiXinMessageTemplateDO> query(Map<String, Object> parameters);
}