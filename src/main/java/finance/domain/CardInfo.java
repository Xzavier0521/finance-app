package finance.domain;

import lombok.Data;

import java.util.Date;

@Data
public class CardInfo {

    private Long id;

    private Long userId;

    private String realName;

    private String idNum;

    private Integer authStatus;

    private Date createTime;

    private Date updateTime;

    private String creater;

    private String updater;

    private Long version;

    private Integer isDelete;
}
