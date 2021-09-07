package com.bedrock.rsocketclient.health.controller;

import com.bedrock.rsocketclient.health.dto.ClientHealthState;
import java.time.Duration;
import java.util.stream.Stream;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class HealthController {

  @MessageMapping("health")
  Flux<ClientHealthState> healthy() {
    return Flux
        //forces the client to be unhealthy at some point
        .fromStream(Stream.generate(() -> new ClientHealthState(Math.random() > 0.2)))
        .delayElements(Duration.ofSeconds(1));
  }

}
