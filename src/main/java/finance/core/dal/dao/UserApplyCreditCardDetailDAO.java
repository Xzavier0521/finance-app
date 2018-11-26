package finance.core.dal.dao;

import finance.core.dal.dataobject.UserApplyCreditCardDetailDO;
import java.util.List;
import java.util.Map;

public interface UserApplyCreditCardDetailDAO {
	int deleteByPrimaryKey(Long id);

	int insert(UserApplyCreditCardDetailDO record);

	int insertSelective(UserApplyCreditCardDetailDO record);

	List<UserApplyCreditCardDetailDO> query(Map parameters);

	int count(Map parameters);

	UserApplyCreditCardDetailDO selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(UserApplyCreditCardDetailDO record);

	int updateByPrimaryKey(UserApplyCreditCardDetailDO record);
}