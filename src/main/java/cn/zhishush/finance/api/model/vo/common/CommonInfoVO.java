package cn.zhishush.finance.api.model.vo.common;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>公共信息</p>
 *
 * @author lili
 * @version 1.0: CommonVO.java, v0.1 2018/11/28 2:05 PM PM lili Exp $
 */
@Data
public class CommonInfoVO implements Serializable {

    private static final long serialVersionUID = 7715356788114163017L;
    /**
     * ip 地址
     */
    private String            ipAddress;
}
