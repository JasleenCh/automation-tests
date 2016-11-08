@payments
Feature: Buy Subscription using world pay
  As a user
  In order to buy the subscription using world pay
  I want to extend the subscription on yearly basis

  Background:
    Given I am logged in
    When I select the my company option
    And I click the subscriptions button

  Scenario Outline: 1. User should able to extend the subscription
    When I input number of sites as "<No of sites>", number of years as "<No of years>" and click calculate
    Then I should see that new expiration date is extended by "<No of years>" years
    And And I click the pay via WorldPay button
    And I select the payment provider
    And I enter the card details
    Then I can successfully make a payment

    Examples:
      | No of sites | No of years |
      | 20          | 2           |