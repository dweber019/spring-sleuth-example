package ch.basler.experimental.sleuthexample;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoggingInterceptor implements ClientHttpRequestInterceptor {

  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
    logRequest(request);
    return execution.execute(request, body);
  }

  private void logRequest(HttpRequest request) {
      log.info("Method: {}, URI: {}, Headers: {}", request.getMethod(), request.getURI(), request.getHeaders());
  }
}
