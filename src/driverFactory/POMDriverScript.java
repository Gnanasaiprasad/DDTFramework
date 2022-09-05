package driverFactory;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import applicationLayer.AddEmpPage;
import applicationLayer.LoginPage;
import applicationLayer.LogoutPage;
import utilities.ExcelFileUtil;
public class POMDriverScript {
WebDriver driver;
String inputpath ="C:\\eclipse\\11clockworkframes\\DDT_FrameWork\\DataTables\\EmpData.xlsx";
String outputpath ="C:\\eclipse\\11clockworkframes\\DDT_FrameWork\\TestResults\\Results.xlsx";
ExtentReports report;
ExtentTest test;
@BeforeSuite
public void setup() throws Throwable
{
	report = new ExtentReports("./Reports/PomDataDriven.html");
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("http://orangehrm.qedgetech.com/");
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	LoginPage login =PageFactory.initElements(driver, LoginPage.class);
	login.verifylogin("Admin", "Qedge123!@#");
}
@Test
public void validateEmp()throws Throwable
{
	//create object excel file util class
	ExcelFileUtil xl=new ExcelFileUtil(inputpath);
	int rc=xl.rowCount("AddEmp");
	Reporter.log("No of rows in AddEmp sheet::"+rc,true);
	for(int i=1;i<=rc;i++)
	{
		test=report.startTest("Validate EMP");
	String fname =xl.getcelldata("AddEmp", i, 0);
	String mname =xl.getcelldata("AddEmp", i, 1);
	String lname =xl.getcelldata("AddEmp", i, 2);
	AddEmpPage emp =PageFactory.initElements(driver, AddEmpPage.class);
	boolean res =emp.verifyAddEmp(fname, mname, lname);
	if(res)
	{
		//if it true write as pass into status cell
		xl.setCellData("AddEmp", i, 3, "pass", outputpath);
		test.log(LogStatus.PASS,"Add Emp success");
	}
	else
	{
		//if it false write as fail into status cell
		xl.setCellData("AddEmp", i, 3, "Fail", outputpath);
	test.log(LogStatus.FAIL, "Add Emp Success");
	}
	report.endTest(test);
		report.flush();		
	}
}
	@AfterTest
	public void tearDown()
	{
LogoutPage logout =PageFactory.initElements(driver, LogoutPage.class);
		logout.verifyLogout();
		driver.close();	
}
}
