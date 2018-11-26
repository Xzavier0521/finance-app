package finance.api.model.vo.activity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>用户当前排名</p>
 * @author lili
 * @version 1.0: UserCurrentRankingVO.java, v0.1 2018/11/26 7:12 PM lili Exp $
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCurrentRankingVO implements Serializable {

    private static final long serialVersionUID = 1501358924193039178L;
    /**
     * 用户id
     */
    private Long              userId;

    /**
     * 手机号码
     */
    private String            mobilePhone;
    /**
     * 排名
     */
    private String            ranking;

    /**
     * 红包总个数
     */
    private Long              totalNum;

    /**
     * 金币总个数
     */
    private Long              totalAmount;

}
