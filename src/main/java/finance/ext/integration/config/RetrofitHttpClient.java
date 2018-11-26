package finance.ext.integration.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * http 请求
 * </p>
 *
 * @author lili
 * @version 0.1: RetrofitHttpClient.java, v 0.1 2017/7/31 lili Exp $$
 */
@Slf4j
@Service("retrofitHttpClient")
public class RetrofitHttpClient {

	public Retrofit build(String url) {

		// 声明日志类
		HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

		// 设定日志级别
		httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

		// 定制OkHttp
		OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
		httpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
		httpClientBuilder.connectTimeout(60, TimeUnit.SECONDS);
		ConnectionPool connectionPool = new ConnectionPool(20, 5, TimeUnit.MINUTES);
		httpClientBuilder.connectionPool(connectionPool);
		// OkHttp进行添加拦截器loggingInterceptor
		httpClientBuilder.addInterceptor(httpLoggingInterceptor);
		OkHttpClient okHttpClient = httpClientBuilder.build();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return new Retrofit.Builder().baseUrl(url).client(okHttpClient)
				.addConverterFactory(JacksonConverterFactory.create(objectMapper))
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
	}
}
