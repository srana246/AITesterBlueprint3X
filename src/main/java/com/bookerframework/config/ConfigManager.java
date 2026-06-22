package com.bookerframework.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
  private static final ConfigManager INSTANCE = new ConfigManager();
  private final Properties properties = new Properties();

  private ConfigManager() {
    try (InputStream stream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
      if (stream != null) {
        properties.load(stream);
      }
    } catch (IOException ignored) {
    }
  }

  public static ConfigManager getInstance() {
    return INSTANCE;
  }

  public String getBaseUrl() {
    return properties.getProperty("base.url", "https://restful-booker.herokuapp.com");
  }

  public String getAdminUsername() {
    return properties.getProperty("admin.username", "admin");
  }

  public String getAdminPassword() {
    return properties.getProperty("admin.password", "password123");
  }

  public int getDefaultTimeout() {
    return Integer.parseInt(properties.getProperty("default.timeout", "10000"));
  }

  public String getReportPath() {
    return properties.getProperty("report.path", "reports/extent-report.html");
  }
}
