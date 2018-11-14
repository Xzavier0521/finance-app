package finance.core.common.util;

import java.io.*;
import java.util.List;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: CollectionUtils.java, v 0.1 2018/9/28 下午2:48 lili Exp $
 */
public class CollectionUtils {

    public static <T> List<T> depCopy(List<T> srcList) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(srcList);
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream inStream = new ObjectInputStream(byteIn);
        List<T> destList = (List<T>) inStream.readObject();
        return destList;
    }


}
