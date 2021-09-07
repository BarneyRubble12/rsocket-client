package com.bedrock.rsocketclient.health.controller;

import com.bedrock.rsocketclient.health.dto.ClientHealthState;
import java.time.Duration;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class HealthController {

  @MessageMapping("health")
  Flux<ClientHealthState> healthy() {
    var stream = Stream.generate(() -> new ClientHealthState(Math.random() > 0.2)); //forces the client to be unhealthy at some point
    return Flux
        .fromStream(stream)
        .delayElements(Duration.ofSeconds(1));
  }

}
