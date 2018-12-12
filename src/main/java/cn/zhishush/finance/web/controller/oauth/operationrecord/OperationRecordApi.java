package cn.zhishush.finance.web.controller.oauth.operationrecord;

import java.util.Objects;

import javax.annotation.Resource;

import cn.zhishush.finance.core.common.enums.CodeEnum;
import cn.zhishush.finance.core.common.util.ConvertBeanUtil;
import cn.zhishush.finance.domain.log.OperationRecordSave;
import cn.zhishush.finance.domainservice.service.operationrecord.OperationRecordBiz;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.zhishush.finance.api.model.request.OperationRecordSaveRequest;
import cn.zhishush.finance.api.model.response.ResponseResult;

/**
 * <p>用户操作记录</p>
 * 
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
	 * &#64;api {POST} /operation/saveRecord 保存操作记录
	 * &#64;apiName saveRecord
	 * &#64;apiGroup OPERATIONRECORD
	 * &#64;apiVersion 0.1.0
	 * &#64;apiDescription 保存用户操作记录
	 * &#64;apiParam {Number} productId 产品Id
	 * &#64;apiParamExample {JSON} Request-example
	 * {
	 *  "productId":12131
	 * }
	 * &#64;apiSuccess {Boolean} succeed 是否成功
	 * &#64;apiSuccess {String} errorCode 结果码
	 * &#64;apiSuccess {String} errorMessage 消息说明
	 * &#64;apiSuccess {JSON} data 数据
	 * &#64;apiSuccessExample {JSON} Success-Response
	 *  HTTP/1.1 200 OK
	 *  {
	 *   "errorCode":"0000000",
	 *   "errorMessage":"成功",
	 *   "succeed",true,
	 *   "data":""
	 *  }
	 * &#64;apiError 0000000 成功
	 * &#64;apiError 9999999 网络返回错误
	 * &#64;apiError 0401001 参数不合法
	 * &#64;apiError 0000010 失败
	 * &#64;apiError code [description]
	 * </pre>
	 */
	@PostMapping(value = "saveRecord")
	public ResponseResult<String> saveRecord(@RequestBody OperationRecordSaveRequest request) {

		if (Objects.isNull(request.getProductId()) && StringUtils.isBlank(request.getProductName())) {
			return ResponseResult.error(CodeEnum.operateValidateInvalid);
		}
		OperationRecordSave operationRecordSave = new OperationRecordSave();
		ConvertBeanUtil.copyBeanProperties(request, operationRecordSave);
		operationRecordBiz.saveOperationRecord(operationRecordSave);
		return ResponseResult.success("");
	}
}
