package cn.zhishush.finance.web.controller.noauth.helpcenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.zhishush.finance.domainservice.service.questionandanswer.QuestionAndAnswerBiz;
import cn.zhishush.finance.core.dal.dataobject.news.QuestionAndAnswerDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.zhishush.finance.api.model.response.ResponseResult;

@RestController
@RequestMapping("question")
public class QuestionAndAnswerApi {
	@Autowired
	private QuestionAndAnswerBiz questionAndAnswerBiz;

	/**
	 * <pre>
	 * &#64;api {GET}/question/queryAllQuestion 展示Q&A
	 * &#64;apiName queryAllQuestion
	 * &#64;apiGroup HELPCENTER
	 * &#64;apiVersion 0.1.0
	 * &#64;apiDescription 手机端帮助中心展示Q&A服务
	 * &#64;apiSuccess {Boolean} succeed 是否成功
	 * &#64;apiSuccess {String} errorCode 结果码
	 * &#64;apiSuccess {String} errorMessage 消息说明
	 * &#64;apiSuccess {Object[]} date Q&A列表
	 * &#64;apiSuccessExample {JSON} Success-Response
	 *  HTTP/1.1 200 OK
	 *  {
	 *   "errorCode":"0000000",
	 *   "errorMessage":"success",
	 *   "succeed",true,
	 *   "data":{
	 *   "questionAndAnswerList": [
	 *         {
	 *             "id": null,
	 *             "title": "一、怎么登陆金榕家平台？都有哪些入口？",
	 *             "content": "<p>1.微信公众号搜索“金榕家平台”公众号，关注点击<strong>首页</strong>进入。</p><p>2.登录网址：http://zc.zhishush.cn</p>",
	 *             "imageUrl": null,
	 *             "reservedField": null,
	 *             "createTime": null,
	 *             "updateTime": null,
	 *             "creater": null,
	 *             "updater": null,
	 *             "versionNum": null,
	 *             "isDelete": null
	 *         },
	 *         {
	 *             "id": null,
	 *             "title": "二、我办理下来了为什么没有返现？",
	 *             "content": "<p>请你点击我的资料板块，完善好自己的资料，姓名+联系方式，我们将进  行核对，核对无误即可返现到你的平台账户上。</p>",
	 *             "imageUrl": null,
	 *             "reservedField": null,
	 *             "createTime": null,
	 *             "updateTime": null,
	 *             "creater": null,
	 *             "updater": null,
	 *             "versionNum": null,
	 *             "isDelete": null
	 *         }
	 *     ]
	 *     }
	 *  }
	 * &#64;apiError 0000000 成功
	 * &#64;apiError 9999999 网络返回错误
	 * &#64;apiError 0000011 参数不合法
	 * &#64;apiError 0000010 失败
	 * </pre>
	 *
	 * @author panzhongkang
	 * @version QuestionAndAnswerApi.java, v1.0 2018年7月6日 下午1:46:11
	 */

	@GetMapping(value = "queryAllQuestion")
	public ResponseResult<Map> getAllQuestion() {
		Map<String, List<QuestionAndAnswerDO>> resultMap = new HashMap<>();

		List<QuestionAndAnswerDO> questionAndAnswerList = questionAndAnswerBiz.queryAll();
		resultMap.put("questionAndAnswerList", questionAndAnswerList);
		return ResponseResult.success(resultMap);
	}

}
