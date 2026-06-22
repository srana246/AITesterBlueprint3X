package com.bookerframework.tests;

import com.bookerframework.base.BaseTest;
import com.bookerframework.config.ConfigManager;
import com.bookerframework.constants.EndPoints;
import com.bookerframework.models.TokenRequest;
import com.bookerframework.utils.RequestSpecUtil;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class AuthTests extends BaseTest {
  @Test(groups = {"smoke", "regression"})
  public void validAuth() {
    TokenRequest request = new TokenRequest(ConfigManager.getInstance().getAdminUsername(), ConfigManager.getInstance().getAdminPassword());
    Response response = io.restassured.RestAssured.given()
        .spec(RequestSpecUtil.getRequestSpecification())
        .body(request)
        .when()
        .post(EndPoints.AUTH);
    response.then().statusCode(200);
  }

  @Test(groups = {"negative"})
  public void invalidAuthWrongPassword() {
    TokenRequest request = new TokenRequest("admin", "wrongpassword");
    io.restassured.RestAssured.given()
        .spec(RequestSpecUtil.getRequestSpecification())
        .body(request)
        .when()
        .post(EndPoints.AUTH)
        .then()
        .statusCode(200)
        .body("reason", equalTo("Bad credentials"));
  }

  @Test(groups = {"negative"})
  public void invalidAuthEmptyCredentials() {
    TokenRequest request = new TokenRequest("", "");
    io.restassured.RestAssured.given()
        .spec(RequestSpecUtil.getRequestSpecification())
        .body(request)
        .when()
        .post(EndPoints.AUTH)
        .then()
        .statusCode(200)
        .body("reason", equalTo("Bad credentials"));
  }
}
