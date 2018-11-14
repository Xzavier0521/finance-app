package finance.web.controller.noauth.system;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import finance.api.model.response.ResponseResult;
import finance.core.common.enums.CodeEnum;

/**
 * <p>应用系统接口</p>
 * @author lili
 * @version $Id: SystemApi.java, v0.1 2018/11/14 2:40 PM lili Exp $
 */
@RestController
public class SystemApi {

    /** ios包是否在审核中*/
    @Value("${appconfig.isIosExamining}")
    private Boolean appIsIosExamining;
    @Value("${appconfig.isExamining}")
    private Boolean appIsExamining;
    /** 框架版本 1 只有H5；2 AppTab*/
    @Value("${appconfig.frameVersion}")
    private String  appFrameVersion;

    @Value("${appconfig.examined.macketIds}")
    private String  examinedMacketIds;

    /**
     * <pre>
     * @api {GET} monitor 可用性监控服务
     * @apiName monitor 
     * @apiGroup SYSTEM
     * @apiVersion 0.1.0
     * @apiDescription HTTP code =200 表示应用正常，忽略其他返回值
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
     *   "data":null
     *  }
     * @apiError 0000000 成功
     * @apiError 9999999 网络返回错误
     * @apiError 0000011 参数不合法
     * @apiError 0000010 失败
     * </pre>
     * @return
     * @author hewenbin
     * @version SystemApi.java, v1.0 2018年7月5日 下午3:59:47 hewenbin
     */
    @GetMapping("monitor")
    public ResponseResult<String> monitor() {
        return ResponseResult.success(null);
    }

    /**
     * <pre>
     * @api {GET} systemCurrentTime 获取系统时间毫秒数
     * @apiName getSystemCurrentTime
     * @apiGroup  SYSTEM
     * @apiVersion 0.1.0
     * @apiDescription 获取系统时间毫秒数
     * @apiSuccess {Boolean} succeed 是否成功
     * @apiSuccess {String} errorCode 结果码
     * @apiSuccess {String} errorMessage 消息说明
     * @apiSuccess {Long} data 数据
     * @apiSuccessExample {JSON} Success-Response
     *  HTTP/1.1 200 OK
     *  {
     *   "errorCode":"0000000",
     *   "errorMessage":"成功",
     *   "succeed",true,
     *   "data":1530777299973
     *  }
     * @apiError 0000000 成功
     * @apiError 9999999 网络返回错误
     * </pre>
     * @return
     * @author hewenbin
     * @version MonitorApi.java, v1.0 2018年7月5日 下午3:56:49 hewenbin
     */
    @GetMapping("systemCurrentTime")
    public ResponseResult<Long> getSystemCurrentTime() {
        // 已和运维确认过，阿里的服务器时间是同步的
        return ResponseResult.success(System.currentTimeMillis());
    }

    /**
     * <pre>
     * @api {GET} appConfig 获取APP配置
     * @apiName getAppConfig 
     * @apiGroup SYSTEM
     * @apiVersion 0.1.0
     * @apiDescription 获取 APP 系统配置信息
     * @apiParam {String} appVersion app 版本信息
     * @apiParam {String} terminal 主包 （规则：渠道+系统（iOS 001; Android 002)，比如主包Id为1，系统为iOS，则为1001
     * @apiParam {String} [macketId] 市场ID jrj00100：360，jrj00200 百度，jrj00300 应用宝，jrj00400 阿里，jrj00500 联想，jrj00600 小米 ……
     * @apiSuccess {Boolean} succeed 是否成功
     * @apiSuccess {String} errorCode 结果码
     * @apiSuccess {String} errorMessage 消息说明
     * @apiSuccess {JSON} data 数据
     * @apiSuccess {Boolean} data.isIosExamining  ios包是否在审核中
     * @apiSuccess {Boolean} data.isExamining  android包是否在审核中
     * @apiSuccess {Int} data.frameVersion  框架版本 1 只有H5；2 AppTab
     * @apiSuccessExample {JSON} Success-Response
     *  HTTP/1.1 200 OK
     *  {
     *   "errorCode":"0000000",
     *   "errorMessage":"成功",
     *   "succeed",true,
     *   "data":{
     *   	"isIosExamining":true,
     *   	"frameVersion":1
     *   }
     *  }
     * @apiError 0000000 成功
     * @apiError 9999999 网络返回错误
     * @apiError 0000002 参数不足
     * </pre>
     * @author hewenbin
     * @version SystemApi.java, v1.0 2018年8月27日 下午4:28:51 hewenbin
     */
    @GetMapping("appConfig")
    public ResponseResult<Map<String, Object>> getAppConfig(@RequestParam(value = "appVersion", required = false) String appVersion,
                                                            @RequestParam(value = "terminal", required = false) String terminal,
                                                            @RequestParam(value = "macketId", required = false) String macketId) {
        /**
         * 	 	安卓应用市场平台	
         * 		编号	名称
         * 		jrj00100	 360
         * 		jrj00200 百度
         * 		jrj00300 应用宝
         * 		jrj00400 阿里
         * 		jrj00500	 联想
         * 		jrj00600	 小米
         * 		jrj00700	 魅族
         * 		jrj00800	 华为
         * 		jrj00900	 OPPO
         * 		jrj01000 VIVO
         * 		jrj01100	 搜狗
         * 		jrj01200	 三星
         */
        // TODO appConfig appVersion terminal ，一期写死吗，二期根据规则来增加逻辑配置
        // macketId 安卓市场 ID，需要根据
        Map<String, Object> resMap = new HashMap<>();
        if (StringUtils.isEmpty(appVersion) || StringUtils.isEmpty(terminal)) {
            return ResponseResult.error(CodeEnum.paramLack);
        }
        resMap.put("isIosExamining", appIsIosExamining);
        resMap.put("frameVersion", appFrameVersion);

        if (macketId != null && examinedMacketIds.contains(macketId)) {
            resMap.put("isExamining", false);
        } else {
            resMap.put("isExamining", appIsExamining);
        }
        return ResponseResult.success(resMap);
    }

}
