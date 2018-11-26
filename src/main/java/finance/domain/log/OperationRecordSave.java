package finance.domain.log;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: OperationRecordSave.java, v0.1 2018/11/6 2:39 PM lili Exp $
 */
@Data
public class OperationRecordSave implements Serializable {

	private static final long serialVersionUID = -4506709755108454661L;
	/**
	 * 产品id
	 */
	private Long productId;

	/**
	 * 产品名称
	 */
	private String productName;
}
