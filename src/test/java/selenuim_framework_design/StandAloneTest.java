package selenuim_framework_design;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;  

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys("milansinghdav@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Milansingh@1");
		driver.findElement(By.id("login")).click();
		String[] wantedProducts = {"BANARSI SAREE","LG REFRIGERATOR"} ;
		List<String> wantedProductsList = Arrays.asList(wantedProducts);
		
		List<WebElement> allProducts = driver.findElements(By.xpath("//div[@class='card-body']/h5"));
		int itemsAddedToCart = 0;
		System.out.println(allProducts.size());
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
		for(int i=0;i<allProducts.size();i++) {
			System.out.println(allProducts.get(i).getText());
			if(wantedProductsList.contains(allProducts.get(i).getText())) {
				driver.findElement(By.xpath("(//div[@class='card-body'] //button[2])["+(i+1)+"]")).click();
				itemsAddedToCart++;
//				Thread.sleep(5000);
				w.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
				w.until(ExpectedConditions.invisibilityOf(driver.findElement((By.cssSelector(".ng-animating")))));
			}
			if(itemsAddedToCart==wantedProductsList.size()) {
				break;
			}
		}
		// go to cart page
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='cartSection'] //h3")));
		List<WebElement> cartItems = driver.findElements(By.xpath("//div[@class='cartSection'] //h3"));
		if(cartItems.size()!=wantedProductsList.size()) {
			Assert.assertFalse(true, "error with items in cart not equal to wanted items list");
		}
		else {
			for(int i = 0; i<cartItems.size();i++ ) {
				System.out.println(cartItems.get(i).getText());
				if(!wantedProductsList.contains(cartItems.get(i).getText())) {
					Assert.assertFalse(true,"Items in cart not match items in wanted products list");
				}
			}
		}
		// go to checkout
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//li[@class='totalRow']/button")));
		driver.findElement(By.xpath("//li[@class='totalRow']/button")).click();
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
		List<WebElement> countries = driver.findElements(By.xpath("//button[contains(@class,'ta-item')]"));
		List<WebElement> selectedCountry = countries.stream().filter(country->country.getText().equalsIgnoreCase("india")).collect(Collectors.toList());
		selectedCountry.get(0).click();
		driver.findElement(By.xpath("//div[@class='actions']/a")).click();
		List<String> orderIds = driver.findElements(By.xpath("//tr[@class='ng-star-inserted'] //label")).stream().map(el->{
			String elText = el.getText();
			elText = elText.substring(1, elText.length()-1).trim();
			System.out.println(elText);
			return elText;
		}).collect(Collectors.toList());
		// go to orders page
		Thread.sleep(3000);
		js.executeScript("window.scrollTo(0,0)");
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[routerlink*='myorders']")));
		driver.findElement(By.cssSelector("button[routerlink*='myorders']")).click();
		List<String> actualOrderIds = driver.findElements(By.xpath("//tbody/tr/th")).stream().map(oid->oid.getText()).collect(Collectors.toList());
		orderIds.stream().forEach(oid->{
			if(!actualOrderIds.contains(oid)) {
				Assert.assertFalse(true,"Order id not present in orders");
			}
		}
		);
		

	}

}
