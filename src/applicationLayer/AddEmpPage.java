package applicationLayer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
public class AddEmpPage {
WebDriver driver;
public AddEmpPage(WebDriver driver)
{
	this.driver=driver;
	}
@FindBy(xpath="//b[contains(test(),'PIM')]")
WebElement pim;
@FindBy(name="btnAdd")
WebElement addbtn;
@FindBy(xpath="//input[@id='firstName']")
WebElement fname;
@FindBy(xpath="//input[@id='middleName']")
WebElement mname;
@FindBy(xpath="//input[@id='lastName']")
WebElement lname;
@FindBy(xpath="//input[@id='employeeId']")
WebElement beforeeid;
@FindBy(xpath="//input[@id='btnSave']")
WebElement savebtn;
@FindBy(xpath="//input[@id='personal_txtEmployeeId']")
WebElement aftereid;
public boolean verifyAddEmp(String firstName,String middleName,String lastName)
{
	Actions ac = new Actions(driver);
	ac.moveToElement(this.pim).click().perform();
	ac.moveToElement(this.addbtn).click().perform();
	this.fname.sendKeys(firstName);; 
	this.mname.sendKeys(middleName);
	this.lname.sendKeys(lastName);
	//capture emid
	String expectedid =this.beforeeid.getAttribute("value");
	ac.moveToElement(savebtn).click().perform();
	String actualid =this.aftereid.getAttribute("value");
	if(expectedid.equals(actualid))
	{
		Reporter.log("Add emp Success::"+expectedid+"    "+actualid,true);
		return true;
	}
	else
	{
		Reporter.log("Add emp Fail::"+expectedid+"     "+actualid,true);
		return false;
	}
}
}


