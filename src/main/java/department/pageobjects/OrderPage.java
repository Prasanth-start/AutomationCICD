package department.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import department.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{

	WebDriver driver;
	
	
	@FindBy (css="tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	@FindBy (css=".totalRow button")
	WebElement checkoutButton;
	
	public OrderPage(WebDriver driver)
	{
		super(driver);
		//Initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//Verify the product display in cart page
	public Boolean verifyOrderDisplay(String productName)

	{
		Boolean match = productNames.stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	
	

	
	
	

}
