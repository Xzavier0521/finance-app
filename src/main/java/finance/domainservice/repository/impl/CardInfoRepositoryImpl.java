package finance.domainservice.repository.impl;

import finance.domain.CardInfo;
import finance.domainservice.converter.CardInfoConverter;
import finance.domainservice.repository.CardInfoRepository;
import finance.mapper.FinanceIdCardInfoDAO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: CardInfoRepositoryImpl.java, v0.1 2018/10/5 8:07 PM lili Exp $
 */
@Repository("cardInfoRepository")
public class CardInfoRepositoryImpl implements CardInfoRepository {

    @Resource
    private FinanceIdCardInfoDAO financeIdCardInfoMapper;

    @Override
    public List<CardInfo> selectByUserIdList(List<Long> userIds) {
        return CardInfoConverter.convert2List(financeIdCardInfoMapper.selectByUserIdList(userIds));
    }
}
