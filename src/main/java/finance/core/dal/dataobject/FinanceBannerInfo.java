package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceBannerInfo.java, v0.1 2018/11/14 6:53 PM lili Exp $
 */
@Data
public class FinanceBannerInfo implements Serializable {
    private static final long serialVersionUID = 3337502201161812450L;
    private Long              id;

    private Integer           pageCode;

    private Integer           bannerType;

    private String            title;

    private Integer           seqNo;

    private String            bannerUrl;

    private String            redirectUrl;

    private Date              createTime;

    private Date              updateTime;

    private Integer           isDelete;

    private String            creator;

    private String            updator;

    private Integer           version;

}