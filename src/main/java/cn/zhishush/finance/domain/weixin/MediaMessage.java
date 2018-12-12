package cn.zhishush.finance.domain.weixin;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version $Id: MediaMessage.java, v0.1 2018/10/28 4:03 PM lili Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MediaMessage extends Message {

    private static final long serialVersionUID = 6917641753423529548L;

    /**
     * 上传的素材id
     */
    private Image             Image;

}
