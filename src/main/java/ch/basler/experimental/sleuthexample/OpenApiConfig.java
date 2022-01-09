package ch.basler.experimental.sleuthexample;

import brave.propagation.B3Propagation;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

@Configuration
public class OpenApiConfig {

  @Bean
  public OperationCustomizer customGlobalHeaders() {
    return (Operation operation, HandlerMethod handlerMethod) -> {
      B3Propagation.get().keys().forEach(header -> {
        Parameter parameter = new Parameter().in(ParameterIn.HEADER.toString()).schema(new StringSchema())
            .name(header).description("Specification at https://github.com/openzipkin/b3-propagation");
        operation.addParametersItem(parameter);
      });
      return operation;
    };
  }
}