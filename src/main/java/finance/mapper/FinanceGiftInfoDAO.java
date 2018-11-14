package finance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import finance.api.model.base.Page;
import finance.model.po.FinanceGiftInfo;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceGiftInfoDAO.java, v0.1 2018/11/14 2:54 PM lili Exp $
 */
public interface FinanceGiftInfoDAO extends BaseDAO<FinanceGiftInfo, Long> {
    /**
      *功能描述:app分页查询礼品信息
      * @author: moruihai
      * @date: 2018/8/29 15:58
      * @param: [financeGiftInfoPage]
      * @return: java.util.List<finance.model.po.FinanceGiftInfo>
      */
    List<FinanceGiftInfo> selectGiftByPage(@Param("page") Page<FinanceGiftInfo> financeGiftInfoPage);

}