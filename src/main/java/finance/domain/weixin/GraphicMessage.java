package finance.domain.weixin;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>微信公众号回复图文消息</p>
 * 
 * @author lili
 * @version $Id: GraphicMessage.java, v0.1 2018/10/28 3:18 PM lili Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GraphicMessage extends Message {
    private static final long serialVersionUID = -6172555151683552613L;

    /**
     * 图文消息个数，限制为10条以内
     */
    private Integer           ArticleCount;

    /**
     * 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应
     */
    private List<item>        Articles;

}
