package finance.mapper;

import java.util.List;

import finance.model.po.FinanceQuestionAndAnswer;
/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceQuestionAndAnswerDAO.java, v0.1 2018/11/14 12:57 PM lili Exp $
 */
public interface FinanceQuestionAndAnswerDAO extends BaseDAO<FinanceQuestionAndAnswer, Long> {

    List<FinanceQuestionAndAnswer> queryAll();
}