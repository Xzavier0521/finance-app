package cn.zhishush.finance.domainservice.service.questionandanswer.impl;

import cn.zhishush.finance.core.dal.dao.news.QuestionAndAnswerDAO;
import cn.zhishush.finance.domainservice.service.questionandanswer.QuestionAndAnswerBiz;
import cn.zhishush.finance.core.dal.dataobject.news.QuestionAndAnswerDO;

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
