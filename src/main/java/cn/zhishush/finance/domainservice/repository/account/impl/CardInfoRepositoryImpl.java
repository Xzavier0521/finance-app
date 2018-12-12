package cn.zhishush.finance.domainservice.repository.account.impl;

import cn.zhishush.finance.domain.coin.CardInfo;
import cn.zhishush.finance.domainservice.converter.account.CardInfoConverter;
import cn.zhishush.finance.domainservice.repository.account.CardInfoRepository;
import cn.zhishush.finance.core.dal.dao.account.IdCardInfoDAO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>注释</p>
 * 
 * @author lili
 * @version $Id: CardInfoRepositoryImpl.java, v0.1 2018/10/5 8:07 PM lili Exp $
 */
@Repository("cardInfoRepository")
public class CardInfoRepositoryImpl implements CardInfoRepository {

	@Resource
	private IdCardInfoDAO financeIdCardInfoMapper;

	@Override
	public List<CardInfo> selectByUserIdList(List<Long> userIds) {
		return CardInfoConverter.convert2List(financeIdCardInfoMapper.selectByUserIdList(userIds));
	}
}
