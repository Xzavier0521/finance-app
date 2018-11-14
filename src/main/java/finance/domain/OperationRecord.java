package finance.domain;

import lombok.Data;

import java.util.Date;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: OperationRecord.java, v 0.1 2018/9/28 上午11:11 lili Exp $
 */
@Data
public class OperationRecord {

    private Long id;
    private Long userId;
    private String realName;
    private String mobileNum;
    private Long productId;
    private String productName;
    private Integer productType;
    private Date operationTime;
    private String status;
    private String reservedField;
    private Date createTime;
    private Date updateTime;
    private String creater;
    private String updater;
    private Long versionNum;
    private Integer isDelete;
}
