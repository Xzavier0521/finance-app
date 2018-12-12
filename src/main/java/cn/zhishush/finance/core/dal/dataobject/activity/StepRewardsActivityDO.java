package cn.zhishush.finance.core.dal.dataobject.activity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version 1.0: StepRewardsActivityDO.java, v0.1 2018/11/26 4:42 PM lili Exp $
 */
@Data
public class StepRewardsActivityDO implements Serializable {
    private static final long serialVersionUID = -6766765364483980739L;
    private Long              id;

    private Long              userId;

    private Integer           inviteNum;

    private Date              createTime;

    private Date              updateTime;

    private String            creater;

    private String            updater;

    private Long              versionNum;

    private Integer           isDelete;

}