package department.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import department.TestComponents.BaseTest;
import department.pageobjects.CartPage;
import department.pageobjects.CheckoutPage;
import department.pageobjects.ConfirmationPage;
import department.pageobjects.LandingPage;
import department.pageobjects.ProductCatalog;

public class ErrorValidations extends BaseTest {

	@Test
	public void LoginErrorValidation() throws IOException, InterruptedException 
	{
		String productname = "ZARA COAT 3";
		ProductCatalog productCatalog = landingPage.loginApplication("prasanthb@gmail.com", "Test@12345"); // Logging in
		Assert.assertEquals("Incorrect email or pass@word", landingPage.getErrorMessage());
	}

	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		String productname = "ZARA COAT 3";
		ProductCatalog productCatalog = landingPage.loginApplication("rahulshetty@gmail.com", "Test@1234"); // Logging
																											// in
		List<WebElement> products = productCatalog.getProductList(); // getting product list
		productCatalog.addProductToCart(productname); // adding products to cart
		CartPage cartPage = productCatalog.goToCart(); // going to cart page
		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33"); // verifying the product display
		Assert.assertFalse(match);
	}
}
