package cn.zhishush.finance.core.dal.dao.news;

import java.util.List;

import cn.zhishush.finance.core.dal.dao.base.BaseDAO;
import cn.zhishush.finance.core.dal.dataobject.news.QuestionAndAnswerDO;

/**
 * <p>注释</p>
 * 
 * @author lili
 * @version $Id: QuestionAndAnswerDAO.java, v0.1 2018/11/14 12:57 PM lili Exp $
 */
public interface QuestionAndAnswerDAO extends BaseDAO<QuestionAndAnswerDO, Long> {

    List<QuestionAndAnswerDO> queryAll();
}