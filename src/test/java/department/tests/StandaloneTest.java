package department.tests;

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

public class StandaloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String productname="ZARA COAT 3";
		WebDriver driver = new ChromeDriver();

		driver.get("https://rahulshettyacademy.com/client/");
		
		driver.manage().window().maximize();
		
		//implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.id("userEmail")).sendKeys("prasanthb@gmail.com");
		
		driver.findElement(By.id("userPassword")).sendKeys("Test@1234");
		
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class, 'mb-3')]"));
	    WebElement prod = products.stream().filter(product->
		product.findElement(By.xpath(".//div[@class='card-body']//b")).getText().equals(productname)).findFirst().orElse(null);
		prod.findElement(By.xpath(".//div[@class='card-body']/button[2]")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List <WebElement> cartProduct = driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean match = cartProduct.stream().anyMatch(p->p.getText().equalsIgnoreCase(productname));
		
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(driver);
		
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
		
		//System.out.println(driver.findElement(By.cssSelector("input[placeholder='Select Country']")).getText());
		
		WebElement placeOrder = driver.findElement(By.cssSelector(".action__submit"));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click();", placeOrder);
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		driver.quit();	
		
		
		
	}
	

}
