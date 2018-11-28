package finance.api.model.vo.common;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: BodyVO.java, v0.1 2018/11/29 1:22 AM PM lili Exp $
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BodyVO implements Serializable {

    private static final long serialVersionUID = 8682983297168921101L;
    /**
     * 标题
     */
    private String            title;

    /**
     * 值
     */
    private String            value;

    /**
     * 类型 text-文本 img-图片地址
     */
    private String            type;

    /**
     * 顺序
     */
    private Integer              order;
}
