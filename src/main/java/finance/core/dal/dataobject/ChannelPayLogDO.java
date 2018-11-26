package finance.core.dal.dataobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version $Id: ChannelPayLogDO.java, v0.1 2018/11/25 1:39 PM lili Exp $
 */
@Data
public class ChannelPayLogDO implements Serializable {
    private static final long serialVersionUID = 6173975365182554667L;
    private Long              id;

    private String            billNo;

    private String            channel;

    private BigDecimal        money;

    private String            accountName;

    private String            accountNo;

    private String            bankName;

    private String            resultCode;

    private String            resultMsg;

    private Integer           isDelete;

    private String            creator;

    private String            updator;

    private Integer           version;

    private Date              createTime;

    private Date              updateTime;

}