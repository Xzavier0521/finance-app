package finance.mapper;

/**
 * 基本Mapper.
 * <pre>每一个具体的mapper需要继承它</pre>
 * @author hewenbin
 * @version v1.0 2018年8月10日 下午6:32:39 hewenbin
 */
public interface BaseDAO<T, V> {

    int insertSelective(T record);

    T selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(T record);

    /** 不允许使用以下接口*/
    //int deleteByPrimaryKey(V id);
    //int insert(T record);
    //int updateByPrimaryKey(T record);

}
