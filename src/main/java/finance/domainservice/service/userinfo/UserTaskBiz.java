package finance.domainservice.service.userinfo;

import java.util.List;

import finance.api.model.base.XMap;
import finance.api.model.response.ResponseResult;
import finance.api.model.vo.coin.EverydayClockVO;
import finance.api.model.vo.coin.GrowTaskVO;
import finance.api.model.vo.coin.NewRegisterListVO;

/**
 * @program: finance-server
 * @description: ${description}
 * @author: MORUIHAI
 * @create: 2018-08-26 11:47
 **/
public interface UserTaskBiz {
	/**
	 * 功能描述:每日签到展示
	 * 
	 * @author: moruihai
	 * @date: 2018/8/27 14:01
	 * @param: []
	 * @return: finance.model.vo.EverydayClockVO
	 */
	EverydayClockVO findEveryDayClockInfo();

	/**
	 * 功能描述:完成任务
	 * 
	 * @author: moruihai
	 * @date: 2018/8/27 14:00
	 * @param: [type]
	 * @return: finance.model.finance.ResponseResult<java.lang.Boolean>
	 */
	ResponseResult<Boolean> finishTask(XMap xMap);

	/**
	 * 功能描述:查询新手任务
	 * 
	 * @author: moruihai
	 * @date: 2018/8/28 14:02
	 * @param: []
	 * @return: java.util.List<finance.model.vo.NewRegisterListVO>
	 */
	List<NewRegisterListVO> queryNewTaskInfo();

	/**
	 * 功能描述:查询成长任务
	 * 
	 * @author: moruihai
	 * @date: 2018/8/28 14:02
	 * @param: []
	 * @return: finance.model.vo.GrowTaskVO
	 */
	GrowTaskVO queryGrowTaskInfo();
}
