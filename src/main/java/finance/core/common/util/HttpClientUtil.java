package finance.core.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 注释
 * </p >
 *
 * @author liwei
 * @version $Id: HttpClientUtil.java, v0.1 2018/11/20 下午8:05 PM user Exp $
 */
@Slf4j
public class HttpClientUtil {

	public static InputStream getImageStream(String url) {
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setReadTimeout(5000);
			connection.setConnectTimeout(5000);
			connection.setRequestMethod("GET");
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStream inputStream = connection.getInputStream();
				return inputStream;
			}
		} catch (IOException e) {
			log.info("[获取网络图片出现异常],图片路径:", url);
			e.printStackTrace();
		}
		return null;
	}

}
