package finance.api.model.vo.coin;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>成长任务VO</p>
 *
 * @author lili
 * @version 1.0: GrowTaskVO.java, v0.1 2018/11/26 5:14 PM lili Exp $
 */
@Data
public class GrowTaskVO implements Serializable {
    private static final long serialVersionUID = -8500758615098763248L;
    private Long              taskId;
    private String            pic;
    private String            title;
    private Integer           coinNum;
    private Integer           currentNum;
    private Integer           nextNum;
    private Integer           status;
}
