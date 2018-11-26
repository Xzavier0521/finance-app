package finance.core.dal.dao;

import java.util.List;

import finance.core.dal.dataobject.QuestionAndAnswerDO;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: QuestionAndAnswerDAO.java, v0.1 2018/11/14 12:57 PM lili Exp $
 */
public interface QuestionAndAnswerDAO extends BaseDAO<QuestionAndAnswerDO, Long> {

	List<QuestionAndAnswerDO> queryAll();
}