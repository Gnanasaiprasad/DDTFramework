package driverFactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import applicationLayer.AddEmpPage;
import applicationLayer.LoginPage;
import applicationLayer.LogoutPage;

public class TestScript {
WebDriver driver;
@BeforeTest
public void setup()
{
	driver= new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("http://orangehrm.qedgetech.com/");
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	LoginPage login =PageFactory.initElements(driver,LoginPage.class);
	login.verifylogin("Admin","Qedge123!@#");
}
@Test
public void addemp()
{
	AddEmpPage emp = PageFactory.initElements(driver, AddEmpPage.class);
	boolean res=emp.verifyAddEmp("Akilesh","Gnana","Selenium");
	System.out.println(res);
}
@AfterTest
public void tearDown()
{
	LogoutPage logout =PageFactory.initElements(driver, LogoutPage.class);
	logout.verifyLogout();
	driver.close();
}
}
