package com.tutorialsNinja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsNinja.qa.utils.ExtentReporter;
import com.tutorialsNinja.qa.utils.Utilities;

public class MyListeners implements ITestListener {
	
	ExtentReports extentreports;
	ExtentTest extenttest;
	
	@Override
	public void onStart(ITestContext context) {
		extentreports = ExtentReporter.generateExtentReports();
			}
	@Override
	public void onTestStart(ITestResult result) {
		
		extenttest = extentreports.createTest(result.getName());
		extenttest.log(Status.INFO,result.getName()+"started executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		extenttest.log(Status.PASS,result.getName()+ "got successfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		File srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath = System.getProperty("user.dir")+"\\screenshots\\"+result.getName()+".png";
		try {
			FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		extenttest.addScreenCaptureFromPath(destinationScreenshotPath);
		extenttest.log(Status.INFO,result.getThrowable());	
		extenttest.log(Status.FAIL,result.getName()+" got failed");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		
		extenttest.log(Status.INFO,result.getThrowable());
		extenttest.log(Status.SKIP,result.getName()+" got skipped");
		System.out.println(result.getName()+"got skipped");
		System.out.println(result.getThrowable());
	}

	

	@Override
	public void onFinish(ITestContext context) {
		
		extentreports.flush();
		String extentreportPath = System.getProperty("user.dir")+"\\test-output\\extentreportfolder";
		File extentreports = new File(extentreportPath);
		try {
			Desktop.getDesktop().browse(extentreports.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}

}
