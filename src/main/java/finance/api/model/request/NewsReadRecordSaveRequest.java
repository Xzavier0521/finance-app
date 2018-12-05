package finance.api.model.request;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>资讯</p>
 *
 * @author lili
 * @version 1.0: NewsReadRecordSaveRequest.java, v0.1 2018/12/5 5:29 PM PM lili Exp $
 */
@Data
public class NewsReadRecordSaveRequest implements Serializable {

    private static final long serialVersionUID = 485820898950032683L;
    private Long              newsId;
}
