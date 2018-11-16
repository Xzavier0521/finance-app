package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceGiftInfo.java, v0.1 2018/11/14 7:03 PM lili Exp $
 */
@Data
public class FinanceGiftInfo implements Serializable {
    private static final long serialVersionUID = 5724083717984928555L;
    private Long              id;

    private String            giftName;

    private String            bannerUrl;

    private String            thumbnailUrl;

    private Integer           needCoinNum;

    private Integer           status;

    private Date              createTime;

    private Date              updateTime;

    private Integer           isDelete;

    private String            creator;

    private String            updator;

    private Integer           version;

}