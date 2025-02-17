package selenuim_framework_design.pageObjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium_framework_design.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement dropdown;
	
	@FindBy(xpath = "//button[contains(@class,'ta-item')]")
	List<WebElement> countries;
	
	@FindBy(xpath = "//div[@class='actions']/a")
	WebElement placeOrderButton;
	
	public void selectOptionFromDropdown(String str) {
		dropdown.sendKeys(str);
		List<WebElement> selectedCountry = countries.stream().filter(country->country.getText().equalsIgnoreCase(str)).collect(Collectors.toList());
		selectedCountry.get(0).click();
	}
	
	public ThankyouPage clickPlaceOrder() {
		placeOrderButton.click();
		ThankyouPage thankyouPage = new ThankyouPage(driver);
		return thankyouPage;
	}
	
	
}
