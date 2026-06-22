package com.bookerframework.utils;

import com.bookerframework.config.ConfigManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecUtil {
  public static RequestSpecification getRequestSpecification() {
    return new RequestSpecBuilder()
        .setBaseUri(ConfigManager.getInstance().getBaseUrl())
        .addHeader("Content-Type", "application/json")
        .addHeader("Accept", "application/json")
        .build();
  }

  public static RequestSpecification getRequestSpecificationWithToken(String token) {
    return new RequestSpecBuilder()
        .setBaseUri(ConfigManager.getInstance().getBaseUrl())
        .addHeader("Content-Type", "application/json")
        .addHeader("Accept", "application/json")
        .addCookie("token", token)
        .build();
  }
}
