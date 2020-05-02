package com.seltest1.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.seltest1.pageObject.LoginPage;

public class TC_LoginTest_001 extends BaseClass
{
	@Test
	public void loginTest() throws IOException
	{
		//driver.get(baseURL);
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		lp.setPassword(password);
		
		
		lp.clickSubmit();
		
		//driver.wait();
		//System.out.print(driver.getTitle());
		
		if(driver.getTitle().equals("Guru99 Bank Home Page"))
		{
			Assert.assertTrue(true);
			//logger.info("Login test passed");
		}
		else
		{
			//add screenshot whenever test case is fail
			captureScreen(driver, "LoginTest");
			
			Assert.assertTrue(false);
			//logger.info("Login test failed");
		}
	}	
}
