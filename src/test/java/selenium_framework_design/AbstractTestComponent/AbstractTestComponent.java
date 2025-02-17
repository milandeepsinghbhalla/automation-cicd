package selenium_framework_design.AbstractTestComponent;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import selenuim_framework_design.pageObjects.LoginPage;

public class AbstractTestComponent {
	public WebDriver driver;
	
	public WebDriver initializeDriver() throws IOException {
		
		Properties p = new Properties();
		FileReader reader = new FileReader(System.getProperty("user.dir")+"//src//main//java//global.properties");
		p.load(reader);
		String browser = p.getProperty("browser");
		
		if(browser.equalsIgnoreCase("chrome")) {
//			ChromeOptions options = new ChromeOptions();
//			options.addArguments("headless");
			driver = new ChromeDriver();
			driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			// firefox code
		}
		else {
			// default code
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.manage().window().maximize();
		
		return driver;
	}
	
	public LoginPage startApplication() throws IOException {
		driver = initializeDriver();
		LoginPage loginPage = new LoginPage(driver);
		loginPage.goTo();
		return loginPage;
	}
	
	public List<HashMap<String, String>> jsonToHashMap(String filePath) throws IOException {
		String jsonString = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> hashMapList = mapper.readValue(jsonString,
                new TypeReference<List<HashMap<String, String>>>(){});
		return hashMapList;
	}
	
	public String getScreenShot(String testCaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir")+"//screenshots//"+testCaseName+".png");
		FileUtils.copyFile(source, dest);
		return System.getProperty("user.dir")+"//screenshots//"+testCaseName+".png";
	}

}
