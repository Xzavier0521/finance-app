package finance.domain.weixin;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
/**
 * <p>注释</p>
 * @author lili
 * @version $Id: Image.java, v0.1 2018/10/28 4:25 PM lili Exp $
 */
@Data
@Builder
public class Image implements Serializable {

    private static final long serialVersionUID = -7790900741258086451L;
    private String            MediaId;
}
