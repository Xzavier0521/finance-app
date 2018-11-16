package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 *  <p>注释</p>
 * @author  lili
 * @version :1.0  FinanceUserInviteInfo.java.java, v 0.1 2018/9/28 下午8:44 lili Exp $
 */
@Data
public class FinanceUserInviteInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long              id;

    private Long              userId;

    private Long              parentUserId;

    private Integer           inviteType;

    private Integer           isDelete;

    private String            creator;

    private String            updator;

    private Integer           version;

    private Date              createTime;

    private Date              updateTime;

    private String            isPayCoin;

}