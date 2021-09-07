package com.bedrock.rsocketclient.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class GreetingRequest {

  @NonNull
  private String name;

}
