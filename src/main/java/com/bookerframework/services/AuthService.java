package com.bookerframework.services;

import com.bookerframework.config.ConfigManager;
import com.bookerframework.constants.EndPoints;
import com.bookerframework.models.TokenRequest;
import com.bookerframework.utils.RequestSpecUtil;
import io.restassured.response.Response;
import java.util.concurrent.atomic.AtomicReference;

public class AuthService {
  private static final AtomicReference<String> TOKEN_CACHE = new AtomicReference<>();

  public static String getToken() {
    String token = TOKEN_CACHE.get();
    if (token == null) {
      token = generateToken();
      TOKEN_CACHE.set(token);
    }
    return token;
  }

  public static String generateToken() {
    TokenRequest request = new TokenRequest(ConfigManager.getInstance().getAdminUsername(), ConfigManager.getInstance().getAdminPassword());
    Response response = io.restassured.RestAssured.given()
        .spec(RequestSpecUtil.getRequestSpecification())
        .body(request)
        .when()
        .post(EndPoints.AUTH);
    return response.then().statusCode(200).extract().path("token");
  }
}
