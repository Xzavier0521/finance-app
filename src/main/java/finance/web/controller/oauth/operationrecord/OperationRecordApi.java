package finance.web.controller.oauth.operationrecord;

import java.util.Objects;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import finance.api.model.request.OperationRecordSaveRequest;
import finance.api.model.response.ResponseResult;
import finance.core.common.enums.CodeEnum;
import finance.core.common.util.ConvertBeanUtil;
import finance.domain.OperationRecordSave;
import finance.domainservice.service.operationrecord.OperationRecordBiz;

/**
 * <p>用户操作记录</p>
 * @author lili
 * @version $Id: OperationRecordApi.java, v0.1 2018/11/6 1:25 PM lili Exp $
 */
@RestController
@RequestMapping("operation")
public class OperationRecordApi {

    @Resource
    private OperationRecordBiz operationRecordBiz;

    /**
     * <pre>
     * @api {POST} /operation/saveRecord 保存操作记录
     * @apiName saveRecord
     * @apiGroup OPERATIONRECORD
     * @apiVersion 0.1.0
     * @apiDescription 保存用户操作记录
     * @apiParam {Number} productId 产品Id
     * @apiParamExample {JSON} Request-example
     * {
     *  "productId":12131
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
     *   "data":""
     *  }
     * @apiError 0000000 成功
     * @apiError 9999999 网络返回错误
     * @apiError 0401001 参数不合法
     * @apiError 0000010 失败
     * @apiError code [description]
     * </pre>
     */
    @PostMapping(value = "saveRecord")
    public ResponseResult<String> saveRecord(@RequestBody OperationRecordSaveRequest request) {

        if (Objects.isNull(request.getProductId())
            && StringUtils.isBlank(request.getProductName())) {
            return ResponseResult.error(CodeEnum.operateValidateInvalid);
        }
        OperationRecordSave operationRecordSave = new OperationRecordSave();
        ConvertBeanUtil.copyBeanProperties(request, operationRecordSave);
        operationRecordBiz.saveOperationRecord(operationRecordSave);
        return ResponseResult.success("");
    }
}
