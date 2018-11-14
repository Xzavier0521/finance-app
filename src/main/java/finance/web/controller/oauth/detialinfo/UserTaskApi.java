package finance.web.controller.oauth.detialinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import finance.api.model.base.XMap;
import finance.api.model.response.ResponseResult;
import finance.api.model.vo.EverydayClockVO;
import finance.api.model.vo.GrowTaskVO;
import finance.api.model.vo.NewRegisterListVO;
import finance.core.common.enums.CodeEnum;
import finance.core.common.enums.GameTaskType;
import finance.domainservice.service.userinfo.UserTaskBiz;

/**
 * 个人任务相关服务.
 * @author hewenbin
 * @version v1.0 2018年8月15日 下午1:44:51 hewenbin
 */
@RequestMapping("userTask")
@RestController
public class UserTaskApi {
    @Resource
    private UserTaskBiz userTaskBizImpl;

    /**
     * <pre>
     * @api {GET} userTask/signInfo 查询个人签到任务详情
     * @apiName querySignTaskInfo 
     * @apiGroup  USERTASK
     * @apiVersion 0.1.0
     * @apiDescription 查询个人签到任务详情
     * @apiSuccess {Boolean} succeed 是否成功
     * @apiSuccess {String} errorCode 结果码
     * @apiSuccess {String} errorMessage 消息说明
     * @apiSuccess {JSON} data 数据
     * @apiSuccess {Int} data.coinNum 单次奖励金币数
     * @apiSuccess {Int} data.totalCoinNum 累计奖励金币数(签到)
     * @apiSuccess {Int} data.currentCanUseTotalCoin 当前可用金额
     * @apiSuccess {Int} data.sigNum 连续签到数
     * @apiSuccess {Array} data.signList 签到列表
     * @apiSuccess {Array} data.signList.date 签到日期
     * @apiSuccess {Array} data.signList.status 签到状态，是否已签到 0：否、1：是
     * @apiSuccessExample {JSON} Success-Response
     *  HTTP/1.1 200 OK
     *  {
     *   "errorCode":"0000000",
     *   "errorMessage":"成功",
     *   "succeed",true,
     *   "data":{
     *   	"coinNum":10,
     *   	"totalCoinNum":100,
     *   	"sigNum":3,
     *      "currentCanUseTotalCoin":1000,
     *   	"signList":[
     *   		{"date":"1","status":0},
     *   		{"date":"2","status":1},
     *   		{"date":"3","status":1}
     *   	]
     *   }
     *  }
     * @apiError 0000000 成功
     * @apiError 9999999 网络返回错误
     * </pre>
     * @author hewenbin
     * @version UserTaskApi.java, v1.0 2018年8月15日 下午2:12:18 hewenbin
     */
    @GetMapping("signInfo")
    public ResponseResult<EverydayClockVO> querySignTaskInfo() {
        EverydayClockVO everydayClockVO = userTaskBizImpl.findEveryDayClockInfo();
        return ResponseResult.success(everydayClockVO);

    }

    /**
     * <pre>
     * @api {GET} userTask/new 查询个人新手任务列表
     * @apiName queryNewTaskInfo 
     * @apiGroup  USERTASK
     * @apiVersion 0.1.0
     * @apiDescription 查询个人新手任务列表
     * @apiSuccess {Boolean} succeed 是否成功
     * @apiSuccess {String} errorCode 结果码
     * @apiSuccess {String} errorMessage 消息说明
     * @apiSuccess {JSON} data 数据
     * @apiSuccess {Array} data.list 任务列表
     * @apiSuccess {String} list.taskId 任务ID
     * @apiSuccess {String} list.pic 任务图标地址链接
     * @apiSuccess {String} list.title 任务标题
     * @apiSuccess {Int} list.coinNum 奖励金币数
     * @apiSuccess {Int} list.status  完成状态，0：未完成，1：完成、待领取金币，2：已领取金币
     * @apiSuccessExample {JSON} Success-Response
     *  HTTP/1.1 200 OK
     *  { 
     *      "errorCode": "0000000",
     *      "errorMessage": "success",
     *      "data": {
     *          "list": [
     *              {
     *                  "coinNum": 10,
     *                  "pic": "xxxx",
     *                  "title": "完善资料",
     *                  "taskId": 1,
     *                  "status": 0
     *              },
     *              {
     *                  "coinNum": 10,
     *                  "pic": "xxxx",
     *                  "title": "推广一次",
     *                  "taskId": 1,
     *                  "status": 0
     *              }
     *          ]
     *      },
     *      "succeed": true
     *  }
     * @apiError 0000000 成功
     * @apiError 9999999 网络返回错误
     * </pre>
     * @author hewenbin
     * @version UserTaskApi.java, v1.0 2018年8月15日 下午2:12:18 hewenbin
     */
    @GetMapping("new")
    public ResponseResult<Map<String, List<NewRegisterListVO>>> queryNewTaskInfo() {
        Map<String, List<NewRegisterListVO>> resMap = new HashMap<>();
        List<NewRegisterListVO> newRegisterListVOList = userTaskBizImpl.queryNewTaskInfo();
        resMap.put("list", newRegisterListVOList);
        return ResponseResult.success(resMap);
    }

    /**
     * <pre>
     * @api {GET} userTask/grow 查询个人成长任务
     * @apiName queryGrowTaskInfo 
     * @apiGroup  USERTASK
     * @apiVersion 0.1.0
     * @apiDescription 查询个人成长任务，在用户点击领取之后，页面需要刷新重新下载下一阶段的数据
     * @apiSuccess {Boolean} succeed 是否成功
     * @apiSuccess {String} errorCode 结果码
     * @apiSuccess {String} errorMessage 消息说明
     * @apiSuccess {JSON} data 数据
     * @apiSuccess {Array} data.taskList 任务列表
     * @apiSuccess {Long} taskList.taskId 任务ID
     * @apiSuccess {String} taskList.pic 任务图标地址链接
     * @apiSuccess {String} taskList.title 任务标题
     * @apiSuccess {Int} taskList.coinNum 奖励金币数
     * @apiSuccess {Int} taskList.currentNum 当前完成数
     * @apiSuccess {Int} taskList.nextNum 到达下一阶段需要的完成数
     * @apiSuccess {Int} taskList.status  完成状态，0：未完成，1：完成、待领取金币
     * @apiSuccessExample {JSON} Success-Response
     *  HTTP/1.1 200 OK
     *  {
     *   "errorCode":"0000000",
     *   "errorMessage":"成功",
     *   "succeed",true,
     *   "data":
     *   	{
     *   		"taskId":1,
     *   		"pic":"http://xxxxx",
     *   		"title":"完善资料",
     *   		"coinNum":20,
     *   		"currentNum":10,
     *   		"nextNum":20,
     *   		"status":0
     *   	}
     *   }
     *  }
     * @apiError 0000000 成功
     * @apiError 9999999 网络返回错误
     * </pre>
     * @author hewenbin
     * @version UserTaskApi.java, v1.0 2018年8月15日 下午2:12:18 hewenbin
     */
    @GetMapping("grow")
    public ResponseResult<GrowTaskVO> queryGrowTaskInfo() {
        // 每完成一个阶段的邀请数就有一条记录，然后返回最先完成的任务，一直到他领取完成为之；考虑是否需要单独建表
        GrowTaskVO growTaskVO = userTaskBizImpl.queryGrowTaskInfo();
        return ResponseResult.success(growTaskVO);
    }

    /**
     * <pre>
     * @api {POST} userTask/finish 完成任务（领取奖励）
     * @apiName finishTask 
     * @apiGroup USERTASK
     * @apiVersion 0.1.0
     * @apiDescription 在任务状态为完成的情况下 ，领取金币
     * @apiParam {String=1,2,3} type 任务类型，1：每日签到，2：新手任务，3：成长任务
     * @apiParam {String{1-}} [taskId] 任务ID，type=2,3时必填
     * @apiParamExample {JSON} Request-example
     * {
     * 	"type":"1",
     * 	"taskId":"1"
     * }
     * @apiSuccess {Boolean} succeed 是否成功
     * @apiSuccess {String} errorCode 结果码
     * @apiSuccess {String} errorMessage 消息说明
     * @apiSuccess {JSON} data 数据
     * @apiSuccessExample {JSON} Success-Response
     *  HTTP/1.1 200 OK
     *  {
     *   "errorCode":"0000000",
     *   "errorMessage":"成功",
     *   "succeed",true,
     *   "data":true
     *  }
     * @apiError 0000000 成功
     * @apiError 9999999 网络返回错误
     * @apiError 0000011 参数不合法
     * @apiError 0000010 失败
     * @apiError code [description]
     * </pre>
     * @return
     * @author hewenbin
     * @version UserTaskApi.java, v1.0 2018年8月15日 下午3:31:18 hewenbin
     */
    @PostMapping("finish")
    public ResponseResult<Boolean> finishTask(@RequestBody XMap xmap) {
        String type = String.valueOf(xmap.get("type"));
        if (!(type.equals(GameTaskType.everydaySign.getCode())
              || type.equals(GameTaskType.newRegisterTask.getCode())
              || type.equals(GameTaskType.groupTask.getCode()))) {
            return ResponseResult.error(CodeEnum.gameParamInvalid);
        }
        return userTaskBizImpl.finishTask(xmap);
    }

}
