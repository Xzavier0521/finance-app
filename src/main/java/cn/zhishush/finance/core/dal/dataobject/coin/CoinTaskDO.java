package cn.zhishush.finance.core.dal.dataobject.coin;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: CoinTaskDO.java, v0.1 2018/11/14 6:59 PM lili Exp $
 */
@Data
public class CoinTaskDO implements Serializable {
    private static final long serialVersionUID = 6781579419572788281L;
    private Long              id;

    private String            taskType;

    private String            taskName;

    private String            taskDesc;

    private String            effect;

    private Integer           num;

    private String            logoUrl;

    private String            redirectUrl;

    private Integer           status;

    private Integer           isDelete;

    private String            creator;

    private String            updator;

    private Integer           version;

    private Date              createTime;

    private Date              updateTime;

}