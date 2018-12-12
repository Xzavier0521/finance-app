package cn.zhishush.finance.domainservice.service.questionandanswer;

import java.util.List;

import cn.zhishush.finance.core.dal.dataobject.news.QuestionAndAnswerDO;

public interface QuestionAndAnswerBiz {

	List<QuestionAndAnswerDO> queryAll();
}
