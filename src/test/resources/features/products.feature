Feature: Products
Browsing, sorting and adding products to the cart

Background:
	Given the user is logged in as "standard_user"
	

@smoke
Scenario:Inventory shows all products
Then the inventroy shows 6 products
And the inventory contains "Sauce Labs Backpack"

@regression
Scenario:Sort products by price low to high
When the products are sorted by "lohi"
Then the product prices are sorted ascending


@regression
Scenario: Sort products by name Z to A
When the products are sorted by "za"
Then the product names are sorted descending

@smoke
Scenario:Cart badge updates when adding and removing products
When the user adds "Sauce Labs Backpack" to the cart
And the user adds "Sauce Labs Bike Light" to the cart
Then the cart badge shows 2
When the user removes "Sauce Labs Backpack" from the cart
Then the cart badge shows 1