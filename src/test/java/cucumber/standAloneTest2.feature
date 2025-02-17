
@tag
Feature: To Purchase 2 products from e-commerce website.
  I want to use this template for my feature file
  
  Background:
  	Given Application is started

  @tag2
  Scenario Outline: positive test of purchasing 2 products
    Given Logged in with username <username> and password <password>
    Then I add products <product_1> and <product_2> to cart and move to cart page
    Then I Verify products <product_1> and <product_2> and go to checkout
    Then I add country <country> and place order
    Then Thankyou page is displayed and i grab order ids
    Then I move to orders page and verify order ids

    Examples: 
      | username  							| password 		 | product_1 				| product_2 		| country	|
      | milansinghdav@gmail.com |	Milansingh@1 | ADIDAS ORIGINAL	| IPHONE 13 PRO | India		|