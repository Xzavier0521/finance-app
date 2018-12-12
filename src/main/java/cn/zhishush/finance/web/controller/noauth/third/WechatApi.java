package cn.zhishush.finance.web.controller.noauth.third;

import java.text.MessageFormat;
import java.util.Map;

import javax.annotation.Resource;

import cn.zhishush.finance.core.common.constants.Constant;
import cn.zhishush.finance.core.common.enums.CodeEnum;
import cn.zhishush.finance.domainservice.service.userinfo.ThirdBindBiz;
import cn.zhishush.finance.domainservice.service.userinfo.UserInfoBiz;
import cn.zhishush.finance.domainservice.service.wechat.WechatBiz;
import lombok.extern.slf4j.Slf4j;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;

import cn.zhishush.finance.api.model.response.ResponseResult;

/**
 * 微信API
 * 
 * @author panzhongkang
 * @date 2018/9/13 17:15
 */
@Slf4j
@RestController
@RequestMapping("wechat")
public class WechatApi {
	@Resource
	private WechatBiz wechatBiz;
	@Resource
	private ThirdBindBiz thirdBindBiz;

	@Resource
	private UserInfoBiz userInfoBiz;

	/**
	 * <pre>
	 * &#64;api {GET}/wechat/signature 微信公众号获取签名
	 * &#64;apiName signature
	 * &#64;apiGroup WECHAT
	 * &#64;apiVersion 0.1.0
	 * &#64;apiDescription 微信公众号获取签名服务
	 * &#64;apiParam {String} url 分享路径
	 * &#64;apiSuccess {Boolean} succeed 是否成功
	 * &#64;apiSuccess {String} errorCode 结果码
	 * &#64;apiSuccess {String} errorMessage 消息说明
	 * &#64;apiSuccess {Object} date 返回数据
	 * &#64;apiSuccessExample {JSON} Success-Response
	 *  HTTP/1.1 200 OK
	 *  {
	 *     "errorCode": "0000000",
	 *     "errorMessage": "success",
	 *     "data": {
	 *         "signature": "31b303e5a75fa7e9fa370967f0bfafd927b8ce73",
	 *         "nonceStr": "7c793ea61bea4450",
	 *         "timestamp": "1536833601"
	 *     },
	 *     "succeed": true
	 * }
	 * &#64;apiError 0000000 成功
	 * &#64;apiError 9999999 网络返回错误
	 * </pre>
	 *
	 * @author panzhongkang
	 * @version QuestionAndAnswerApi.java, v1.0 2018年9月13日 下午4:46:11
	 */
	@GetMapping("signature")
	public ResponseResult<Map<String, String>> getSignature(@RequestParam(name = "url") String url) {
		Map<String, String> resMap = wechatBiz.getSignature(url);
		return ResponseResult.success(resMap);
	}

	/**
	 * <pre>
	 * &#64;api {GET} wechat/openid 获取openid
	 * &#64;apiName queryOpenId
	 * &#64;apiGroup LOGIN
	 * &#64;apiVersion 0.1.0
	 * &#64;apiDescription 获取openid
	 * &#64;apiParam {String="wechatPub","wechat","qq"} channel 渠道
	 * &#64;apiParam {String{1..100}} [code] 授权临时票据，channel=wechat,wechatPub 时必填
	 * &#64;apiParam {String{1..100}} [openId]  第三方账户唯一标示，channel=qq时必填
	 * &#64;apiParamExample {JSON} Request-example
	 * {
	 * 	"channel":"wechatPub",
	 * 	"code":"abcdef",
	 * 	"openId":"abcdef"
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
	 *      "data": {
	 *          "openId": "123456"
	 *          }
	 *  }
	 * &#64;apiError 0000000 成功
	 * &#64;apiError 9999999 网络返回错误
	 * &#64;apiError 0000011 参数不合法
	 * </pre>
	 * 
	 * @author moruihai
	 * @version ThirdBindApi.java
	 */
	@GetMapping("openid")
	public ResponseResult<Map<String, String>> queryOpenId(@RequestParam("channel") String channel,
			@RequestParam("code") String code, @RequestParam(name = "openId", required = false) String openId) {

		if (StringUtils.isEmpty(channel)
				|| (!Constant.THIRD_PARD_WECHATPUB.equals(channel) && !Constant.THIRD_PARD_WECHAT.equals(channel)
						&& !Constant.THIRD_PARD_QQ.equals(channel))
				|| ((Constant.THIRD_PARD_WECHAT.equals(channel) || Constant.THIRD_PARD_WECHATPUB.equals(channel))
						&& StringUtils.isEmpty(code))
				|| (Constant.THIRD_PARD_QQ.equals(channel) && StringUtils.isEmpty(openId))) {
			return ResponseResult.error(CodeEnum.paramInvalid);
		}
		return thirdBindBiz.queryOpenId(channel, code, openId);
	}

	@GetMapping("weChatPubInfo")
	public ResponseResult<Map<String, Boolean>> query(@RequestParam("openId") String openId) {
		log.info("[开始查询用户是否关注微信公众号],open_id:{}", openId);
		ResponseResult<Map<String, Boolean>> response;
		try {
			Boolean isFlowWeChatPub = userInfoBiz.isFlowWeChatPub(openId)
					|| userInfoBiz.isFlowWeChatPub(MessageFormat.format("\"{0}\"", openId));
			Map<String, Boolean> result = Maps.newHashMap();
			result.put("isFlowWeChatPub", isFlowWeChatPub);
			response = ResponseResult.success(result);
		} catch (final Exception e) {
			response = ResponseResult.error(CodeEnum.systemError);
		}
		log.info("[结束查询用户是否关注微信公众号],open_id:{},response:{}", openId, response);
		return response;
	}
}
