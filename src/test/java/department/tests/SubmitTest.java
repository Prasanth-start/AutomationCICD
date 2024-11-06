package department.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import department.TestComponents.BaseTest;
import department.pageobjects.CartPage;
import department.pageobjects.CheckoutPage;
import department.pageobjects.ConfirmationPage;
import department.pageobjects.OrderPage;
import department.pageobjects.ProductCatalog;

public class SubmitTest extends BaseTest {

	//String productName = "ZARA COAT 3";

	@Test (dataProvider="getdata", groups ="Purchase")
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {

		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password")); // Logging in
		
		List<WebElement> products = productCatalog.getProductList(); // getting product list
		productCatalog.addProductToCart(input.get("productName")); // adding products to cart

		CartPage cartPage = productCatalog.goToCart(); // going to cart page

		Boolean match = cartPage.verifyProductDisplay(input.get("productName")); // verifying the product display

		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout(); // checkout
		checkoutPage.selectCountry("India"); // Selecting the country
		Thread.sleep(4000);
		ConfirmationPage confirmationPage = checkoutPage.submitOrder(); // Submitting the order
		Thread.sleep(4000);
		String confirmMessage = confirmationPage.getConfirmationMessage(); // getting the confirmation page & message
		Thread.sleep(4000);
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods= {"submitOrder"})

	public void orderHistoryTest() throws InterruptedException

	{
		String productName = "ADIDAS ORIGINAL";
		ProductCatalog productCatalog = landingPage.loginApplication("prasanthb@gmail.com", "Test@123456");
		OrderPage ordersPage = productCatalog.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	}

	@DataProvider
	public Object[][] getdata() throws IOException
	{

		List<HashMap<String,String>>data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//department//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

	
}


//	HashMap<String,String>map = new HashMap<String,String>();
//		map.put("userEmail", "prasanthb@gmail.com");
//		map.put("userPassword","Test@1234");
//		map.put("productName","ZARA COAT 3");
	
	
//		HashMap<String,String>map1 = new HashMap<String,String>();
//		map1.put("userEmail", "prasanthb1@gmail.com");
//		map1.put("userPassword","Test@123456");
//		map1.put("productName","ADIDAS ORIGINAL");


