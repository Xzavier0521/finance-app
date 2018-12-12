package cn.zhishush.finance.web.controller.oauth.businessinformation;

import java.util.List;
import java.util.Map;

import cn.zhishush.finance.domainservice.service.businessinformation.AnnouncementInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.zhishush.finance.api.model.response.ResponseResult;
import cn.zhishush.finance.api.model.vo.info.AnnouncementListVO;

/**
 * @program: finance-server
 *
 * @description: 后台-公告
 *
 * @author: MORUIHAI
 *
 * @create: 2018-08-15 09:19
 **/
@RestController
@RequestMapping("announcement")
public class AnnouncementApi {
	@Autowired
	private AnnouncementInfo announcementImpl;

	/**
	 * <pre>
	 * &#64;api {GET} /announcement/announcementList 公告展示
	 * &#64;apiName announcementList
	 * &#64;apiGroup Announcement
	 * &#64;apiVersion 0.1.0
	 * &#64;apiDescription 公告展示
	 * &#64;apiSuccess {Boolean} succeed 是否成功
	 * &#64;apiSuccess {String} errorCode 结果码
	 * &#64;apiSuccess {String} errorMessage 消息说明
	 * &#64;apiSuccess {Object[]} data.list 公告数组
	 * &#64;apiSuccessExample {JSON} Success-Response
	 *  HTTP/1.1 200 OK
	 *  {
	 *      "errorCode": "0000000",
	 *      "errorMessage": "success",
	 *      "data": {
	 *          "announcementList": [
	 *              {
	 *                  "announcementTitle": "金榕家二期上线啦！",
	 *                  "announcementContext": "注册推广有大礼包赠送！",
	 *                  "announcementType": "announcement",
	 *                  "id": 1
	 *              },
	 *              {
	 *                  "announcementTitle": "您推广的客户13412128771已完成注册",
	 *                  "announcementContext": "您推广的客户13412128771已完成注册",
	 *                  "announcementType": "message",
	 *                  "id": 2
	 *              }
	 *          ]
	 *      },
	 *      "succeed": true
	 *  }
	 * &#64;apiError 0000000 成功
	 * &#64;apiError 9999999 网络返回错误
	 * &#64;apiError 0302001 参数不合法
	 * </pre>
	 * 
	 * @author
	 */
	@GetMapping("announcementList")
	public ResponseResult<Map<String, List<AnnouncementListVO>>> getAnnouncementList() {
		Map<String, List<AnnouncementListVO>> returnMap = announcementImpl.getAnnouncementList();
		return ResponseResult.success(returnMap);
	}
}
