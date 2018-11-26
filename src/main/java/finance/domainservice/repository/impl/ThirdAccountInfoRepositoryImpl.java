package finance.domainservice.repository.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

import finance.core.dal.dao.ThirdAccountInfoDAO;
import finance.domain.user.ThirdAccountInfo;
import finance.domainservice.converter.ThirdAccountInfoConverter;
import finance.domainservice.repository.ThirdAccountInfoRepository;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: ThirdAccountInfoRepositoryImpl.java, v0.1 2018/10/24 3:40 PM
 *          lili Exp $
 */
@Repository("thirdAccountInfoRepository")
public class ThirdAccountInfoRepositoryImpl implements ThirdAccountInfoRepository {

	@Resource
	private ThirdAccountInfoDAO thirdAccountInfoDAO;

	/**
	 * 查询用户绑定信息
	 *
	 * @param userId
	 *            用户id
	 * @return ThirdAccountInfo
	 */
	@Override
	public ThirdAccountInfo queryByCondition(Long userId) {

		ThirdAccountInfo thirdAccountInfo = null;

		Map<String, Object> parameters = Maps.newHashMap();
		parameters.put("userId", userId);
		parameters.put("channel", "wechatPub");
		List<ThirdAccountInfo> thirdAccountInfoList = ThirdAccountInfoConverter
				.convert2List(thirdAccountInfoDAO.query(parameters));
		if (CollectionUtils.isNotEmpty(thirdAccountInfoList)) {
			thirdAccountInfo = thirdAccountInfoList.get(0);
		}

		return thirdAccountInfo;
	}
}
