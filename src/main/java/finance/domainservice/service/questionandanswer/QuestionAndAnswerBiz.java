package finance.domainservice.service.questionandanswer;

import java.util.List;

import finance.core.dal.dataobject.FinanceQuestionAndAnswer;

public interface QuestionAndAnswerBiz {

    List<FinanceQuestionAndAnswer> queryAll();
}
