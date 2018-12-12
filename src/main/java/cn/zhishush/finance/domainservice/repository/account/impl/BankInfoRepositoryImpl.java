package cn.zhishush.finance.domainservice.repository.account.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.core.dal.dao.product.BankInfoDAO;
import cn.zhishush.finance.domain.creditcard.BankInfo;
import cn.zhishush.finance.domainservice.converter.account.BankInfoConverter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

import cn.zhishush.finance.domainservice.repository.account.BankInfoRepository;

/**
 * <p>银行信息</p>
 *
 * @author lili
 * @version 1.0: BankInfoRepositoryImpl.java, v0.1 2018/11/28 11:36 PM PM lili Exp $
 */
@Repository("bankInfoRepository")
public class BankInfoRepositoryImpl implements BankInfoRepository {

    @Resource
    private BankInfoDAO bankInfoDAO;

    @Override
    public Page<BankInfo> query(int pageSize, int pageNum) {
        Page<BankInfo> page = new Page<>(pageSize, (long) pageNum);
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("page", page);
        int count = bankInfoDAO.count(parameters);
        page.setTotalCount((long) count);
        if (count > 0) {
            page.setDataList(BankInfoConverter.convert2List(bankInfoDAO.query(parameters)));
        }
        return page;
    }

    @Override
    public BankInfo query(String bankCode) {
        BankInfo bankInfo = null;
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("bankCode", bankCode);
        List<BankInfo> bankInfoList = BankInfoConverter.convert2List(bankInfoDAO.query(parameters));
        if (CollectionUtils.isNotEmpty(bankInfoList)) {
            bankInfo = bankInfoList.get(0);
        }
        return bankInfo;
    }
}
