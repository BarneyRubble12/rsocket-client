package com.bedrock.rsocketclient.config;

import com.bedrock.rsocketclient.dto.GreetingRequest;
import com.bedrock.rsocketclient.dto.GreetingResponse;
import com.bedrock.rsocketclient.health.controller.HealthController;
import io.rsocket.SocketAcceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.messaging.rsocket.annotation.support.RSocketMessageHandler;

@Configuration
@Slf4j
public class RSocketClientConfig {


  @Bean
  RSocketRequester rSocketRequester(RSocketRequester.Builder builder, SocketAcceptor socketAcceptor) {
    return builder
        .rsocketConnector(connector -> connector.acceptor(socketAcceptor))
        .tcp("localhost", 8888);
  }

  @Bean
  ApplicationListener<ApplicationReadyEvent> client(RSocketRequester client) {
    return arguments -> {
      var greetingResponseFlux = client.route("greetings")
          .data(new GreetingRequest("Bedrock"))
          .retrieveFlux(GreetingResponse.class);
      greetingResponseFlux.subscribe(response -> log.info(response.toString()));
    };
  }

  @Bean
  SocketAcceptor socketAcceptor(RSocketStrategies strategies,
      HealthController healthController) {
    return RSocketMessageHandler.responder(strategies, healthController);
  }


}
