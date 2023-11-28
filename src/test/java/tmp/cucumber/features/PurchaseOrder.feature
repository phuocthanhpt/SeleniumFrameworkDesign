Feature: Submit order

  Background:
    Given I landed on Ecommerce Page

  Scenario Outline: User can submit order successfully
    Given Logged in with username <email> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples:
      | email         | password | productName |
      | tmp@email.com | Auto@123 | ZARA COAT 3 |