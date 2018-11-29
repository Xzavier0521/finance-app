package finance.domainservice.repository.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

import finance.api.model.base.Page;
import finance.core.dal.dao.LoanApplyInfoDAO;
import finance.domain.loan.LoanApplyInfo;
import finance.domainservice.converter.LoanApplyInfoConverter;
import finance.domainservice.repository.LoanApplyInfoRepository;

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
