package cn.zhishush.finance.core.dal.dataobject.news;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>QA</p>
 * @author lili
 * @version 1.0: QuestionAndAnswerDO.java, v0.1 2018/11/26 4:41 PM lili Exp $
 */
@Data
public class QuestionAndAnswerDO implements Serializable {
    private static final long serialVersionUID = 5719172796934757943L;
    private Long              id;

    private String            title;

    private String            content;

    private String            imageUrl;

    private String            reservedField;

    private Date              createTime;

    private Date              updateTime;

    private String            creater;

    private String            updater;

    private Long              versionNum;

    private Integer           isDelete;
    private Integer           seqNo;

}