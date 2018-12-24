package cn.zhishush.finance.core.dal.dao.flash;

import cn.zhishush.finance.core.dal.dataobject.flash.InformationConfigDO;
import cn.zhishush.finance.domain.flash.InformationConfig;

import java.util.List;
import java.util.Map;

public interface InformationConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InformationConfigDO record);

    int insertSelective(InformationConfigDO record);

    List<InformationConfigDO> query(Map parameters);

    int count(Map parameters);

    InformationConfigDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InformationConfigDO record);

    int updateByPrimaryKey(InformationConfigDO record);
}