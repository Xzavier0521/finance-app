package cn.zhishush.finance.domainservice.repository.third.impl.product.impl;

import java.util.Map;

import javax.annotation.Resource;

import cn.zhishush.finance.core.dal.dao.order.LoanApplyInfoDAO;
import cn.zhishush.finance.domain.loan.LoanApplyInfo;
import cn.zhishush.finance.domainservice.converter.product.LoanApplyInfoConverter;
import cn.zhishush.finance.domainservice.repository.third.impl.product.LoanApplyInfoRepository;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

import cn.zhishush.finance.api.model.base.Page;

/**
 * <p>贷款申请记录</p>
 *
 * @author lili
 * @version 1.0: LoanApplyInfoRepositoryImpl.java, v0.1 2018/11/29 3:35 AM PM lili Exp $
 */
@Repository("loanApplyInfoRepository")
public class LoanApplyInfoRepositoryImpl implements LoanApplyInfoRepository {

    @Resource
    private LoanApplyInfoDAO loanApplyInfoDAO;

    @Override
    public int save(LoanApplyInfo loanApplyInfo) {
        return loanApplyInfoDAO.insertSelective(LoanApplyInfoConverter.convert(loanApplyInfo));
    }

    @Override
    public Page<LoanApplyInfo> query(Long userId, int pageSize, int pageNum) {
        Page<LoanApplyInfo> page = new Page<>(pageSize, (long) pageNum);
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("userId", userId);
        parameters.put("page", page);
        int count = loanApplyInfoDAO.count(parameters);
        page.setTotalCount((long) count);
        if (count > 0) {
            page.setDataList(
                LoanApplyInfoConverter.convert2List(loanApplyInfoDAO.query(parameters)));
        }
        return page;
    }
}
