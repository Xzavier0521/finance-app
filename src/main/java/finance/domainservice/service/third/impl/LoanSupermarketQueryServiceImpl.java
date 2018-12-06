package finance.domainservice.service.third.impl;

import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import finance.api.model.vo.third.LoanSupermarketStatisticsDataVO;
import finance.domain.third.ThirdChannelConfig;
import finance.domain.user.UserInfo;
import finance.domainservice.repository.ThirdChannelConfigRepository;
import finance.domainservice.repository.UserRegisterChannelInfoRepository;
import finance.domainservice.service.third.LoanSupermarketQueryService;

/**
 * <p>贷超数据查询</p>
 *
 * @author lili
 * @version 1.0: LoanSupermarketQueryServiceImpl.java, v0.1 2018/12/5 6:43 PM PM lili Exp $
 */
@Service("loanSupermarketQueryService")
public class LoanSupermarketQueryServiceImpl implements LoanSupermarketQueryService {

    @Resource
    private ThirdChannelConfigRepository      thirdChannelConfigRepository;

    @Resource
    private UserRegisterChannelInfoRepository userRegisterChannelInfoRepository;

    @Override
    public LoanSupermarketStatisticsDataVO queryStatisticsData(UserInfo userInfo) {

        LoanSupermarketStatisticsDataVO loanSupermarketStatisticsDataVO = new LoanSupermarketStatisticsDataVO();
        ThirdChannelConfig thirdChannelConfig = thirdChannelConfigRepository
            .query(userInfo.getMobileNum());
        if (Objects.isNull(thirdChannelConfig)) {
            return loanSupermarketStatisticsDataVO;
        }
        loanSupermarketStatisticsDataVO.setChannelCode(thirdChannelConfig.getChannelCode());
        loanSupermarketStatisticsDataVO.setChannelName(thirdChannelConfig.getChannelName());
        loanSupermarketStatisticsDataVO.setRegisterNum(userRegisterChannelInfoRepository
            .countRegisterNumByChannel(thirdChannelConfig.getChannelCode()));
        loanSupermarketStatisticsDataVO.setRegisterDetails(userRegisterChannelInfoRepository
            .queryStatisticsData(thirdChannelConfig.getChannelCode()));
        return loanSupermarketStatisticsDataVO;
    }
}
