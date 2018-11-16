package finance.model.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceUserInfo.java, v0.1 2018/11/14 7:14 PM lili Exp $
 */
@Data
public class FinanceUserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long              id;

    private String            mobileNum;

    private String            inviteCode;

    private String            status;

    private Integer           isDelete;

    private String            creator;

    private String            updator;

    private Integer           version;

    private Date              createTime;

    private Date              updateTime;

}