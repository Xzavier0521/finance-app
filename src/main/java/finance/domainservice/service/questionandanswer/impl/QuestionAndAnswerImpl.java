package finance.domainservice.service.questionandanswer.impl;

import finance.core.dal.dao.QuestionAndAnswerDAO;
import finance.domainservice.service.questionandanswer.QuestionAndAnswerBiz;
import finance.core.dal.dataobject.QuestionAndAnswerDO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionAndAnswerImpl implements QuestionAndAnswerBiz {
	@Autowired
	private QuestionAndAnswerDAO questionAndAnswerMapper;

	@Override
	public List<QuestionAndAnswerDO> queryAll() {
		return questionAndAnswerMapper.queryAll();
	}
}
