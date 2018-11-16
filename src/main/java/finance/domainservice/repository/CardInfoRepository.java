package finance.domainservice.repository;

import finance.domain.coin.CardInfo;
import java.util.List;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: CardInfoRepository.java, v0.1 2018/10/5 8:07 PM lili Exp $
 */
public interface CardInfoRepository {

    List<CardInfo> selectByUserIdList(List<Long> userIds);
}
