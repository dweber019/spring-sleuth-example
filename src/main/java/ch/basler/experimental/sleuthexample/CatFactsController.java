package ch.basler.experimental.sleuthexample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
class CatFactsController {

  @Autowired
  RestTemplate restTemplate;

  @GetMapping(value = "/cats-facts")
  public String catsFacts() {
    log.info("You are at sleuth endpoint");

    return restTemplate.exchange("http://asdfast.beobit.net/api/",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<String>() {})
        .getBody();
  }
}
