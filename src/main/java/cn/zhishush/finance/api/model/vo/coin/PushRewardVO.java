package cn.zhishush.finance.api.model.vo.coin;

import java.io.Serializable;

import lombok.Data;

@Data
public class PushRewardVO implements Serializable {

	private static final long serialVersionUID = 5377904050978983222L;
	private Integer rewardCoin;

	private Integer signCoin;

	/**
	 * 是否首次调用
	 */
	private Boolean isFirstCall;

}
