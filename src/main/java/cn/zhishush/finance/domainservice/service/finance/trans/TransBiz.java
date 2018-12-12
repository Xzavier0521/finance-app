package cn.zhishush.finance.domainservice.service.finance.trans;

import java.util.List;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.response.ResponseResult;
import cn.zhishush.finance.api.model.vo.financeProfit.FinanceProfitVO;
import cn.zhishush.finance.api.model.vo.transRecord.FinanceOrderVo;
import cn.zhishush.finance.core.dal.dataobject.user.UserInfoDO;
import cn.zhishush.finance.domain.dto.UserWithdrawDto;

/**
 * @author yaolei
 * @Title: TransBiz
 * @ProjectName finance-app
 * @Description: 交易
 * @date 2018/7/6下午1:41
 */
public interface TransBiz {
	/**
	 * 提现接口
	 * 
	 * @param
	 * @return
	 */
	ResponseResult<String> withdraw(UserInfoDO userInfo, UserWithdrawDto paramDto);

	/**
	 * 查询我的返现记录.
	 * 
	 * @param userId
	 * @return
	 */
	List<FinanceProfitVO> myProfit(Long userId, Page<FinanceProfitVO> page);

	/**
	 * 注册充值
	 * 
	 * @param userId
	 * @param userName
	 * @return
	 */
	ResponseResult<String> recharge(Long userId, String userName);

	/**
	 * 查询交易记录
	 * 
	 * @param id
	 * @return
	 */
	List<FinanceOrderVo> transRecords(Long id, Page<FinanceOrderVo> page);
}
