package finance.domainservice.repository.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

import finance.api.model.base.Page;
import finance.core.dal.dao.LoanInfoDAO;
import finance.domain.loan.LoanInfo;
import finance.domainservice.converter.LoanInfoConverter;
import finance.domainservice.repository.LoanInfoRepository;

/**
 * <p>贷款产品信息</p>
 *
 * @author lili
 * @version 1.0: LoanInfoRepositoryImpl.java, v0.1 2018/11/29 1:43 PM PM lili Exp $
 */
@Repository("loanInfoRepository")
public class LoanInfoRepositoryImpl implements LoanInfoRepository {

    @Resource
    private LoanInfoDAO loanInfoDAO;

    @Override
    public Page<LoanInfo> query(int pageSize, int pageNum) {

        Map<String, Object> parameters = Maps.newHashMap();
        Page<LoanInfo> page = new Page<>(pageNum, (long) pageNum);
        parameters.put("page", page);
        int count = loanInfoDAO.count(parameters);
        page.setTotalCount((long) count);
        if (count > 0) {
            page.setDataList(LoanInfoConverter.convert2List(loanInfoDAO.query(parameters)));
        }
        return page;
    }

    @Override
    public LoanInfo query(String productCode) {
        LoanInfo loanInfo = null;
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("productCode", productCode);
        List<LoanInfo> loanInfoList = LoanInfoConverter.convert2List(loanInfoDAO.query(parameters));
        if (CollectionUtils.isNotEmpty(loanInfoList)) {
            loanInfo = loanInfoList.get(0);
        }
        return loanInfo;
    }
}
