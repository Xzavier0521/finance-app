package finance.api.model.vo.coin;
/**
 * @program: finance-server
 *
 * @description: 参加活动VO
 *
 * @author: MORUIHAI
 *
 * @create: 2018-08-22 09:27
 **/
public class JoinTaskBaseVO {
	private Long totalJoinNum;
	private Long totalCoinNum;

	public Long getTotalJoinNum() {
		return totalJoinNum;
	}

	public void setTotalJoinNum(Long totalJoinNum) {
		this.totalJoinNum = totalJoinNum;
	}

	public Long getTotalCoinNum() {
		return totalCoinNum;
	}

	public void setTotalCoinNum(Long totalCoinNum) {
		this.totalCoinNum = totalCoinNum;
	}

	@Override
	public String toString() {
		return "JoinTaskBaseVO{" + "totalJoinNum=" + totalJoinNum + ", totalCoinNum=" + totalCoinNum + '}';
	}
}
