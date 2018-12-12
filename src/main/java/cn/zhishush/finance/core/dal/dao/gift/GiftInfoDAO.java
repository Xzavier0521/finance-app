package cn.zhishush.finance.core.dal.dao.gift;

import java.util.List;

import cn.zhishush.finance.core.dal.dao.base.BaseDAO;
import org.apache.ibatis.annotations.Param;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.core.dal.dataobject.gift.GiftInfoDO;

/**
 * <p>注释</p>
 * 
 * @author lili
 * @version $Id: GiftInfoDAO.java, v0.1 2018/11/14 2:54 PM lili Exp $
 */
public interface GiftInfoDAO extends BaseDAO<GiftInfoDO, Long> {

    /**
     * app分页查询礼品信息
     * @param financeGiftInfoPage
     * @return
     */
    List<GiftInfoDO> selectGiftByPage(@Param("page") Page<GiftInfoDO> financeGiftInfoPage);

}