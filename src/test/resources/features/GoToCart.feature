Feature: Open DemoBlaze Site, Access Monitor Category, and Go To Cart

  Scenario: User opens the homepage, navigates to the Monitor category, and goes to the cart
    Given I open the DemoBlaze homepage
    When I navigate to the "Monitor" category
    And I go to the cart
    Then I should see the items in the cart