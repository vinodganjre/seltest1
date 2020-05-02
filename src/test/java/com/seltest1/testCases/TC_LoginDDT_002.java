package com.seltest1.testCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.seltest1.pageObject.LoginPage;
import com.seltest1.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass
{
	@Test (dataProvider ="LoginData")
	public void loginDDT(String name, String pwd) throws InterruptedException 
	{
		LoginPage lp = new LoginPage(driver);
		//System.out.print("Vinod");
		
		lp.setUserName(name);
		lp.setPassword(pwd);
		
		System.out.println(name);
		System.out.println(pwd);
		
		//Thread.sleep(3000);
		
		lp.clickSubmit();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(1000);
		//driver.manage().window().maximize();
		
		//lp.clickLogout();
		
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		if(isAlertPresent() == true)
		{
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			lp.clickLogout();
			Thread.sleep(1000);
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
		}
	}
	
	
	public boolean isAlertPresent() //user defined to check the alert is present or not
	{
		try 
		{
			driver.switchTo().alert();
			return true;
		}
		catch (NoAlertPresentException e)
		{
			return false;
		}
	}
	
	
	
	@DataProvider(name="LoginData")
	String [][] getData() throws IOException
	{
		//System.out.print("Vinod1");
		String path = System.getProperty("user.dir")+"/src/test/java/com/seltest1/testData/LoginData.xlsx";
		//String path = "/Users/macbook/Desktop/Selenium Projects/Project1/selTest1" + "/src/test/java/com/seltest1/testData/LoginData.xlsx";
		
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);
		
		String logindata [][] = new String [rownum][colcount];
		
		for (int i=1; i<=rownum; i++)
		{
			for (int j=0; j<colcount; j++)
			{
				logindata[i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j); //1 0
				//System.out.println(logindata[i][j]);
				//System.out.print("Vinod2");
			}
			
		}
		//System.out.print(logindata[i][j]);
		return logindata;
	}
	
	
}
