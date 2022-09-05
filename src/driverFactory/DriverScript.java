package driverFactory;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import commonFunctions.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtil;
public class DriverScript extends AppUtil {
String inputpath="C:\\eclipse\\11clockworkframes\\DDT_FrameWork\\DataTables\\LoginData.xlsx";
String outputpath="C:\\eclipse\\11clockworkframes\\DDT_FrameWork\\TestResults\\DataDrivenResults.xlsx";
ExtentReports report;
ExtentTest test;
@Test
public void Login() throws Throwable
{
	 report = new ExtentReports("./Report/DataDriven.html");
	//create object for excel file util
	ExcelFileUtil xl =new ExcelFileUtil(inputpath);
	//count no of rows
	int rc =xl.rowCount("Login");
	Reporter.log("No of rows are::"+rc, true);
	for(int i=1;i<=rc;i++)
	{
     	test =report.starttest("Validate Login");
		 String user = xl.getcelldata("Login", i, 0);
		String pass =xl.getcelldata("Login", i, 1);
		//cell login method
		boolean res =FunctionLibrary.verifylogin(user, pass);
		if(res)
		{
			//write as login success into result cell
			xl.setCellData("Login", i, 2," Login success",outputpath);
			xl.setCellData("Login", i, 3, "Pass",outputpath);
			 test.log(LogStatus.PASS, "Login Success");
		}
		else
		{
File screen =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
FileUtils.copyFile(screen,new File("./Screens/Iteration/"+i+"Loginpage.png"));
			//write as login Fail into result cell
			xl.setCellData("Login", i, 2," Login Fail",outputpath);
			xl.setCellData("Login", i, 3, "Fail",outputpath);
            test.log(LogStatus.FAIL, "Login Fail");
		}
		report.endtest(test);
		report.flush();
		}
			
	}
}

