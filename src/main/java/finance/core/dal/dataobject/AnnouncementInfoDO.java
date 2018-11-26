package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: AnnouncementInfoDO.java, v0.1 2018/11/14 6:53 PM lili Exp $
 */
@Data
public class AnnouncementInfoDO implements Serializable {
    private static final long serialVersionUID = -5290425997552226851L;
    private Long              id;

    private String            announcementTitle;

    private String            announcementContext;

    private Integer           isShow;

    private Date              createTime;

    private Date              updateTime;

    private Integer           isDelete;

    private String            creator;

    private String            updator;

    private Integer           version;

}