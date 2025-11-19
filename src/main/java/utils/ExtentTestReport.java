package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentTestReport {

    public static ExtentReports ExtentReportUtility() {
        //MAC
        String path = System.getProperty("user.dir") + "/src/main/java/report.html";

        //Windows
       // String path = System.getProperty("user.dir") + "\\src\\main\\java\\report.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Divyansh Test Report");
        reporter.config().setDocumentTitle("Test Result Report");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("tester", "Divyansh Bansal");
        return extent;


    }
}
