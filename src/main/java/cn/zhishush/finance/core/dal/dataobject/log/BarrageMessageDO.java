package cn.zhishush.finance.core.dal.dataobject.log;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: BarrageMessageDO.java, v0.1 2018/11/14 6:55 PM lili Exp $
 */
@Data
public class BarrageMessageDO implements Serializable {
    private static final long serialVersionUID = 3145966117980507029L;
    private Long              id;

    private String            messageCode;

    private String            messageDesc;

    private String            creator;

    private String            updator;

    private Integer           version;

    private Date              createTime;

    private Date              updateTime;

}