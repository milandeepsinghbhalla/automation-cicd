package selenuim_framework_design.pageObjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import selenium_framework_design.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent {

	WebDriver driver;
	
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//tbody/tr/th")
	List<WebElement> actualOrderIds;
	
	public void checkOrder(List<String> orderIds) {
		List<String>actualOrderIdStrings = actualOrderIds.stream().map(oid->oid.getText()).collect(Collectors.toList());
		orderIds.stream().forEach(oid->{
			if(!actualOrderIdStrings.contains(oid)) {
				Assert.assertFalse(true,"Order id not present in orders");
			}
		}
		);
	}
}
