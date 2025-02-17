package selenuim_framework_design.pageObjects;

//import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.PageFactory;

import selenium_framework_design.AbstractComponents.AbstractComponent;

public class ProductsPage extends AbstractComponent {
	
	WebDriver driver;
	
	public ProductsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='card-body']/h5")
	List<WebElement> productNames;
	
	@FindBy(xpath="//div[@class='card-body'] //button[2]")
	List<WebElement> addToCartButtons;
	
	@FindBy(css = "button[routerlink*='cart']")
	WebElement cartButton;
	
	public List<WebElement> getProductNames() {
		waitForElementsToAppear(By.xpath("//div[@class='card-body']/h5"));
		return productNames;
	}
	public void addElementsToCart(List<String> wantedProductsList) {
		int itemsAddedToCart = 0;
		List<WebElement> pNames = getProductNames();
		System.out.println(pNames.size());
//		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
		for(int i=0;i<pNames.size();i++) {
			System.out.println(pNames.get(i).getText());
			if(wantedProductsList.contains(pNames.get(i).getText())) {
//				driver.findElement(By.xpath("(//div[@class='card-body'] //button[2])["+(i+1)+"]")).click();
				addToCartButtons.get(i).click();
				itemsAddedToCart++;
//				Thread.sleep(5000);
//				w.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
				waitForElementToAppear(By.id("toast-container"));
//				w.until(ExpectedConditions.invisibilityOf(driver.findElement((By.cssSelector(".ng-animating")))));
				waitForElementToDisappear(driver.findElement((By.cssSelector(".ng-animating"))));
			}
			if(itemsAddedToCart==wantedProductsList.size()) {
				break;
			}
		}
	}
	
	public CartPage goToCart() {
		cartButton.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}

}
