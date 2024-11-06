package department.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import department.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		//Initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (id="userEmail")
	WebElement userEmail;
	
	@FindBy (id="userPassword")
	WebElement userPassword;
	
	@FindBy (id="login")
	WebElement submit;
	
	@FindBy (css="[class*=flyInOut]")
	WebElement errorMessage;
	
	
	//driver.findElement(By.id("userEmail")).sendKeys("prasanthb@gmail.com");
	//driver.findElement(By.id("userPassword")).sendKeys("Test@1234");
	//driver.findElement(By.id("login")).click();
	public ProductCatalog loginApplication(String email, String password) throws InterruptedException
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		ProductCatalog productCatalog = new ProductCatalog(driver);
		return productCatalog;
	}
	
	//driver.get("https://rahulshettyacademy.com/client/");
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
