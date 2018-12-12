package cn.zhishush.finance.domainservice.service.third.impl;

import java.util.Objects;

import javax.annotation.Resource;

import cn.zhishush.finance.api.model.vo.third.LoanSupermarketStatisticsDataVO;
import cn.zhishush.finance.domain.third.ThirdChannelConfig;
import cn.zhishush.finance.domain.user.UserInfo;
import cn.zhishush.finance.domainservice.repository.third.ThirdChannelConfigRepository;
import cn.zhishush.finance.domainservice.repository.user.UserRegisterChannelInfoRepository;
import cn.zhishush.finance.domainservice.service.third.LoanSupermarketQueryService;
import org.springframework.stereotype.Service;

/**
 * <p>贷超数据查询</p>
 *
 * @author lili
 * @version 1.0: LoanSupermarketQueryServiceImpl.java, v0.1 2018/12/5 6:43 PM PM lili Exp $
 */
@Service("loanSupermarketQueryService")
public class LoanSupermarketQueryServiceImpl implements LoanSupermarketQueryService {

    @Resource
    private ThirdChannelConfigRepository thirdChannelConfigRepository;

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
