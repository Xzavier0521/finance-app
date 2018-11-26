package finance.api.model.response;

import lombok.Data;
import finance.api.model.base.Page;
import finance.api.model.vo.redenvelope.ParticipantInfoVO;

/**
 * <p>注释</p>
 * @author lili
 * @version 1.0: RedEnvelopeDetailQueryResponse.java, v0.1 2018/11/26 7:04 PM lili Exp $
 */
@Data
public class RedEnvelopeDetailQueryResponse {

	/**
	 * 红包活动代码
	 */
	private String activityCode;
	/**
	 * 总参加人数
	 */
	private Long joinNumbers;

	/**
	 * 参与者明细
	 */
	private Page<ParticipantInfoVO> items;
}
