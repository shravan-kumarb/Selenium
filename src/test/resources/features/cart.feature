Feature: Cart
Managing items in the shopping cart

Background:
Given the user is logged in as "standard_user"
And the user adds "Sauce Labs Backpack" to the cart
And the user adds "Sauce Labs Bike Light" to the cart


@smoke @cart
Scenario:Cart shows added items
When the user opens the cart
Then the cart shows 2 items
And the cart contains "Sauce Labs Backpack"
And the cart contains "Sauce Labs Bike Light"

@regression @cart
Scenario:User can remove an item from the cart
When the user opens the cart
And the user removes "Sauce Labs Backpack" from the cart page
Then the cart shows 1 items

@regression @cart
Scenario: User can continue shopping from the cart
When the user opens the cart
And the user continues shopping
Then the products page is displayed

@regression @cart
Scenario:User can go to the checkout page
When the user opens the cart
And the user proceeds to checkout
Then the checkout information page is displayed