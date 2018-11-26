package finance.api.model.vo.coin;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: finance-server
 *
 * @description: 每日签到
 *
 * @author: MORUIHAI
 *
 * @create: 2018-08-26 16:34
 **/
public class EverydayClockVO {
	private Integer coinNum;
	private Integer totalCoinNum;
	private int sigNum;
	private List<EverydayClockDateListVO> signList = new ArrayList<>();

	private Integer currentCanUseTotalCoin;

	public Integer getCurrentCanUseTotalCoin() {
		return currentCanUseTotalCoin;
	}

	public void setCurrentCanUseTotalCoin(Integer currentCanUseTotalCoin) {
		this.currentCanUseTotalCoin = currentCanUseTotalCoin;
	}

	public Integer getCoinNum() {
		return coinNum;
	}

	public void setCoinNum(Integer coinNum) {
		this.coinNum = coinNum;
	}

	public Integer getTotalCoinNum() {
		return totalCoinNum;
	}

	public void setTotalCoinNum(Integer totalCoinNum) {
		this.totalCoinNum = totalCoinNum;
	}

	public int getSigNum() {
		return sigNum;
	}

	public void setSigNum(int sigNum) {
		this.sigNum = sigNum;
	}

	public List<EverydayClockDateListVO> getSignList() {
		return signList;
	}

	public void setSignList(List<EverydayClockDateListVO> signList) {
		this.signList = signList;
	}

	@Override
	public String toString() {
		return "EverydayClockVO{" + "coinNum=" + coinNum + ", totalCoinNum=" + totalCoinNum + ", sigNum=" + sigNum
				+ ", signList=" + signList + '}';
	}
}
