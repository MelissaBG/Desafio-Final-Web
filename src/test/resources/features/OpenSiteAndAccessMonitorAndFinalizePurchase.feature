Feature: Open DemoBlaze Site, Access Monitor Category, Add to Cart, Go to Cart, Finalize Purchase and Validate

  Scenario: User opens the homepage, navigates to the Monitor category, adds a monitor to the cart, goes to the cart, completes the purchase and validates the entire flow
    Given I open the DemoBlaze homepage
    Then I validate that the homepage is loaded correctly
    When I navigate to the "Monitor" category
    Then I validate that the Monitor category is visible
    And I add a monitor to the cart
    Then I validate that the monitor is added to the cart
    When I go to the cart
    Then I validate that the checkout page is displayed
    When I finalize the purchase by filling in the required fields and clicking "Purchase"
    Then I validate that the order is completed successfully