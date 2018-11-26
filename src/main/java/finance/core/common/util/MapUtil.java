package finance.core.common.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Map工具类
 * </p >
 *
 * @author liwei
 * @version $Id: MapUtil.java, v0.1 2018/11/21 下午2:00 PM user Exp $
 */
public class MapUtil {
	/**
	 * 从map集合中获取属性值
	 * 
	 * @param map
	 * @param key
	 * @param defaultValue
	 * @param <E>
	 * @return
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public final static <E> E get(Map map, Object key, E defaultValue) {
		Object o = map.get(key);
		if (o == null) {
			return defaultValue;
		}
		return (E) o;
	}

	/**
	 * Map集合对象转化成 JavaBean集合对象
	 * 
	 * @param javaBean
	 * @param mapList
	 * @param <T>
	 * @return
	 */
	@SuppressWarnings({"rawtypes"})
	public static <T> List<T> map2Java(T javaBean, List<Map> mapList) {
		if (mapList == null || mapList.isEmpty()) {
			return null;
		}
		List<T> objectList = new ArrayList<T>();
		T object = null;
		for (Map map : mapList) {
			if (map != null) {
				object = map2Java(javaBean, map);
				objectList.add(object);
			}
		}
		return objectList;

	}

	/**
	 * Map对象转化成 JavaBean对象
	 * 
	 * @param javaBean
	 * @param map
	 * @param <T>
	 * @return
	 */
	@SuppressWarnings({"rawtypes", "unchecked", "hiding"})
	public static <T> T map2Java(T javaBean, Map map) {
		if (map == null || javaBean == null) {
			return null;
		}
		try {
			// 获取javaBean属性
			BeanInfo beanInfo = Introspector.getBeanInfo(javaBean.getClass());
			// 创建 JavaBean 对象
			Object obj = javaBean.getClass().newInstance();
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			if (propertyDescriptors != null && propertyDescriptors.length > 0) {
				String propertyName = null; // javaBean属性名
				Object propertyValue = null; // javaBean属性值
				for (PropertyDescriptor pd : propertyDescriptors) {
					propertyName = pd.getName();
					if (map.containsKey(propertyName)) {
						propertyValue = map.get(propertyName);
						pd.getWriteMethod().invoke(obj, new Object[]{propertyValue});
					}
				}
				return (T) obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * JavaBean对象转化成 Map对象
	 * 
	 * @param javaBean
	 * @return
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	public static Map java2Map(Object javaBean) {
		if (javaBean == null) {
			return null;
		}
		Map map = new HashMap();
		try {
			// 获取javaBean属性
			BeanInfo beanInfo = Introspector.getBeanInfo(javaBean.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			if (propertyDescriptors != null && propertyDescriptors.length > 0) {
				String propertyName = null; // javaBean属性名
				Object propertyValue = null; // javaBean属性值
				for (PropertyDescriptor pd : propertyDescriptors) {
					propertyName = pd.getName();
					if (!propertyName.equals("class")) {
						Method readMethod = pd.getReadMethod();
						propertyValue = readMethod.invoke(javaBean, new Object[0]);
						map.put(propertyName, propertyValue);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * JavaBean对象转化成 Map对象
	 * 
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> transBean2Map(Object obj) {
		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);
					map.put(key, value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
