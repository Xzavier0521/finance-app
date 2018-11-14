package finance.api.model.base;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * API中饭POST请求参数接受类.
 * @author hewenbin
 * @version v1.0 2018年7月17日 下午5:55:27 hewenbin
 */
public class XMap extends HashMap<Object, Object>{

	private static final long serialVersionUID = -87848835995168123L;
	
	public XMap() {
	}

	public XMap(Map<?, ?> m) {
		super(m);
	}
	
	/**
	 * Map转bean工具方法.
	 * @param clsss
	 * @return
	 * @author hewenbin
	 * @version XMap.java, v3.0 2018年7月18日 下午8:27:22 hewenbin
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> T toBean(Class<T> clsss) {
		T object = null;
		try {
			object = clsss.newInstance();
			org.apache.commons.beanutils.BeanUtils.populate(object, (Map)this);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return object;
	}
	
	/**
	 * Map转bean工具方法.
	 * @param clsss 
	 * @return
	 * @author hewenbin
	 * @version XMap.java, v1.0 2018年7月17日 下午5:56:40 hewenbin
	 */
	public <T> T toBean_v1(Class<T> clsss) {
		T object = null;
		try {
			object = clsss.newInstance();
			Field[] fiels = clsss.getDeclaredFields();
			for (Field field : fiels) {
				String filedName = field.getName();
				field.setAccessible(true);
				field.set(object, this.get(filedName));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return object;
	}
	
	
	/**
	 * Map转bean工具方法.
	 * @param clsss
	 * @return
	 * @author hewenbin
	 * @version XMap.java, v3.0 2018年7月18日 下午8:30:24 hewenbin
	 */
	public <T> T toBean_v2(Class<T> clsss) {
		T object = null;
		try {
			object = clsss.newInstance();
			Field[] fiels = clsss.getDeclaredFields();
			for (Field field : fiels) {
				String filedName = field.getName();
//				
				Object value = this.get(filedName);
				if (value == null) {
					continue;
				}
				String setMethodName = "set"    
                        + filedName.substring(0, 1).toUpperCase()  
                        + filedName.substring(1);    
                Class<?> fieldTypeClass = field.getType();    
                value = convertValType(value, fieldTypeClass);   
                	clsss.getMethod(setMethodName, field.getType()).invoke(object, value);   
                
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return object;
	}
	
	/**  
     * 将Object类型的值，转换成bean对象属性里对应的类型值  
     * @param value Object对象值  
     * @param fieldTypeClass 属性的类型  
     * @return 转换后的值  
     */    
    private static Object convertValType(Object value, Class<?> fieldTypeClass) {    
        Object retVal = null;    
        if(Long.class.getName().equals(fieldTypeClass.getName())    
                || long.class.getName().equals(fieldTypeClass.getName())) {    
            retVal = Long.parseLong(value.toString());    
        } else if(Integer.class.getName().equals(fieldTypeClass.getName())    
                || int.class.getName().equals(fieldTypeClass.getName())) {    
            retVal = Integer.parseInt(value.toString());    
        } else if(Float.class.getName().equals(fieldTypeClass.getName())    
                || float.class.getName().equals(fieldTypeClass.getName())) {    
            retVal = Float.parseFloat(value.toString());    
        } else if(Double.class.getName().equals(fieldTypeClass.getName())    
                || double.class.getName().equals(fieldTypeClass.getName())) {    
            retVal = Double.parseDouble(value.toString());    
        } else {    
            retVal = value;    
        }    
        return retVal;    
    }     
}
