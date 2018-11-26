package finance.api.model.vo.activity;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: ActivityListVO.java, v0.1 2018/11/14 3:26 PM lili Exp $
 */
@Data
public class ActivityListVO implements Serializable {

	private static final long serialVersionUID = 3862703643895468516L;
	private Long id;

	private String taskName;

	private String logoUrl;

	private String redirectUrl;

}
