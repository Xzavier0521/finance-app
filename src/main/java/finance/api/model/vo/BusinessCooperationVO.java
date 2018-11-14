package finance.api.model.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>商务合作</p>
 * @author lili
 * @version $Id: BusinessCooperationVO.java, v0.1 2018/10/29 5:22 PM lili Exp $
 */
@Data
public class BusinessCooperationVO implements Serializable {

    private static final long serialVersionUID = 6919086217753952180L;
    /**
     * 姓名
     */
    private String            realName;
    /**
     * 手机号码
     */
    private String            mobilePhone;
}
