package finance.ext.integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

	// @Autowired
	// RestTemplateBuilder restTemplateBuilder;
	//
	// @Bean
	// public RestTemplate restTemplate(){
	// return restTemplateBuilder.build();
	// }

	@Bean
	public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
		return new RestTemplate(factory);
	}

	@Bean
	public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setReadTimeout(3000);// 单位为ms
		factory.setConnectTimeout(3000);// 单位为ms
		return factory;
	}
}
