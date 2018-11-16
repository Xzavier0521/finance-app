package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceUserRegisterChannelInfo.java, v0.1 2018/11/14 7:16 PM lili Exp $
 */
@Data
public class FinanceUserRegisterChannelInfo implements Serializable {
    private static final long serialVersionUID = 8516156763378964800L;
    private Long              id;

    private Long              userId;

    private String            channelCode;

    private String            channelDetail;

    private String            platformCode;

    private String            platformDetail;

    private String            approach1;

    private String            approach2;

    private String            approach3;

    private String            approach4;

    private String            approach5;

    private String            approach6;

    private String            approach7;

    private String            approach8;

    private String            approach9;

    private String            approach10;

    private Integer           isDelete;

    private String            creator;

    private String            updator;

    private Integer           version;

    private Date              createTime;

    private Date              updateTime;

}