package student.examples.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test ending: " + result.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test starting: " + result.getName());

    }
}

