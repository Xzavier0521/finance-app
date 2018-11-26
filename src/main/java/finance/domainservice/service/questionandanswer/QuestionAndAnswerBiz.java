package finance.domainservice.service.questionandanswer;

import java.util.List;

import finance.core.dal.dataobject.QuestionAndAnswerDO;

public interface QuestionAndAnswerBiz {

	List<QuestionAndAnswerDO> queryAll();
}
