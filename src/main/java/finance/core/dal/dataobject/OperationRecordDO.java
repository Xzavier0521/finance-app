package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: OperationRecordDO.java, v0.1 2018/11/14 7:06 PM lili Exp $
 */
@Data
public class OperationRecordDO implements Serializable {
    private static final long serialVersionUID = -7254872540901265120L;
    private Long              id;

    private Long              userId;

    private String            realName;

    private String            mobileNum;

    private Long              productId;

    private String            productName;

    private Integer           productType;

    private Date              operationTime;

    private String            status;

    private String            reservedField;

    private Date              createTime;

    private Date              updateTime;

    private String            creater;

    private String            updater;

    private Long              versionNum;

    private Integer           isDelete;

}