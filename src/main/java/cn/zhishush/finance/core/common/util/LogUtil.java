package cn.zhishush.finance.core.common.util;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * 日志工具类.
 * 
 * @author hewenbin
 * @version $Id: LogUtil.java, v 0.1 2017年8月10日 下午4:21:18 hewenbin Exp $
 */
public class LogUtil {

	/**
	 * <pre>
	 * 示例：
	 * logger.error(LogUtil.getFormatLog(paramMap, "查询xxx异常"), e);
	 * </pre>
	 * 
	 * 获取格式化之后的日志.
	 * 
	 * @param param
	 *            自定义参(必须重写其toString方法)
	 * @param message
	 *            自定义日志内容
	 * @return
	 */
	public static String getFormatLog(Object param, String message) {
		Map<String, Object> map = new HashMap<>();
		map.put("methodName", Thread.currentThread().getStackTrace()[2].getMethodName());
		map.put("paramter", param == null ? "" : param.toString());
		map.put("message", message);
		String logMessage = JSON.toJSONString(map);
		return logMessage;
	}
}