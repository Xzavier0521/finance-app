package finance.api.model.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>公告列表</p>
 * @author lili
 * @version $Id: AnnouncementListVO.java, v0.1 2018/11/14 3:27 PM lili Exp $
 */
@Data
public class AnnouncementListVO implements Serializable {
    private static final long serialVersionUID = 1768766175345419850L;
    private String            announcementTitle;
    private String            announcementContext;
    private String            announcementType;
    private Long              id;

}
