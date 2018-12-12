package cn.zhishush.finance.core.common.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.MapUtils;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: EntityUtils.java, v0.1 2018/10/29 1:29 PM lili Exp $
 */
public class EntityUtils {

	@SuppressWarnings("unchecked")
	private static void hashToObject(Map<?, ?> map, Object obj) {
		try {
			BeanUtils.populate(obj, (Map) map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T hashToObject(Map<?, ?> map, Class c) {
		if (MapUtils.isEmpty(map)) {
			return null;
		}
		try {
			Object obj = c.newInstance();
			hashToObject(map, obj);
			return (T) obj;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
