package cn.zhishush.finance.web.controller.response;

import java.util.List;
import java.util.Objects;

import cn.zhishush.finance.core.common.util.CommonUtils;
import cn.zhishush.finance.core.common.util.ConvertBeanUtil;
import cn.zhishush.finance.domain.activity.LeaderBoard;
import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

import cn.zhishush.finance.api.model.response.LeaderBoardQueryResponse;
import cn.zhishush.finance.api.model.vo.redenvelope.LeaderBoardVO;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version $Id: LeaderBoardResponseBuilder.java, v0.1 2018/10/20 9:54 AM lili Exp $
 */
public class LeaderBoardResponseBuilder {

	public static LeaderBoardQueryResponse build(List<LeaderBoard> leaderBoards) {

		List<LeaderBoardVO> items = Lists.newArrayList();
		if (CollectionUtils.isNotEmpty(leaderBoards)) {
			LeaderBoardVO item;
			for (LeaderBoard leaderBoard : leaderBoards) {
				item = new LeaderBoardVO();
				ConvertBeanUtil.copyBeanProperties(leaderBoard, item);
				if (Objects.nonNull(leaderBoard) && Objects.nonNull(leaderBoard.getRewardAmount())) {
					item.setRewardAmount(CommonUtils.decimalFormat(leaderBoard.getRewardAmount()));
				} else {
					item.setRewardAmount("");
				}
				item.setRanking(String.valueOf(leaderBoard.getRanking()));
				items.add(item);
			}
		}
		return LeaderBoardQueryResponse.builder().items(items).build();
	}

}
