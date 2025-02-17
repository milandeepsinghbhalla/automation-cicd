package selenium_framework_design.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {
	
	WebDriver driver;
	WebDriverWait w;
	JavascriptExecutor js; 
	
	public AbstractComponent(WebDriver driver){
		this.driver = driver;
		w = new WebDriverWait(this.driver,Duration.ofSeconds(5));
		js= (JavascriptExecutor) driver;
	}
	
	public void waitForElementToAppear(By locator) {
//		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitForWebElementToAppear(WebElement el) {
		w.until(ExpectedConditions.visibilityOf(el));
	}
	
	public void waitForElementsToAppear(By locator) {
//		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public void waitForElementToDisappear(WebElement el) {
		w.until(ExpectedConditions.invisibilityOf(el));
	}
	
	public void scrollToBottom(By locator) {
		
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		waitForElementToAppear(locator);
	}
	
	public void scrollToTop(By locator) {
		js.executeScript("window.scrollTo(0,0)");
		waitForElementToAppear(locator);
	}
	
	

}
