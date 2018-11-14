package finance.domain.weixin;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: TextMessage.java, v0.1 2018/10/28 4:10 PM lili Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TextMessage extends Message {
    private static final long serialVersionUID = -3606888824213933612L;
    private String Content;
}
