package finance.model.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: BusinessCooperationDO.java, v0.1 2018/10/29 4:46 PM lili Exp $
 */
@Data
public class BusinessCooperationDO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *  主键
     */
    private Long              id;
    /**
     * 姓名
     */
    private String            realName;
    /**
     * 手机号码
     */
    private String            mobilePhone;
    /**
     * 创建时间
     */
    private Date              createTime;
    /**
     * 更新时间
     */
    private Date              updateTime;
    /**
     * 是否删除：0-否，1-是
     */
    private String            isDelete;
    /**
     * 创建者
     */
    private String            creator;
    /**
     * 更新者
     */
    private String            updator;
    /**
     * 版本号
     */
    private Integer           version;

}