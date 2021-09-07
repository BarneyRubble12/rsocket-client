package com.bedrock.rsocketclient;

import com.bedrock.rsocketclient.dto.GreetingRequest;
import com.bedrock.rsocketclient.dto.GreetingResponse;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.rsocket.RSocketRequester;

@SpringBootApplication
public class RsocketClientApplication {

  @SneakyThrows
  public static void main(String[] args) {
    SpringApplication.run(RsocketClientApplication.class, args);
    System.in.read();
  }
}
