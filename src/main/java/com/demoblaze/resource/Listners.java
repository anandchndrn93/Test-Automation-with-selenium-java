package com.demoblaze.resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.demoblaze.utilities.screenShotUtility;

public class Listners implements ITestListener {
	ExtentReports extent = ExtentReport.getReport();
	ExtentTest test;
	public static ThreadLocal<ExtentTest> thread = new ThreadLocal<ExtentTest>();
	private static Logger log = LogManager.getLogger(Listners.class.getName());

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		// on start of test start extent reporting
		test = extent.createTest(result.getMethod().getMethodName());
		thread.set(test);
		log.info("starting Test Case: " + result.getMethod().getMethodName());
		getReporter().log(Status.INFO, "starting Test Case: " + result.getMethod().getMethodName());
		ITestListener.super.onTestStart(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		getReporter().log(Status.PASS, "Test Passed");
		log.debug("Test Passed");
		attachScreenShot(result); // attch screenshot
		ITestListener.super.onTestSuccess(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		log.fatal(result.getThrowable());
		result.getThrowable().printStackTrace();
		getReporter().log(Status.FAIL, "Test Failed Abruptly");
		String methodName = result.getMethod().getMethodName();
		log.fatal("Test Failed Abruptly. Test name: " + methodName);
		attachScreenShot(result);// attch screenshot
		ITestListener.super.onTestFailure(result);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		String testCaseName = result.getMethod().getMethodName();
		getReporter().log(Status.SKIP, "Test was Skipped");
		getReporter().skip(result.getThrowable());
		log.fatal("Test was skipped!. Test name: " + testCaseName);
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
		ITestListener.super.onFinish(context);
	}

	public static ExtentTest getReporter() {
		return thread.get();
	}

	public void attachScreenShot(ITestResult result) {
		// method to attach screen shot to extent reports
		WebDriver driver = null;
		String methodName = result.getMethod().getMethodName();
		Object currentClass = result.getInstance();
		try {
			driver = ((TestBase) currentClass).getDriver();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// will take screenshot on test failure
		String srcpath = screenShotUtility.getScreenShot(driver, methodName);
		thread.get().addScreenCaptureFromPath(srcpath);
	}
}
