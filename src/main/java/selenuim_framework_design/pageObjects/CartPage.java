package selenuim_framework_design.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import selenium_framework_design.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='cartSection'] //h3")
	List<WebElement> cartItems;
	
	@FindBy(xpath = "//li[@class='totalRow']/button")
	WebElement checkoutButton;
	
	public List<WebElement> getCartItems(){
		
		waitForElementsToAppear(By.xpath("//div[@class='cartSection'] //h3"));
		return cartItems;
	}
	
	public void checkCartItems(List<String> wantedProductsList) {
		
		List<WebElement> ci = getCartItems();
		
		if(ci.size()!=wantedProductsList.size()) {
			Assert.assertFalse(true, "error with items in cart not equal to wanted items list");
		}
		else {
			for(int i = 0; i<ci.size();i++ ) {
				System.out.println(ci.get(i).getText());
				if(!wantedProductsList.contains(ci.get(i).getText())) {
					Assert.assertFalse(true,"Items in cart not match items in wanted products list");
				}
			}
		}
	}
	
	public CheckoutPage goToCheckout() throws InterruptedException {
		scrollToBottom(By.xpath("//li[@class='totalRow']/button"));
		Thread.sleep(3000);
		checkoutButton.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
		
	}
}
