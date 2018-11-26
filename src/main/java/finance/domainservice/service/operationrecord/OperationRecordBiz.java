package finance.domainservice.service.operationrecord;

import finance.domain.log.OperationRecordSave;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: OperationRecordBiz.java, v0.1 2018/11/6 1:38 PM lili Exp $
 */
public interface OperationRecordBiz {

	/**
	 * 保存用户操作记录
	 * 
	 * @param operationRecordSave
	 *            用户操作记录
	 * @return int
	 */
	void saveOperationRecord(OperationRecordSave operationRecordSave);
}
