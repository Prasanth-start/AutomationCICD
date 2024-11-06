package department.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import department.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{

	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		//Initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy (css=".totalRow button")
	WebElement checkoutButton;
	
	//Verify the product display in cart page
	public Boolean verifyProductDisplay(String productName)

	{
		Boolean match = cartProducts.stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout()
	{
		checkoutButton.click();
		return new CheckoutPage(driver);
	}

	
	
	

}
