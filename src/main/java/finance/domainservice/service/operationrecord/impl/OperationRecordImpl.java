package finance.domainservice.service.operationrecord.impl;

import java.util.Objects;

import javax.annotation.Resource;

import finance.mapper.FinanceOperationRecordDAO;
import finance.mapper.FinanceProductMainDAO;
import org.springframework.stereotype.Service;

import finance.domainservice.service.operationrecord.OperationRecordBiz;
import finance.domain.OperationRecordSave;
import finance.mapper.FinanceUserBankCardInfoDAO;
import finance.model.po.FinanceOperationRecord;
import finance.model.po.FinanceProductMain;
import finance.model.po.FinanceUserBankCardInfo;
import finance.model.po.FinanceUserInfo;
import finance.domainservice.service.jwt.JwtService;

/**
 * <p>用户操作记录</p>
 *
 * @author lili
 * @version $Id: OperationRecordImpl.java, v0.1 2018/11/6 1:25 PM lili Exp $
 */
@Service
public class OperationRecordImpl implements OperationRecordBiz {

    @Resource
    private FinanceOperationRecordDAO operationRecordMapper;
    @Resource
    private JwtService                    jwtService;
    @Resource
    private FinanceUserBankCardInfoDAO cardInfoMapper;
    @Resource
    private FinanceProductMainDAO productMainMapper;

    @Override
    public void saveOperationRecord(OperationRecordSave operationRecordSave) {
        FinanceOperationRecord operationRecord = new FinanceOperationRecord();
        FinanceUserInfo userInfo = jwtService.getUserInfo();
        operationRecord.setUserId(userInfo.getId());
        operationRecord.setMobileNum(userInfo.getMobileNum());
        //银行卡表获取姓名
        FinanceUserBankCardInfo userBankCardInfo = cardInfoMapper
            .selectDefaultBankCard(userInfo.getId());
        if (userBankCardInfo != null) {
            operationRecord.setRealName(userBankCardInfo.getAccountName());
        }
        FinanceProductMain productMain;
        //产品表获取产品名称
        if (Objects.nonNull(operationRecordSave.getProductId())) {
            productMain = productMainMapper.selectByPrimaryKey(operationRecordSave.getProductId());
        } else {
            productMain = new FinanceProductMain();
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
