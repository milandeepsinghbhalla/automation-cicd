package selenuim_framework_design;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


import org.testng.annotations.Test;

import selenium_framework_design.AbstractTestComponent.AbstractTestComponent;
import selenuim_framework_design.pageObjects.CartPage;
import selenuim_framework_design.pageObjects.CheckoutPage;
import selenuim_framework_design.pageObjects.LoginPage;
import selenuim_framework_design.pageObjects.OrdersPage;
import selenuim_framework_design.pageObjects.ProductsPage;
import selenuim_framework_design.pageObjects.ThankyouPage;  

public class StandAloneTest2 extends AbstractTestComponent {

	@Test
	public void standAloneTest() throws InterruptedException, IOException {

		
//		WebDriver driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//
//		driver.manage().window().maximize();
//
//		LoginPage lp = new LoginPage(driver);
//		lp.goTo();
		LoginPage loginPage = startApplication();
		
		

		String[] wantedProducts = {"ADIDAS ORIGINAL","IPHONE 13 PRO"} ;
		List<String> wantedProductsList = Arrays.asList(wantedProducts);

		ProductsPage pp = loginPage.loginApplication("milansinghdav@gmail.com", "Milansingh@1");
		pp.addElementsToCart(wantedProductsList);

		CartPage cartPage = pp.goToCart();

		
		cartPage.checkCartItems(wantedProductsList);

		CheckoutPage checkoutPage = cartPage.goToCheckout();

		checkoutPage.selectOptionFromDropdown("India");
		ThankyouPage thankyouPage = checkoutPage.clickPlaceOrder();
		
		List<String> orderIds = thankyouPage.getOrderStrings();

		OrdersPage ordersPage = thankyouPage.goToOrders();

		ordersPage.checkOrder(orderIds);
		
//		driver.close();
		

	}

}
