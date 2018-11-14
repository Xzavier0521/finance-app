package finance.domainservice.repository.impl;

import javax.annotation.Resource;

import finance.mapper.FinanceThirdAccountInfoDAO;
import org.springframework.stereotype.Repository;

import finance.domain.ThirdAccountInfo;
import finance.domainservice.converter.ThirdAccountInfoConverter;
import finance.domainservice.repository.ThirdAccountInfoRepository;
import finance.model.po.FinanceThirdAccountInfo;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: ThirdAccountInfoRepositoryImpl.java, v0.1 2018/10/24 3:40 PM lili Exp $
 */
@Repository("thirdAccountInfoRepository")
public class ThirdAccountInfoRepositoryImpl implements ThirdAccountInfoRepository {

    @Resource
    private FinanceThirdAccountInfoDAO financeThirdAccountInfoMapper;

    /**
     * 查询用户绑定信息
     *
     * @param userId 用户id
     * @return ThirdAccountInfo
     */
    @Override
    public ThirdAccountInfo queryByCondition(Long userId) {

        FinanceThirdAccountInfo financeThirdAccountInfo = new FinanceThirdAccountInfo();
        financeThirdAccountInfo.setChannel("wechatPub");
        financeThirdAccountInfo.setUserId(userId);
        return ThirdAccountInfoConverter
            .convert(financeThirdAccountInfoMapper.selectOneBySelective(financeThirdAccountInfo));
    }
}
