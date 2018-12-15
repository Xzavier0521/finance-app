package cn.zhishush.finance.domainservice.repository.third.impl.product.impl;

import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.core.dal.dao.product.LoanInfoDAO;
import cn.zhishush.finance.domain.loan.LoanInfo;
import cn.zhishush.finance.domainservice.converter.product.LoanInfoConverter;
import cn.zhishush.finance.domainservice.repository.third.impl.product.LoanDetailsRepository;
import cn.zhishush.finance.domainservice.repository.third.impl.product.LoanInfoRepository;

import com.google.common.collect.Maps;

/**
 * <p>贷款产品信息</p>
 *
 * @author lili
 * @version 1.0: LoanInfoRepositoryImpl.java, v0.1 2018/11/29 1:43 PM PM lili Exp $
 */
@Repository("loanInfoRepository")
public class LoanInfoRepositoryImpl implements LoanInfoRepository {

    @Resource
    private LoanInfoDAO           loanInfoDAO;

    @Resource
    private LoanDetailsRepository loanDetailsRepository;

    @Override
    public Page<LoanInfo> query(int pageSize, int pageNum) {

        Map<String, Object> parameters = Maps.newHashMap();
        Page<LoanInfo> page = new Page<>(pageSize, (long) pageNum);
        parameters.put("page", page);
        int count = loanInfoDAO.count(parameters);
        page.setTotalCount((long) count);
        if (count > 0) {
            List<LoanInfo> loanInfoList = LoanInfoConverter
                .convert2List(loanInfoDAO.query(parameters));
            loanInfoList.forEach(loanInfo -> loanInfo
                .setAvgOrderAmount(loanDetailsRepository.query(loanInfo.getProductCode())
                    .getAvgOrderAmount().setScale(0, RoundingMode.HALF_UP).toString()));
            page.setDataList(loanInfoList);
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

    @Override
    public List<LoanInfo> query() {
        Map<String, Object> parameters = Maps.newHashMap();
        return LoanInfoConverter.convert2List(loanInfoDAO.query(parameters));
    }
}
