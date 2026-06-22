package com.bookerframework.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.bookerframework.config.ConfigManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ReportManager implements ITestListener {
  private static final ExtentReports EXTENT = createReporter();
  private static final ThreadLocal<ExtentTest> TEST_THREAD = new ThreadLocal<>();

  private static ExtentReports createReporter() {
    ExtentSparkReporter reporter = new ExtentSparkReporter(ConfigManager.getInstance().getReportPath());
    ExtentReports extent = new ExtentReports();
    extent.attachReporter(reporter);
    return extent;
  }

  @Override
  public void onStart(ITestContext context) {
    EXTENT.setSystemInfo("Base URL", ConfigManager.getInstance().getBaseUrl());
  }

  @Override
  public void onFinish(ITestContext context) {
    EXTENT.flush();
  }

  @Override
  public void onTestStart(ITestResult result) {
    ExtentTest test = EXTENT.createTest(result.getMethod().getMethodName());
    TEST_THREAD.set(test);
  }

  @Override
  public void onTestSuccess(ITestResult result) {
    TEST_THREAD.get().log(Status.PASS, "Test passed");
  }

  @Override
  public void onTestFailure(ITestResult result) {
    TEST_THREAD.get().log(Status.FAIL, result.getThrowable());
  }

  @Override
  public void onTestSkipped(ITestResult result) {
    TEST_THREAD.get().log(Status.SKIP, "Test skipped");
  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
  }
}
