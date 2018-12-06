package finance.api.model.vo.third;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>注册数据</p>
 *
 * @author lili
 * @version 1.0: RegisterStatisticsData.java, v0.1 2018/12/6 2:17 PM PM lili Exp $
 */
@Data
public class RegisterStatisticsData implements Serializable {

    private static final long serialVersionUID = 1813822918762925534L;
    /**
     * 注册日期
     */
    private String registerDate;

    /**
     * 注册用户数
     */
    private int    registerNum;
}
