package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceQuestionAndAnswer.java, v0.1 2018/11/14 7:09 PM lili Exp $
 */
@Data
public class FinanceQuestionAndAnswer implements Serializable {
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