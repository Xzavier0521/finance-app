package finance.domain.weixin;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>注释</p>
 * 
 * @author lili
 * @version $Id: GraphicMessageItem.java, v0.1 2018/10/28 3:23 PM lili Exp $
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class item implements Serializable {

    private static final long serialVersionUID = -7823721387935968046L;
    /**
     * 图文消息标题
     */
    private String            Title;
    /**
     * 图文消息描述
     */
    private String            Description;

    /**
     * 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
     */
    private String            PicUrl;

    /**
     * 点击图文消息跳转链接
     */
    private String            Url;
}
