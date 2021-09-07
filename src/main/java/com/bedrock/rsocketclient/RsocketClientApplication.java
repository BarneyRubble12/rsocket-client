package com.bedrock.rsocketclient;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RsocketClientApplication {

  @SneakyThrows
  public static void main(String[] args) {
    SpringApplication.run(RsocketClientApplication.class, args);
    System.in.read();
  }
}
