package finance.domainservice.service.questionandanswer.impl;

import finance.domainservice.service.questionandanswer.QuestionAndAnswerBiz;
import finance.core.dal.dao.FinanceQuestionAndAnswerDAO;
import finance.core.dal.dataobject.FinanceQuestionAndAnswer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionAndAnswerImpl implements QuestionAndAnswerBiz {
    @Autowired
    private FinanceQuestionAndAnswerDAO questionAndAnswerMapper;

    @Override
    public List<FinanceQuestionAndAnswer> queryAll() {
        return questionAndAnswerMapper.queryAll();
    }
}
