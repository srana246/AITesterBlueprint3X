package com.bookerframework.base;

import com.bookerframework.utils.ReportManager;
import com.bookerframework.config.ConfigManager;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners(ReportManager.class)
public class BaseTest {
  @BeforeSuite
  public void setUp() {
    RestAssured.baseURI = ConfigManager.getInstance().getBaseUrl();
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
  }
}
