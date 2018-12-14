
package cn.zhishush.finance.core.common.util;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.cglib.beans.BeanCopier;
/**
 * <p>注释</p>
 * 
 * @author lili
 * @version :1.0 ConvertBeanUtil.java.java, v 0.1 2018/9/28 上午10:31 lili Exp $
 */
public class ConvertBeanUtil {

	private static ConcurrentHashMap<String, BeanCopier> cache = new ConcurrentHashMap<String, BeanCopier>();

	public static <T> T convert(Object source, Class<T> target) {
		if (source == null) {
			return null;
		}
		T t = null;
		try {
			t = target.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("对象转换异常");
		}
		String key = source.getClass().getSimpleName() + target.getSimpleName();
		BeanCopier copier = cache.get(key);
		if (copier == null) {
			copier = createBeanCopier(source.getClass(), target, false, key);
		}
		copier.copy(source, t, null);
		return t;
	}

	public static <T> T copyBeanProperties(Class source, Class<T> target, Object sourceObj, boolean useConverter) {
		if (sourceObj == null) {
			return null;
		}
		T t;
		try {
			t = target.newInstance();
		} catch (Exception e) {
			return null;
		}
		String key = source.getSimpleName() + target.getSimpleName();
		BeanCopier copier = cache.get(key);
		if (copier == null) {
			copier = createBeanCopier(source, target, useConverter, key);
		}
		copier.copy(sourceObj, t, null);
		return t;
	}

	public static <T> T copyBeanProperties(Object sourceObj, T target) {
		return copyBeanProperties(sourceObj, target, false);
	}

	public static <T> T copyBeanProperties(Object sourceObj, T target, boolean useConverter) {
		if (sourceObj == null || target == null) {
			return null;
		}
		String key = sourceObj.getClass().getSimpleName() + target.getClass().getSimpleName();
		BeanCopier copier = cache.get(key);
		if (copier == null) {
			copier = createBeanCopier(sourceObj.getClass(), target.getClass(), useConverter, key);
		}
		copier.copy(sourceObj, target, null);
		return target;
	}

	public static <T> List<T> copyListBeanPropertiesToList(List<?> sourceObjs, List<T> targets, Class<T> targetType) {
		if (sourceObjs == null || targets == null || targetType == null) {
			return null;
		}
		T t;
		for (Object o : sourceObjs) {
			try {
				t = targetType.newInstance();
				targets.add(copyBeanProperties(o, t, false));
			} catch (InstantiationException | IllegalAccessException e) {
			}
		}
		return targets;
	}

	private static BeanCopier createBeanCopier(Class sourceClass, Class targetClass, boolean useConverter,
			String cacheKey) {
		BeanCopier copier = BeanCopier.create(sourceClass, targetClass, useConverter);
		cache.putIfAbsent(cacheKey, copier);
		return copier;
	}
}
