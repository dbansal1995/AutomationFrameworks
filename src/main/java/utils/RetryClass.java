package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryClass implements IRetryAnalyzer {
    @Override
    public boolean retry(ITestResult iTestResult) {
        int count = 0;
        int Maxretry = 1;

        while (count < Maxretry) {
            count++;
            return true;
        }
        return false;
    }
}
