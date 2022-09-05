package config;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;

public class AppUtil {
public static WebDriver driver; 
public static Properties config;
@BeforeTest
public void setup() throws Throwable
{
	config = new Properties();
	config.load(new FileInputStream("C:\\eclipse\\11clockworkframes\\DDT_FrameWork\\PropertyFiles\\Environment.properties"));
if(config.getProperty("Browser").equalsIgnoreCase("chrome"))
{
	driver = new ChromeDriver();
	driver.manage().window().maximize();
}
else if(config.getProperty("Browser").equalsIgnoreCase("firefox"))
{
	driver = new FirefoxDriver();
}
else
{
	Reporter.log("Browser value not matching",true);
	
}
	}
}
