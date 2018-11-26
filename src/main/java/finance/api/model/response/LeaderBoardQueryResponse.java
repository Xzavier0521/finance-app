package finance.api.model.response;

import java.io.Serializable;
import java.util.List;

import finance.api.model.vo.redenvelope.LeaderBoardVO;
import lombok.Builder;
import lombok.Data;

/**
 * <p>排行榜</p>
 * @author lili
 * @version 1.0: LeaderBoardQueryResponse.java, v0.1 2018/11/26 7:03 PM lili Exp $
 */
@Data
@Builder
public class LeaderBoardQueryResponse implements Serializable {

	private static final long serialVersionUID = 8415927462822335842L;
	/**
	 * 结果集
	 */
	private List<LeaderBoardVO> items;

}
