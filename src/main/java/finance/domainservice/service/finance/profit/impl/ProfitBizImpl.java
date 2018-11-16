package finance.domainservice.service.finance.profit.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import finance.api.model.base.Page;
import finance.api.model.vo.financeProfit.FinanceProfitVO;
import finance.core.common.util.DateUtil;
import finance.core.common.util.HideUtil;
import finance.domainservice.service.finance.profit.ProfitBiz;
import finance.core.dal.dao.FinanceProfitDAO;
import finance.core.dal.dataobject.FinanceProfit;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: ProfitBizImpl.java, v0.1 2018/11/14 1:54 PM lili Exp $
 */
@Component
public class ProfitBizImpl implements ProfitBiz {

    @Resource
    public FinanceProfitDAO profitMapper;

    /**
     * 查询余额
     * @param userId
     * @return
     */
    @Override
    public List<FinanceProfitVO> myProfit(Long userId, Page<FinanceProfitVO> page) {
        List<FinanceProfitVO> res = new ArrayList<FinanceProfitVO>();
        List<FinanceProfit> ps = profitMapper.selectProfitsByUserId(userId, page);
        for (FinanceProfit profit : ps) {
            FinanceProfitVO vo = new FinanceProfitVO();
            vo.setCreateTime(
                DateUtil.dateToString(profit.getCreateTime(), DateUtil.fmx_yyyy_MM_dd));
            vo.setProdName(profit.getProdName());
            vo.setTerminalName(profit.getTerminalName());
            vo.setTerminalPhone(profit.getTerminalPhone());
            if (profit.getGrandParentId() != null
                && profit.getGrandParentId().intValue() == userId.intValue()) {
                vo.setTerminalPhone(HideUtil.hideMobile(profit.getTerminalPhone()));
                vo.setTerminalMoney(profit.getGrandParentMoney());
            } else if (profit.getParentId() != null
                       && profit.getParentId().intValue() == userId.intValue()) {
                vo.setTerminalMoney(profit.getParentMoney());
            } else {
                vo.setTerminalMoney(profit.getTerminalMoney());
            }
            res.add(vo);
        }
        return res;
    }
}
