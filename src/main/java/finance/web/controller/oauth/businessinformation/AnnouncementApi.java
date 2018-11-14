package finance.web.controller.oauth.businessinformation;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import finance.api.model.response.ResponseResult;
import finance.api.model.vo.AnnouncementListVO;
import finance.domainservice.service.businessinformation.AnnouncementInfo;

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
     * @api {GET} /announcement/announcementList 公告展示
     * @apiName announcementList
     * @apiGroup Announcement
     * @apiVersion 0.1.0
     * @apiDescription 公告展示
     * @apiSuccess {Boolean} succeed 是否成功
     * @apiSuccess {String} errorCode 结果码
     * @apiSuccess {String} errorMessage 消息说明
     * @apiSuccess {Object[]} data.list 公告数组
     * @apiSuccessExample {JSON} Success-Response
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
     * @apiError 0000000 成功
     * @apiError 9999999 网络返回错误
     * @apiError 0302001 参数不合法
     * </pre>
     * @author
     */
    @GetMapping("announcementList")
    public ResponseResult<Map<String, List<AnnouncementListVO>>> getAnnouncementList() {
        Map<String, List<AnnouncementListVO>> returnMap = announcementImpl.getAnnouncementList();
        return ResponseResult.success(returnMap);
    }
}
