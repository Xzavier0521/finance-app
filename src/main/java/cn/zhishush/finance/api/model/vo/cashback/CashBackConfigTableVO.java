package cn.zhishush.finance.api.model.vo.cashback;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * <p>返用配置</p>
 *
 * @author lili
 * @version 1.0: CashBackConfigTableVO.java, v0.1 2018/11/29 1:19 AM PM lili Exp $
 */
@Data
public class CashBackConfigTableVO implements Serializable {

    private static final long serialVersionUID = -2043502724918400389L;
    private String            title;

    private List<String>      body;
}
