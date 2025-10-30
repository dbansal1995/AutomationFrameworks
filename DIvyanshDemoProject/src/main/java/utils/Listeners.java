package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.opentelemetry.sdk.trace.internal.ExtendedSpanProcessor;
import org.apache.commons.io.FileUtils;
import org.example.Base;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Listeners extends Base implements ITestListener {

    ExtentReports extent = ExtentTestReport.ExtentReportUtility();
    ExtentTest test;
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal();

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        System.out.println("Test Case Passed " + result.getMethod().getMethodName());
        test.log(Status.PASS, result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        test.log(Status.FAIL, result.getMethod().getMethodName());
        test.fail(result.getThrowable());

        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
                    .get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        TakesScreenshot ts = (TakesScreenshot) driver;
        File screenshot = ts.getScreenshotAs(OutputType.FILE);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy_HHmmss");
        String dateTime = simpleDateFormat.format(new Date());
        System.out.println(dateTime);
        String screenShotName = result.getName() + "_" + dateTime;
//        try {
//            //Apache commons io dependency need to get installed so as to use it as filw reading, writing operations
//            FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + "\\src\\main\\java\\" + screenShotName + ".png"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        test.addScreenCaptureFromPath(System.getProperty("user.dir") + "\\src\\main\\java\\" + screenShotName + ".png");
        //new File(System.getProperty("user.dir")+"\\src\\main\\screenshot.png"))
        System.out.println("Test Case Failed" + result.getTestName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        extent.flush();
    }

    @Override
    public boolean isEnabled() {
        return ITestListener.super.isEnabled();
    }
}
