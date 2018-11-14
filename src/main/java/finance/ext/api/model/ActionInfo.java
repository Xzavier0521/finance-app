package finance.ext.api.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: ActionInfo.java, v0.1 2018/10/29 5:05 PM lili Exp $
 */
@Data
@Builder
public class ActionInfo implements Serializable {
    private static final long serialVersionUID = 3272486764826016741L;
    private Scene             scene;
}
