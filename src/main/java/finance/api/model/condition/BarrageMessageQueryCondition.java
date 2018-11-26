package finance.api.model.condition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 弹幕消息查询条件
 * </p>
 *
 * @author lili
 * @version 1.0: BarrageMessageQueryCondition.java, v 0.1 2018/9/29 上午10:02 lili
 *          Exp $
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BarrageMessageQueryCondition implements Serializable {

	private static final long serialVersionUID = 5949033495184343743L;
	/**
	 * 每页记录数
	 */
	private int pageSize;
	/**
	 * 第几页
	 */
	private int pageNum;

}
