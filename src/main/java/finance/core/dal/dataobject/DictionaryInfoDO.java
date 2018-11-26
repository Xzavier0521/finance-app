package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: DictionaryInfoDO.java, v0.1 2018/11/25 4:41 PM lili Exp $
 */
@Data
public class DictionaryInfoDO implements Serializable {
    private static final long serialVersionUID = 4301402306353866453L;
    private Long              id;

    private Integer           businessCode;

    private String            businessName;

    private Integer           businessType;

    private String            status;

    private Date              createTime;

    private Date              updateTime;

    private Integer           isDelete;

    private String            creater;

    private String            updater;

    private Integer           version;

}