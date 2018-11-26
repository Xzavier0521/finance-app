package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version $Id: NewsInfoDO.java, v0.1 2018/11/25 4:45 PM lili Exp $
 */
@Data
public class NewsInfoDO implements Serializable {
    private static final long serialVersionUID = -7417862791188070628L;
    private Long              id;

    private String            title;

    private String            tag1;

    private String            tag2;

    private String            bannerUrl;

    private String            redirectUrl;

    private Integer           state;

    private Date              createTime;

    private Date              updateTime;

    private String            creater;

    private String            updater;

    private Long              version;

    private Integer           isDelete;

}