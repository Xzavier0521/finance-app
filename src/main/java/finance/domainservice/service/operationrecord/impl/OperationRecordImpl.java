package finance.domainservice.service.operationrecord.impl;

import java.util.Objects;

import javax.annotation.Resource;

import finance.core.dal.dao.OperationRecordDAO;
import finance.core.dal.dao.ProductMainDAO;
import finance.core.dal.dao.UserBankCardInfoDAO;
import finance.core.dal.dataobject.OperationRecordDO;
import finance.core.dal.dataobject.UserBankCardInfoDO;
import org.springframework.stereotype.Service;

import finance.domainservice.service.operationrecord.OperationRecordBiz;
import finance.domain.log.OperationRecordSave;
import finance.core.dal.dataobject.ProductMain;
import finance.core.dal.dataobject.UserInfoDO;
import finance.domainservice.service.jwt.JwtService;

/**
 * <p>
 * 用户操作记录
 * </p>
 *
 * @author lili
 * @version $Id: OperationRecordImpl.java, v0.1 2018/11/6 1:25 PM lili Exp $
 */
@Service
public class OperationRecordImpl implements OperationRecordBiz {

	@Resource
	private OperationRecordDAO operationRecordMapper;
	@Resource
	private JwtService jwtService;
	@Resource
	private UserBankCardInfoDAO cardInfoMapper;
	@Resource
	private ProductMainDAO productMainMapper;

	@Override
	public void saveOperationRecord(OperationRecordSave operationRecordSave) {
		OperationRecordDO operationRecord = new OperationRecordDO();
		UserInfoDO userInfo = jwtService.getUserInfo();
		operationRecord.setUserId(userInfo.getId());
		operationRecord.setMobileNum(userInfo.getMobileNum());
		// 银行卡表获取姓名
		UserBankCardInfoDO userBankCardInfo = cardInfoMapper.selectDefaultBankCard(userInfo.getId());
		if (userBankCardInfo != null) {
			operationRecord.setRealName(userBankCardInfo.getAccountName());
		}
		ProductMain productMain;
		// 产品表获取产品名称
		if (Objects.nonNull(operationRecordSave.getProductId())) {
			productMain = productMainMapper.selectByPrimaryKey(operationRecordSave.getProductId());
		} else {
			productMain = new ProductMain();
			productMain.setId(0L);
			productMain.setProductName(operationRecordSave.getProductName());
			productMain.setType(0);
			operationRecordSave.setProductId(0L);
		}
		operationRecord.setProductId(operationRecordSave.getProductId());
		if (productMain != null) {
			operationRecord.setProductName(productMain.getProductName());
			operationRecord.setProductType(productMain.getType());
		}
		operationRecordMapper.insertSelective(operationRecord);
	}
}
