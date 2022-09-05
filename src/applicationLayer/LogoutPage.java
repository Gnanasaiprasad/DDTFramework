package applicationLayer;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPage {
@FindBy(xpath="//a[@id='welcome']")
WebElement welcome;
@FindBy(xpath="//a[contains(test(),'Logout']")
WebElement logout;
public void verifyLogout()
{
	welcome.click();
	logout.click();
	
}
}
