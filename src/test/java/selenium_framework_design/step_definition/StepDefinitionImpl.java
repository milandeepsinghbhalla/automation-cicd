package selenium_framework_design.step_definition;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import selenium_framework_design.AbstractTestComponent.AbstractTestComponent;
import selenuim_framework_design.pageObjects.CartPage;
import selenuim_framework_design.pageObjects.CheckoutPage;
import selenuim_framework_design.pageObjects.LoginPage;
import selenuim_framework_design.pageObjects.OrdersPage;
import selenuim_framework_design.pageObjects.ProductsPage;
import selenuim_framework_design.pageObjects.ThankyouPage;

public class StepDefinitionImpl extends AbstractTestComponent {

	public LoginPage loginPage;
	public ProductsPage productPage;
	public CartPage cartPage;
	public CheckoutPage checkoutPage;
	public ThankyouPage thankyouPage;
	public List<String> orderIds = null;
	public OrdersPage ordersPage;
	
	@Given("Application is started")
	public void application_is_started() throws IOException {
		loginPage = startApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void loginUser(String username, String password) {
		productPage = loginPage.loginApplication(username, password);
	}
	
	@Then("^I add products (.+) and (.+) to cart and move to cart page$")
	public void addProductsToCart(String product_1,String product_2) {
		String[] wantedProducts = {product_1,product_2} ;
		List<String> wantedProductsList = Arrays.asList(wantedProducts);
		productPage.addElementsToCart(wantedProductsList);
		cartPage = productPage.goToCart();
	}
	@Then("^I Verify products (.+) and (.+) and go to checkout$")
	public void verifyCartProducts(String product_1, String product_2) throws InterruptedException {
		String[] wantedProducts = {product_1,product_2} ;
		List<String> wantedProductsList = Arrays.asList(wantedProducts);
		cartPage.checkCartItems(wantedProductsList);
		checkoutPage = cartPage.goToCheckout();
	}
	
	@Then("^I add country (.+) and place order$")
	public void fillCheckoutAndPlaceOrder(String country) {
		checkoutPage.selectOptionFromDropdown(country);
		thankyouPage = checkoutPage.clickPlaceOrder();
	}
	
	@Then("Thankyou page is displayed and i grab order ids")
	public void grabOrderIds() throws InterruptedException {
		orderIds = thankyouPage.getOrderStrings();
		ordersPage = thankyouPage.goToOrders();
	}
	
	@Then("I move to orders page and verify order ids")
	public void verifyOrderIds() {
		ordersPage.checkOrder(orderIds);
	}
}
