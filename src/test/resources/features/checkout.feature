Feature: Checkout
Completing an order and validating checkout information

Background:
Given the user is logged in as "standard_user"
And the user adds "Sauce Labs Backpack" to the cart
And the user adds "Sauce Labs Bike Light" to the cart

@smoke @e2e
Scenario:Complete the full checkout flow
When the user opens the cart
And the user proceeds to checkout
And the user enters valid checkout information
And the order total equals subtotal plus tax
Then the order total equals subtotal plus tax
When the user finishes the order
Then the order is successful

@regression @validation
Scenario Outline:Checkout validation errors
When the user opens the cart
And the user proceeds to checkout
And the user enters checkout info "<first>" "<last>" "<postal>"
Then a checkout error containing "<message>" is shown

Examples:

	|first   |last   |postal    |message             	 |
	|		 |Brown  |543636    |First Name is required  |
	|Venice  |       |545436    |Last Name is required   |
	|Venice  |Brown  |          |Postal Code is required |
