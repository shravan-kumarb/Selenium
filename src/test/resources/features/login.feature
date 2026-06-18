Feature: Login
As a SauceDemo user
I want to authenticates
So that I cam access the products page

Background:
	Given the user is on the login page
	

@smoke @login @critical
Scenario:Valid user can login 
When the user logs in as "standard_user"
Then the products page is displayed

@smoke @login @negative
Scenario: Locked out user sees an error
When the user logs in as "locked_out_user"
Then a login error containing "locked out" is shown

@regression @login
Scenario Outline: Perfomance glitch user can still login 
When the user logs in as "performance_glitch_user"
Then the products page is displayed

@regression @login 
Scenario Outline:Invalid login shows an error
When the user logs in with username "<username>" and password "<password>"
Then a login error containing "<message>" is shown



Examples:
|username       |password        |message              |
|standard_user  |wrong           |do not match         |
|no_user        |secret          |do not match         |
|               |secret_sauce    |Username is required |
|standard_user  |                |Password is required |

