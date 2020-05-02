package com.seltest1.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.seltest1.utilities.ReadConfig;



public class BaseClass 
{
	//public String baseURL = "http://demo.guru99.com/V4/";
	//public String username = "mngr257739";
	//public String password = "dUpYrAz";
	
	ReadConfig readconfig = new ReadConfig ();
	
	public String baseURL = readconfig.getApplicationUrl();
	public String username = readconfig.getUsername();
	public String password = readconfig.getPassword();
	
	//public String chromepath = readconfig.getChromePath();
	
	
	public static WebDriver driver;
	
	//public static Logger Logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br)
	{
		
		//Logger = Logger.getLogger("selTest");
		//PropertyConfigurator.configure("Log4j.properties");
		
		if(br.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", readconfig.getChromePath() );
			driver = new ChromeDriver();
		}
		else if(br.equals("safari"))
		{
			driver = new SafariDriver();
		}	
		else if(br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", readconfig.getFirefoxPath() );
			driver = new FirefoxDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseURL);
		//Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	
	//this is common to all TCs to take screenshot of failure test case
	public void captureScreen(WebDriver driver, String tname) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile (source, target);
		System.out.println ("Scrnshot taken");
	}
	

}
