package commonFunctions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil {
public static Boolean verifylogin(String username,String password)
{
driver.get(config.getProperty("url"));
driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);                          	driver.findElement(By.xpath(config.getProperty("objUser"))).sendKeys(username);
driver.findElement(By.xpath(config.getProperty("objPass"))).sendKeys(password);
driver.findElement(By.xpath(config.getProperty("objLogin"))).click();
	String expected = "dashboard";
	String actual = driver.getCurrentUrl();
	if(actual.contains(expected))
	{
		Reporter.log("Login success",true);
		return true;
	}
	else
	{
		//capture error message
String errormessage =driver.findElement(By.xpath(config.getProperty("objError"))).getText();
	Reporter.log(errormessage,true);
		return false;
		
	}
}
}
