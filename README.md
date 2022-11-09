# spring-sleuth-example
With OpenApi, RestTemplate and request logging

## Swagger UI
You can find the OpenAPI UI at http://localhost:8080/swagger-ui/index.html

As the Sleuth headers (default B3) aren't directly generated into the OpenAPI specification we have to add them manually.  
See `OpenApiConfig.java`.

## Sleuth settings
If you like to pass tracing context from outside the app you need to define at least `TraceId` and `SpanId`.

**Here are some valid example values:**  
X-B3-TraceId: 80f198ee56343ba864fe8b2a57d3eff7  
X-B3-ParentSpanId: 05e3ac9a4f6e3b90  
X-B3-SpanId: e457b5a2e4d86bd1  
X-B3-Sampled: 1

## Logging and proxy
All logging configs can be found at `LoggingConfig.java` including a proxy config as this example was behind a proxy.  
Just remove the factory if you don't need it.

## Baggage
You can use the [testing.http](./testing.http) file to test.
We propagate the `cid` field via `baggage` header. You need to add the `cid` to `remote-fields` and `correlation-fields` in [application.yaml](./src/main/resources/application.yml).

You also need to specify specific log pattern:
```yaml
logging.pattern.level: "%5p [${spring.application.name:},%X{traceId:-},%X{spanId:-},CID=%X{cid:-}]"
```

This will generate the following log example:
```text
2022-11-07 16:22:04.583  INFO [Sleuth Example,3e7eb8c882e31157,261ccb470dc092c4,CID=my-correalation-id] 22656 --- [nio-8080-exec-1] c.b.e.sleuthexample.CatFactsController   : You are at sleuth endpoint
```