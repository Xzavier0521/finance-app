package cn.zhishush.finance.api.model.vo.info;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>注释</p>
 * 
 * @author lili
 * @version $Id: BannerDetailVO.java, v0.1 2018/11/14 3:28 PM lili Exp $
 */
@Data
public class BannerDetailVO implements Serializable {
    private static final long serialVersionUID = -9180008003367403767L;
    private Long              id;
    private Long              pageCode;
    private Long              bannerType;
    private String            title;
    private Integer           seqNo;
    private String            bannerUrl;
    private String            redirectUrl;
}
