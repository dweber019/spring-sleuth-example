package ch.basler.experimental.sleuthexample;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class LoggingConfig {

  @Bean
  public CommonsRequestLoggingFilter requestLoggingFilter() {
    CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
    loggingFilter.setIncludeHeaders(true);
    return loggingFilter;
  }

  @Bean
  public RestTemplate getRestTemplate() {
    SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
    Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress("localhost", 8888));
    requestFactory.setProxy(proxy);
    RestTemplate restTemplate = new RestTemplate(requestFactory);
    restTemplate.setInterceptors(Collections.singletonList(new LoggingInterceptor()));
    return restTemplate;
  }
}