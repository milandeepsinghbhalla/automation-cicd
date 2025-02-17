package selenuim_framework_design.pageObjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium_framework_design.AbstractComponents.AbstractComponent;

public class ThankyouPage extends AbstractComponent {
	
	WebDriver driver;
	
	public ThankyouPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//tr[@class='ng-star-inserted'] //label")
	List<WebElement> orderIds;
	
	@FindBy(css = "button[routerlink*='myorders']" )
	WebElement myOrders;
	
	public List<String> getOrderStrings(){
		return orderIds.stream().map(el->{
			String elText = el.getText();
			elText = elText.substring(1, elText.length()-1).trim();
			System.out.println(elText);
			return elText;
		}).collect(Collectors.toList());
	}
	
	public OrdersPage goToOrders() throws InterruptedException {
		scrollToTop(By.cssSelector("button[routerlink*='myorders']"));
		Thread.sleep(3000);
		myOrders.click();
		OrdersPage ordersPage = new OrdersPage(driver);
		return ordersPage;
	}
	
}
