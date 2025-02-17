package selenuim_framework_design;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import selenium_framework_design.AbstractTestComponent.Retry;

import selenium_framework_design.AbstractTestComponent.AbstractTestComponent;
import selenuim_framework_design.pageObjects.LoginPage;

public class LoginErrorValidation extends AbstractTestComponent {

	@Test(dataProvider="getData",retryAnalyzer=Retry.class,groups= {"errorHandling"})
	public void loginErrorValidation(HashMap<String,String> map) throws IOException {
		LoginPage loginPage = startApplication();
		loginPage.loginApplication(map.get("email"), map.get("password"));
		Assert.assertEquals(loginPage.getErrorMessage(), "Incorrect email or password."); 
	}
	
	@DataProvider
	public Object[][] getData() throws IOException{
//		HashMap<String,String> dataset1 = new HashMap<>();
//		dataset1.put("email", "milan@gmail.com");
//		dataset1.put("password","Milan123@1");
//		
//		HashMap<String,String> dataset2 = new HashMap<>();
//		dataset2.put("email", "milan1234@gmail.com");
//		dataset2.put("password","Milandf453@1");
		
		List<HashMap<String, String>> data =jsonToHashMap(System.getProperty("user.dir")+"//src//test//java//selenium_framework_design//data//errorHandling.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
