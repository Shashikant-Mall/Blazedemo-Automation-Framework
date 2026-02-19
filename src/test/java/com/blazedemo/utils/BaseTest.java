package com.blazedemo.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import com.google.common.io.Files;

public class BaseTest {
	
	protected WebDriver driver;
	protected Logger logger = LogManager.getLogger(getClass());
	protected Properties p;
		
  @BeforeMethod(groups= {"Smoke","Functional","Negative","Data-driven"})
  @Parameters({"OS","bName"})
  public void setUp(String os,String br) throws IOException {
	  
	  //Loading config properties file
	  FileReader file = new FileReader(".//src//test//resources//config.properties");
	  p = new Properties();
	  p.load(file);
	 
	  if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
		  
		  DesiredCapabilities cap = new DesiredCapabilities();
		  
		  //OS
		  if(os.equalsIgnoreCase("Windows")) {
			  cap.setPlatform(Platform.WIN11);
		  }
		  else if(os.equalsIgnoreCase("mac")) {
			  cap.setPlatform(Platform.MAC);
		  }
		  else if(os.equalsIgnoreCase("linux")) {
			  cap.setPlatform(Platform.LINUX);
		  }
		  else {
			  System.out.println("No matching os");
			  return;
		  }
		  //Browser
		  switch(br.toLowerCase()) {
		  case "chrome" : cap.setBrowserName("chrome"); break;
		  case "edge"  : cap.setBrowserName("MicrosoftEdge"); break;
		  case "firefox" :cap.setBrowserName("firefox"); break;
		  default : throw new IllegalArgumentException("Invalid browser name: " + br);
		  }
		  
		  driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
	  }
	  
	  if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
	  switch(br.toLowerCase()) {
	  case "chrome" : driver = new ChromeDriver(); break;
	  case "edge": driver = new EdgeDriver(); break;
	  case "firefox" : driver = new FirefoxDriver(); break;
	  default : throw new IllegalArgumentException("Invalid browser name: " + br);
	  }	  
	}
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	  driver.manage().window().maximize();
	  driver.get(p.getProperty("appURL"));	 
	  
	  logger.info("Browser launched and application opened");
	  logger.info("Running on OS: " + os + " Browser: " + br);
  }
  
  @AfterMethod(groups= {"Smoke","Functional","Negative","Data-driven"})
  public void tearDown() {
	  
	  if(driver!=null) {
		  driver.quit();
		  
		  logger.info("Browser closed");
	  }
  }
  
  public String captureScreenshot(String tname) throws IOException {
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());
		
		TakesScreenshot takesScreenshot= (TakesScreenshot) driver;
		File sourceFile=takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+".png";
		File targetFile=new File(targetFilePath);
		Files.copy(sourceFile, targetFile);		
		
		return targetFilePath;
	}
  
  
}
