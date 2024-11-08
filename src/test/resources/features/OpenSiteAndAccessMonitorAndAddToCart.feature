Feature: Open DemoBlaze Site, Access Monitor Category, Add to Cart, and Go to Cart

  Scenario: User opens the homepage, navigates to the Monitor category, adds a monitor to the cart, and goes to the cart
    Given I open the DemoBlaze homepage
    When I navigate to the "Monitor" category
    And I add a monitor to the cart
    And I go to the cart
    Then I should see the monitor in the cart