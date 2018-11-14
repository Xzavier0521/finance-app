package finance.domainservice.service.questionandanswer;

import java.util.List;

import finance.model.po.FinanceQuestionAndAnswer;

public interface QuestionAndAnswerBiz {

    List<FinanceQuestionAndAnswer> queryAll();
}
