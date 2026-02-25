package com.blazedemo.utils;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testContext) {
		
		//Time Stamp by using in short of immediate above commented lines
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName="Test-Report-"+timeStamp+".html";
		
		//Specify location of the report
		sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);
		
		sparkReporter.config().setDocumentTitle("Blazedemo-Automation Report");  //Title of report
		sparkReporter.config().setReportName("Blazedemo Functional Testing");    //Name of report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Blazedemo");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String operatingSys=testContext.getCurrentXmlTest().getParameter("OS");
		extent.setSystemInfo("Operating Syatem", operatingSys);
		
		String browser=testContext.getCurrentXmlTest().getParameter("bName");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}		
	}
	public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+"got successfully executed");
	}
	
	public void onTestFailure(ITestResult result) {

		test = extent.createTest(result.getMethod().getMethodName());
        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.FAIL, "Test failed: " + result.getName());
        test.log(Status.INFO, result.getThrowable().getMessage());

        try {
            BaseTest baseTest = (BaseTest) result.getInstance();
            String imgPath = baseTest.captureScreenshot(result.getName());
            test.addScreenCaptureFromPath(imgPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+"got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	public void onFinish(ITestContext testcontext) {
		extent.flush();
		
		/*
		//For opening report automatically:-
		
		String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport=new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}catch(Exception e) {
			e.printStackTrace();
		} */
	}

	
}
