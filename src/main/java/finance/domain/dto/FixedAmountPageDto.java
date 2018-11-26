package finance.domain.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: FixedAmountPageDto.java, v0.1 2018/11/14 3:45 PM lili Exp $
 */
@Data
public class FixedAmountPageDto implements Serializable {
	private static final long serialVersionUID = 3902951413477708973L;
	private String pageType;
	private int pageSize;
	private Long pageNum;
	private Long activityId;

}
