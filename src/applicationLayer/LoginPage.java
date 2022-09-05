package applicationLayer;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
public class LoginPage {
//define repository
	@FindBy(name="txtUsername")
	WebElement user;
	@FindBy(name="txtPassword")
	WebElement pass;
	@FindBy(xpath="//input[@id='btnLogin']")
	WebElement loginbtn;
	//write method
	public void verifylogin(String usernane,String password)
	{
			user.sendKeys(usernane);
		pass.sendKeys(password);
		loginbtn.submit();
	}
}
