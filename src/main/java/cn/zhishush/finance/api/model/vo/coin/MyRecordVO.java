package cn.zhishush.finance.api.model.vo.coin;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * <p>
 * 我的战绩
 * </p>
 * 
 * @author lili
 * @version $Id: MyRecordVO.java, v0.1 2018/11/15 10:12 AM lili Exp $
 */

@Data
public class MyRecordVO implements Serializable {

	private static final long serialVersionUID = 4612388400322311820L;
	private Integer totalOutCoin;
	private Integer totalInCoin;
	private Integer totalSign;
	private List<MyCoinGameLogVO> records;

}
