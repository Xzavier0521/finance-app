package cn.zhishush.finance.domain.product;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>产品信息</p>
 *
 * @author lili
 * @version 1.0: ProductInfo.java, v0.1 2018/11/28 8:45 PM PM lili Exp $
 */
@Data
public class ProductInfo implements Serializable {
    private static final long serialVersionUID = -1371059356634090855L;

    private String            productCode;

    private String            productName;
}
